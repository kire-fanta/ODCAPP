package gestionevenements.odcEvents.controllers;

import gestionevenements.odcEvents.models.Salles;
import gestionevenements.odcEvents.security.services.SalleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/salles")
public class SalleController {

    @Autowired
    SalleService salleService;

    @PostMapping("/add")
    public Salles create(@RequestBody Salles salles) {
        return salleService.create(salles);
    }

    @GetMapping("/afficher")
    public List<Salles> getAll() {
        return salleService.getAll();
    }

    @GetMapping("/liste/{id}")
    public Salles read(@PathVariable long id) {
        return salleService.read(id);
    }

    @PutMapping("/{id}")
    public Salles update(@RequestBody Salles salle, @PathVariable long id) {
        return salleService.update(salle, id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        salleService.delete(id);
    }

    @GetMapping("/libelle/{libelle}")
    public Salles getByLibelle(@PathVariable String libelle) {
        return salleService.getByLibelle(libelle);
    }

    @GetMapping("/idsalle/{id}")
    public Salles getByIdsalle(@PathVariable long id) {
        return salleService.getByIdsalle(id);
    }

    @GetMapping("/disponibilite/{disponibilite}")
    public List<Salles> ParEtat(@PathVariable boolean disponibilite) {
        return salleService.ParEtat(disponibilite);
    }
}
