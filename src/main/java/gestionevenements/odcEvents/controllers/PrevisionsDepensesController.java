package gestionevenements.odcEvents.controllers;

import gestionevenements.odcEvents.models.Evenements;
import gestionevenements.odcEvents.models.PrevisionsDepenses;
import gestionevenements.odcEvents.repository.PrevisionsDepensesRepository;
import gestionevenements.odcEvents.security.services.PrevisionsDepensesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//@CrossOrigin(origins = "http://localhost:8100",allowCredentials = "true")
@CrossOrigin
@RestController
@RequestMapping("/previsions-depenses")
public class PrevisionsDepensesController {

    float totaux = 0;
    float montant = 0;

    @Autowired
    private final PrevisionsDepensesService service;
    private final PrevisionsDepensesRepository previsionsDepensesRepository;

    public PrevisionsDepensesController(PrevisionsDepensesService service,
                                        PrevisionsDepensesRepository previsionsDepensesRepository) {
        this.service = service;
        this.previsionsDepensesRepository = previsionsDepensesRepository;
    }

    @PostMapping("/add")
    public PrevisionsDepenses gererPrevision(@RequestBody PrevisionsDepenses prevision) {
        System.out.println("AAAA "+prevision.getPrixUnitaire());
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
    @GetMapping("/recuperer/{idevent}")
    public Object getServices(@PathVariable("idevent") Evenements idevent) {
         List<PrevisionsDepenses> previsionsDepenses = previsionsDepensesRepository.findByEvenements(idevent);
        for (PrevisionsDepenses p:previsionsDepenses) {
            montant = p.getPrixUnitaire()*p.getQuantite();
            totaux += montant;
            System.out.println("Test "+totaux);

        }            System.out.println("Test2 "+totaux);

        return previsionsDepensesRepository.findByEvenements(idevent);
    }
}
