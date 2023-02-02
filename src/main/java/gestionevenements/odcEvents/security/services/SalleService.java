package gestionevenements.odcEvents.security.services;




import org.springframework.web.multipart.MultipartFile;

import gestionevenements.odcEvents.models.Salles;

import java.util.List;

public interface SalleService {

    // Creer Salle
    Salles create(Salles salles);

    // Afficher une salle par son id
    Salles read(long id);

    // Modifier salle par id
    Salles update(Salles salle, long id);

    // Effacer salle
    void delete(long id);

    List<Salles> getAll();



    // Afficher salle par libelle
    Salles getByLibelle(String libelle);
    Salles getByIdsalle(long id);

    /// list des salles par disponiblite
    List<Salles> ParEtat(Boolean disponibilite);

    // Méthode pour récupérer l'image d'une salle
    byte[] getImage(long id);

}
