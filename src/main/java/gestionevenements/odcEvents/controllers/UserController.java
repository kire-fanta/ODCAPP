package gestionevenements.odcEvents.controllers;

import gestionevenements.odcEvents.image.ConfigImage;
import gestionevenements.odcEvents.models.User;
import gestionevenements.odcEvents.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
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
                    //p.setEmail(email);
                    p.setNom(nom);
                    p.setPrenom(prenom);
                    if (imageprofil != null) {
                        String img1 = StringUtils.cleanPath(imageprofil.getOriginalFilename());
                        p.setImageprofil(img1);
                        String uploaDir1 = "src/main/ressources/images/user";
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
}
