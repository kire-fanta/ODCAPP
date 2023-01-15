package gestionevenements.odcEvents.controllers;

import gestionevenements.odcEvents.models.PrevisionsDepenses;
import gestionevenements.odcEvents.security.services.PrevisionsDepensesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/previsions-depenses")
public class PrevisionsDepensesController {

    @Autowired
    private final PrevisionsDepensesService service;

    public PrevisionsDepensesController(PrevisionsDepensesService service) {
        this.service = service;
    }

    @PostMapping("/add")
    public PrevisionsDepenses gererPrevision(@RequestBody PrevisionsDepenses prevision) {
        return service.gererPrevision(prevision);
    }


    @GetMapping("/all")
    public List<PrevisionsDepenses> getAllPrevisions() {
        return service.getAllPrevisions();
    }

    @DeleteMapping("/{id}")
    public void supprimerPrevision(@PathVariable Long id) {
        service.supprimerPrevision(id);
    }

    @GetMapping("/calculer-depense/{id}")
    public float calculerDepense(@PathVariable Long id) {
        PrevisionsDepenses prevision = service.getPrevisionById(id);
        return service.calculerDepense(prevision);
    }

}
