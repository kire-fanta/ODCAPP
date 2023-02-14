package gestionevenements.odcEvents.controllers;

import gestionevenements.odcEvents.image.ConfigImage;
import gestionevenements.odcEvents.models.User;
import gestionevenements.odcEvents.repository.UserRepository;
import gestionevenements.odcEvents.security.services.userService;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {
    private final UserRepository userRepository;

    private final userService UserService;

    public UserController(UserRepository userRepository, userService userService) {
        this.userRepository = userRepository;
        UserService = userService;
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateUser(@PathVariable Long id,
                                             @RequestParam("username") String username,
                                             @RequestParam("nom") String nom,
                                             @RequestParam("prenom") String prenom,
                                             @RequestParam(value = "imageprofil", required = false) MultipartFile imageprofil,
                                             @RequestParam("email") String email) throws IOException {
        return userRepository.findById(id)
                .map(p -> {
                    p.setUsername(username);
                    p.setEmail(email);
                    p.setNom(nom);
                    p.setPrenom(prenom);
                    p.setPassword(p.getPassword());
                    if (imageprofil != null) {
                        String img1 = StringUtils.cleanPath(imageprofil.getOriginalFilename());
                        p.setImageprofil(img1);
                        String uploaDir1 = "src/main/resources/static/images/user";
                        try {
                            ConfigImage.saveimg(uploaDir1, img1, imageprofil);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    userRepository.save(p);
                    return ResponseEntity.ok("user modifié avec succès !!");
                }).orElseThrow(() -> new RuntimeException("user non trouvé !!"));
    }
    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id){
        return userRepository.findById(id).get();
    }
    @GetMapping("/all")
    public List<User> getAllUtilisateurs() {
        return UserService.getAllUtilisateurs();
    }

}
