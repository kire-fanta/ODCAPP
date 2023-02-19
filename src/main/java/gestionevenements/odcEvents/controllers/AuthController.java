package gestionevenements.odcEvents.controllers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import gestionevenements.odcEvents.models.ERole;
import gestionevenements.odcEvents.models.Role;
import gestionevenements.odcEvents.models.User;
import gestionevenements.odcEvents.repository.RoleRepository;
import gestionevenements.odcEvents.repository.UserRepository;
import gestionevenements.odcEvents.security.services.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;



import gestionevenements.odcEvents.payload.request.LoginRequest;
import gestionevenements.odcEvents.payload.request.SignupRequest;
import gestionevenements.odcEvents.payload.response.JwtResponse;
import gestionevenements.odcEvents.payload.response.MessageResponse;
import gestionevenements.odcEvents.security.jwt.JwtUtils;
import gestionevenements.odcEvents.security.services.UserDetailsImpl;
import org.thymeleaf.util.StringUtils;

import static gestionevenements.odcEvents.image.UserImage.USER_FOLDER;

//@CrossOrigin(origins = "*", maxAge = 3600)


@CrossOrigin(value = {"http://localhost:8100","http://localhost:4200"},maxAge = 3600 , allowCredentials = "true")
@RestController
@RequestMapping("/api/auth")
public class AuthController {
  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  private userService userservice;

  @Autowired
  BCryptPasswordEncoder bCryptPasswordEncoder;
  @Autowired
  UserRepository userRepository;

  @Autowired
  RoleRepository roleRepository;

  @Autowired
  PasswordEncoder encoder;

  @Autowired
  JwtUtils jwtUtils;

  public static final File TEMP_USER = new File("src/main/resources/static/images/user/temp/profile.png");
  @PostMapping("/connexion")
  public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

    SecurityContextHolder.getContext().setAuthentication(authentication);
    String jwt = jwtUtils.generateJwtToken(authentication);
    
    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();    
    List<String> roles = userDetails.getAuthorities().stream()
        .map(item -> item.getAuthority())
        .collect(Collectors.toList());

    return ResponseEntity.ok(new JwtResponse(jwt, 
                         userDetails.getId(), 
                         userDetails.getUsername(), 
                         userDetails.getEmail(), 
                         roles));
  }

  @PostMapping("/inscrire")
  public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
    if (userRepository.existsByUsername(signUpRequest.getUsername())) {
      return ResponseEntity
          .badRequest()
          .body(new MessageResponse("Error: Username is already taken!"));
    }

    if (userRepository.existsByEmail(signUpRequest.getEmail())) {
      return ResponseEntity
          .badRequest()
          .body(new MessageResponse("Error: Email is already in use!"));
    }

    // Create new user's account
    User user = new User(signUpRequest.getNom(),
            signUpRequest.getPrenom(),
            signUpRequest.getUsername(),
               signUpRequest.getEmail(),
               encoder.encode(signUpRequest.getPassword()));

    Set<String> strRoles = signUpRequest.getRole();
    Set<Role> roles = new HashSet<>();

    if (strRoles == null) {
      Role userRole = roleRepository.findByName(ERole.ROLE_USER)
          .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
      roles.add(userRole);
    } else {
      strRoles.forEach(role -> {
        switch (role) {
        case "admin":
          Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
          roles.add(adminRole);

          break;
        case "mod":
          Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
          roles.add(modRole);

          break;
        default:
          Role userRole = roleRepository.findByName(ERole.ROLE_USER)
              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
          roles.add(userRole);
        }
      });
    }

    user.setRoles(roles);
    userRepository.save(user);
    byte[] bytes;
    try {
      bytes = Files.readAllBytes(TEMP_USER.toPath());
      String fileName = user.getId() + ".png";
      user.setImageprofil(fileName);
      userRepository.save(user);
      Path path = Paths.get(USER_FOLDER + fileName);
      Files.write(path, bytes);
    } catch (IOException e) {
      e.printStackTrace();
    }

    return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
  }

  // Déconnexion d'un nouveau user
  @PostMapping("/signout")
  public ResponseEntity<?> logoutUser() {
    ResponseCookie cookie = jwtUtils.getCleanJwtCookie();
    return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString())
            .body(new MessageResponse("Vous avez été déconnecté!"));
  }





  @PostMapping("/changePassword/{email}")
  public ResponseEntity<String> changePassword(@RequestBody HashMap<String, String> request, @PathVariable String email) {
    //String numeroOrEmail = request.get("numeroOrEmail");
    User user = userRepository.findByEmail(email);
    if (user == null) {
      return new ResponseEntity<>("Utilisateur non fourni!", HttpStatus.BAD_REQUEST);
    }
    String currentPassword = request.get("currentpassword");
    String newPassword = request.get("newpassword");
    String confirmpassword = request.get("confirmpassword");
    if (!newPassword.equals(confirmpassword)) {
      return new ResponseEntity<>("PasswordNotMatched", HttpStatus.BAD_REQUEST);
    }
    String userPassword = user.getPassword();
    try {
      if (newPassword != null && !newPassword.isEmpty() && !StringUtils.isEmpty(newPassword)) {
        if (bCryptPasswordEncoder.matches(currentPassword, userPassword)) {
          userservice.updateUserPassword(user, newPassword);
        }
      } else {
        return new ResponseEntity<>("IncorrectCurrentPassword", HttpStatus.BAD_REQUEST);
      }
      return new ResponseEntity<>("Mot de passe changé avec succès!", HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>("Error Occured: " + e.getMessage(), HttpStatus.BAD_REQUEST);
    }
  }
}
