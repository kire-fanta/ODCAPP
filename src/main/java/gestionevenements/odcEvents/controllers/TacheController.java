package gestionevenements.odcEvents.controllers;

import gestionevenements.odcEvents.models.Tache;
import gestionevenements.odcEvents.security.services.TacheService;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/tache")
public class TacheController {

    private TacheService tacheService;

    public TacheController(TacheService tacheService) {
        this.tacheService = tacheService;
    }

    @PostMapping("/add")
    public Tache addTache(@RequestBody Tache tache) {
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

