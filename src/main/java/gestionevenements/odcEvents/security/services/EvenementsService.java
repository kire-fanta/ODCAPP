package gestionevenements.odcEvents.security.services;



import gestionevenements.odcEvents.models.Evenements;
import gestionevenements.odcEvents.models.Status;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EvenementsService {
    Evenements addEvenement(Evenements evenement);
    Evenements updateEvenement(Evenements evenement,Long idevent );
    void deongeteEvenement(Long idEvenement);
    List<Evenements> getAllEvenements();
    List<Evenements> getAllEvenementsByStatus(String status);
    Evenements getEvenementById(Long idEvenement);


    void deleteEvenement(Long idEvenement);
    byte[] getImage(long id);
}


