package gestionevenements.odcEvents.security.services;

import gestionevenements.odcEvents.models.Evenements;
import gestionevenements.odcEvents.models.PrevisionsDepenses;
import gestionevenements.odcEvents.repository.PrevisionsDepensesRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class PrevisionsDepensesServiceImpl implements PrevisionsDepensesService{
    @Autowired
    private PrevisionsDepensesRepository repository;
    @Override
    public float calculerDepense(PrevisionsDepenses prevision) {
        return  prevision.getPrixUnitaire() * prevision.getQuantite();
    }

    @Override
    public PrevisionsDepenses gererPrevision(PrevisionsDepenses prevision, Evenements evenements) {
        prevision.setEvenements(evenements);
        return repository.save(prevision);
    }

    @Override
    public List<PrevisionsDepenses> getAllPrevisions() {
        return repository.findAll();
    }

    @Override
    public void supprimerPrevision(Long idPrevision) {
        repository.deleteById(idPrevision);
    }

    @Override
    public PrevisionsDepenses getPrevisionById(Long id) {
        return repository.findById(id).orElse(null);
    }
}
