package gestionevenements.odcEvents.controllers;

import gestionevenements.odcEvents.MailConfig.SaveImage;
import gestionevenements.odcEvents.models.Evenements;
import gestionevenements.odcEvents.models.Salles;
import gestionevenements.odcEvents.models.Status;
import gestionevenements.odcEvents.models.User;
import gestionevenements.odcEvents.repository.StatutRepository;
import gestionevenements.odcEvents.repository.UserRepository;
import gestionevenements.odcEvents.security.services.EvenementsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.text.DateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
//@CrossOrigin(origins = "http://localhost:8100",allowCredentials = "true")
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/evenements")
public class EvenementsController {
    @Autowired
   private EvenementsService evenementsService;

    @Autowired
    private StatutRepository statutRepository;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/add/1")
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
    @GetMapping("/allByStatus/{status}")
    public List<Evenements> getAllEvenementsByStatus(@PathVariable String status) {
        return evenementsService.getAllEvenementsByStatus(status);
    }

    @GetMapping("/{idEvenement}")
    public Evenements getEvenementById(@PathVariable Long idEvenement) {
        return evenementsService.getEvenementById(idEvenement);
    }

    @PostMapping("/add/{id_status}/{id_user}")
    public Evenements create(@Param("nomEvenement") String nomEvenement,
                         @Param("lieu") String lieu,
                         @Param("etat") Boolean etat,
                         @Param("heuredebut") String heuredebut,
                         @Param("heurefin") String heurefin,
                         @Param("datedebut") String datedebut,
                         @Param("datefin") String datefin,
                         @Param("description") String description,
                         @Param("duree") int duree,
                         @Param("typeEvenement") String typeEvenement,
                         @Param("file") MultipartFile file,
                             @PathVariable("id_user") Long id_user,
                             @PathVariable("id_status") Long id_status
    ) {
        //traitement pour enregistrer l'image sur le disque dur
        //...
        Evenements  Events = new Evenements();
        String nomfile = StringUtils.cleanPath(file.getOriginalFilename());
        //salles1.setImage(nomfile);

        DateTimeFormatter formatter  = DateTimeFormatter.ofPattern("yyyy-MM-d");
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("H:mm");

        //convert String to LocalDate
        LocalDate datedebut1 = LocalDate.parse(datedebut, formatter);
        LocalDate datefin1   = LocalDate.parse(datefin, formatter);

        LocalTime heuredebut1 = LocalTime.parse(heuredebut, formatter1);
        LocalTime heurefin1 = LocalTime.parse(heurefin, formatter1);
        Events.setLieu(lieu);
        Events.setImage(SaveImage.save(file,nomfile));
        Events.setDatedebut(datedebut1);
        Events.setDatefin(datefin1);
        Events.setNomEvenement(nomEvenement);
        Events.setHeuredebut(heuredebut1);
        Events.setHeurefin(heurefin1);
        Events.setEtat(false);
        Events.setDescription(description);
        Events.setTypeEvenement(typeEvenement);
        Events.setDuree(duree);

        Status status = statutRepository.findById(id_status).get();
        Events.setStatus(status);

        User user = userRepository.findById(id_user).get();
        Events.setUser(user);
        return evenementsService.addEvenement(Events);
    }





    /*@PostMapping("/add/{id_user}")
    public Evenements create2(@Param("nomEvenement") String nomEvenement,
                             @Param("lieu") String lieu,
                             @Param("etat") Boolean etat,
                             @Param("heure") int  heure,
                             @Param("description") String description,
                             @Param("duree") int duree,
                             @Param("typeEvenement") String typeEvenement,
                             @Param("file") MultipartFile file,
                             @PathVariable("id_user") Long id_user,
                             @PathVariable("id_status") Long id_status
    ) {
        //traitement pour enregistrer l'image sur le disque dur
        //...
        Evenements  Events = new Evenements();
        String nomfile = StringUtils.cleanPath(file.getOriginalFilename());
        //salles1.setImage(nomfile);
        Events.setLieu(lieu);
        Events.setImage(SaveImage.save(file,nomfile));
        Events.setDate(new Date());
        Events.setNomEvenement(nomEvenement);
        Events.setHeure(heure);
        Events.setEtat(etat);
        Events.setDescription(description);
        Events.setTypeEvenement(typeEvenement);
        Events.setDuree(duree);

        Status status = statutRepository.findById(id_status).get();
        Events.setStatus(status);

        User user = userRepository.findById(id_user).get();
        Events.setUser(user);
        return evenementsService.addEvenement(Events);
    }*/

}

