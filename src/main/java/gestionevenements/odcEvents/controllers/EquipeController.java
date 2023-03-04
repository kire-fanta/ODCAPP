package gestionevenements.odcEvents.controllers;

import gestionevenements.odcEvents.models.Equipe;
import gestionevenements.odcEvents.security.services.EquipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//@CrossOrigin(origins = "http://localhost:8100",allowCredentials = "true")
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/equipes")
public class EquipeController {

    @Autowired
    private EquipeService equipeService;

    /*
    * Cette méthode gère la requête HTTP GET pour récupérer toutes les équipes
    */
    @GetMapping("/afficher")
    public List<Equipe> findAll() {
        return equipeService.findAll();
    }

    /*
    * Cette méthode gère la requête HTTP POST pour créer une nouvelle équipe
    */
    @PostMapping("/add")
    public Equipe create(@RequestBody Equipe equipe) {
        return equipeService.save(equipe);
    }

    /*
    * Cette méthode gère la requête HTTP GET pour récupérer une équipe par son ID
    */
    @GetMapping("/{id}")
    public Equipe findById(@PathVariable Long id) {
        return equipeService.findById(id);
    }

    /*
    * Cette méthode gère la requête HTTP PUT pour mettre à jour une équipe existante
    */
    @PutMapping("/update/{idequipe}")
    public Equipe update(@RequestBody Equipe equipe) {
        return equipeService.update(equipe);
    }

    /*
    * Cette méthode gère la requête HTTP DELETE pour supprimer une équipe par son ID
    */
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        equipeService.delete(id);
    }
}