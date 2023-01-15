package gestionevenements.odcEvents.controllers;

import gestionevenements.odcEvents.models.Evenements;
import gestionevenements.odcEvents.security.services.EvenementsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/evenements")
public class EvenementsController {
    @Autowired
   private EvenementsService evenementsService;

    @PostMapping("/add")
    public Evenements addEvenement(@RequestBody Evenements evenement) {
        return evenementsService.addEvenement(evenement);
    }

    @PutMapping("/update/{idevent}")
    public Evenements updateEvenement(@RequestBody Evenements evenement,@PathVariable("idevent") Long idevent) {
        return evenementsService.updateEvenement(evenement,idevent);
    }

    @DeleteMapping("/delete/{idEvenement}")
  public void deleteEvenement(@PathVariable Long idEvenement) {
       evenementsService.deleteEvenement(idEvenement);
   }

    @GetMapping("/all")
    public List<Evenements> getAllEvenements() {
        return evenementsService.getAllEvenements();
    }

    @GetMapping("/{idEvenement}")
    public Evenements getEvenementById(@PathVariable Long idEvenement) {
        return evenementsService.getEvenementById(idEvenement);
    }
}

