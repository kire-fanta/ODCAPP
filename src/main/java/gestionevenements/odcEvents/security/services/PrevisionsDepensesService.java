package gestionevenements.odcEvents.security.services;

import gestionevenements.odcEvents.models.PrevisionsDepenses;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface PrevisionsDepensesService {
    float calculerDepense(PrevisionsDepenses prevision);
    PrevisionsDepenses gererPrevision(PrevisionsDepenses prevision);
    List<PrevisionsDepenses> getAllPrevisions();
    void supprimerPrevision(Long idPrevision);

    PrevisionsDepenses getPrevisionById(Long id);
}

