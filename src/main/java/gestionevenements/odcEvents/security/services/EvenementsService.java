package gestionevenements.odcEvents.security.services;



import gestionevenements.odcEvents.models.Evenements;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EvenementsService {
    Evenements addEvenement(Evenements evenement);
    Evenements updateEvenement(Evenements evenement,Long idevent );
    void deongeteEvenement(Long idEvenement);
    List<Evenements> getAllEvenements();
    Evenements getEvenementById(Long idEvenement);

    void deleteEvenement(Long idEvenement);
}


