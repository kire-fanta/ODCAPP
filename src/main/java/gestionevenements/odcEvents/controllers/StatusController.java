package gestionevenements.odcEvents.controllers;

import gestionevenements.odcEvents.models.Status;
import gestionevenements.odcEvents.security.services.StatutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:8100",allowCredentials = "true")

@RestController
@RequestMapping("/status")
public class StatusController {
    // Injection de dépendance de la classe Service pour pouvoir utiliser les méthodes de création, lecture, mise à jour et suppression de statuts
    @Autowired
    private StatutService statutService;

    // Méthode pour récupérer tous les statuts en base de données
    @GetMapping("/all")
    public List<Status> findAll() {
        return statutService.findAll();
    }

    // Méthode pour récupérer un statut par son identifiant
    @GetMapping("/{id}")
    public Status findById(@PathVariable Long id) {
        return statutService.findById(id);
    }

    // Méthode pour créer un nouveau statut en base de données
    @PostMapping("/add")
    public Status create(@RequestBody Status statut) {
        return statutService.create(statut);
    }

    // Méthode pour mettre à jour un statut existant en base de données
    @PutMapping
    public Status update(@RequestBody Status statut) {
        return statutService.update(statut);
    }

    // Méthode pour supprimer un statut en base de données
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        statutService.delete(id);
    }
}
