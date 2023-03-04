package gestionevenements.odcEvents.controllers;

import gestionevenements.odcEvents.models.Tache;
import gestionevenements.odcEvents.models.User;
import gestionevenements.odcEvents.repository.UserRepository;
import gestionevenements.odcEvents.security.services.TacheService;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.UserDataHandler;

import java.util.Date;
import java.util.List;
//@CrossOrigin(origins = "http://localhost:8100",allowCredentials = "true")
//@CrossOrigin
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/tache")
public class TacheController {

    private TacheService tacheService;
    private final UserRepository userRepository;

    public TacheController(TacheService tacheService,
                           UserRepository userRepository) {
        this.tacheService = tacheService;
        this.userRepository = userRepository;
    }

    @PostMapping("/add/{id_user}")
    public Tache addTache(@RequestBody Tache tache ,@PathVariable Long id_user) {
        User user= userRepository.findById(id_user).get();

        return tacheService.addTache(tache);
    }

    @PostMapping("/add")
    public Object addTaches(@RequestBody Tache tache) {
       // List<User> user= userRepository.findById(tache.getUtilisateurs().get(0).getId()).get();

        return tacheService.addTache(tache);
    }

    @PutMapping
    public Tache updateTache(@RequestBody Tache tache) {
        return tacheService.updateTache(tache);
    }

    @DeleteMapping("/{idTache}")
    public void deleteTache(@PathVariable Long idTache) {
        tacheService.deleteTache(idTache);
    }

    @GetMapping("/all")
    public List<Tache> getAllTaches() {
        return tacheService.getAllTaches();
    }

    @GetMapping("/{idTache}")
    public Tache getTacheById(@PathVariable Long idTache) {
        return tacheService.getTacheById(idTache);
    }

    @PutMapping("/assigner/{idTache}/{idUser}")
    public void assignerTache(@PathVariable Long idTache, @PathVariable Long idUser) {
        tacheService.assignerTache(idTache, idUser);
    }

    @PutMapping("/modifierDate/{idTache}")
    public void modifierDate(@PathVariable Long idTache, @RequestBody Date dateEcheance) {
        tacheService.modifierDate(idTache, dateEcheance);
    }
}

