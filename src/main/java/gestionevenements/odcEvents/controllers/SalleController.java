package gestionevenements.odcEvents.controllers;

import gestionevenements.odcEvents.MailConfig.SaveImage;
import gestionevenements.odcEvents.models.Evenements;
import gestionevenements.odcEvents.models.Salles;
import gestionevenements.odcEvents.security.services.SalleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.util.List;
//@CrossOrigin(origins = "http://localhost:8100",allowCredentials = "true")
@CrossOrigin

@RestController
@RequestMapping("/salles")
public class SalleController {

    @Autowired
    SalleService salleService;

    @PostMapping("/add/O")
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
    @PostMapping("/add")
    public Salles create(@Param("nombreplace") long nombreplace,
                         @Param("libelle") String libelle,
                         @Param("disponibilite") Boolean disponibilite,
                         @Param(" numero") int  numero,
                         @Param(" description") String description,
                         @Param(" etage") String etage,
                         @Param(" evenement") Evenements evenements,
                         @Param("file") MultipartFile file
    ) {
        //traitement pour enregistrer l'image sur le disque dur
        //...
        Salles salles1 = new Salles();
        String nomfile = StringUtils.cleanPath(file.getOriginalFilename());
        //salles1.setImage(nomfile);
        salles1.setLibelle(libelle);
        salles1.setEtage(etage);
        salles1.setDescription(description);
        salles1.setNombreplace(nombreplace);
        salles1.setDisponibilite(disponibilite);
        salles1.setNumero(numero);
        salles1.setEvenements(evenements);
        salles1.setImage(SaveImage.save(file,nomfile));
        return salleService.create(salles1);
    }





}
