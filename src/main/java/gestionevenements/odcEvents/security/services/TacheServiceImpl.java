package gestionevenements.odcEvents.security.services;

import gestionevenements.odcEvents.MailConfig.EmailConstructor;
import gestionevenements.odcEvents.models.Notification;
import gestionevenements.odcEvents.models.Tache;
import gestionevenements.odcEvents.models.User;
import gestionevenements.odcEvents.repository.NotificationRepository;
import gestionevenements.odcEvents.repository.TacheRepository;
import gestionevenements.odcEvents.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.hibernate.usertype.UserCollectionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor

public class TacheServiceImpl implements TacheService{
    @Autowired
    private TacheRepository TR;

    private EmailConstructor emailConstructor;

    private JavaMailSender mailSender;
    private final UserRepository userRepository;
    private final NotificationRepository notificationRepository;

    @Override
    public Tache addTache(Tache tache) {
        tache.setUtilisateurs(tache.getUtilisateurs());
        tache.setStatus(tache.getStatus());
        System.out.println("zzzzzzzzzzz "+tache.getUtilisateurs());
        List<User> lesUsers = tache.getUtilisateurs();
        lesUsers.forEach(user -> {
            mailSender.send(emailConstructor.constructAssignerTacheEmail(user,tache ));

        });
        return TR.save(tache);
    }

    @Override
    public Tache updateTache(Tache tache) {
        Tache updatedTache = TR.findById(tache.getIdTache()).orElse(null);
        if (updatedTache != null) {
            updatedTache.setNomTache(tache.getNomTache());
            updatedTache.setDescriptionTache(tache.getDescriptionTache());
            updatedTache.setDateEcheance(tache.getDateEcheance());

            TR.save(updatedTache);
            return updatedTache;
        } else {
            return null;
        }
    }

    @Override
    public void deleteTache(Long idTache) {
        TR.deleteById(idTache);
    }

    @Override
    public List<Tache> getAllTaches() {
        return TR.findAll();
    }

    @Override
    public Tache getTacheById(Long idTache) {
        return  TR.findById(idTache).orElse(null);
    }

    @Override
    public void assignerTache(Long idTache, Long idUser) {
        Tache tache = TR.findById(idTache).orElse(null);
        //recuperation de l'utilisateur
        User user = userRepository.findById(idUser).get();
        //creation de la notification
        Notification notification = new Notification();
        notification.setTitre(tache.getNomTache());
        notification.setDate(new Date());
        notification.setDescription("Cher utilisateur vous avez recu une nouvelle tache : " + tache.getDescriptionTache());
        notificationRepository.save(notification);
        user.getNotifications().add(notification);
        if (tache != null) {
            tache.setRoleUser();
            TR.save(tache);
        }

        mailSender.send(emailConstructor.constructAssignerTacheEmail(user,tache));
    }

    @Override
    public void modifierDate(Long idTache, Date dateEcheance) {
        Tache updatedTache = TR.findById(idTache).orElse(null);
        if (updatedTache != null) {
            updatedTache.setDateEcheance(dateEcheance);
            TR.save(updatedTache);
        }
    }

}
