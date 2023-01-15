package gestionevenements.odcEvents.security.services;

import gestionevenements.odcEvents.models.Status;
import gestionevenements.odcEvents.repository.StatutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StatutServiceImpl extends StatutService {

    @Autowired
    private StatutRepository statutRepository;

    // Méthode pour trouver tous les statuts en base de données
    @Override
    public List<Status> findAll() {
        return statutRepository.findAll();
    }

    // Méthode pour trouver un statut par son identifiant
    @Override
    public Status findById(Long id) {
        Optional<Status> statut = statutRepository.findById(id);
        if (statut.isPresent()) {
            return statut.get();
        } else {
            return null;
        }
    }

    // Méthode pour créer un nouveau statut en base de données
    @Override
    public Status create(Status statut) {
        return statutRepository.save(statut);
    }




    // Méthode pour supprimer un statut en base de données
    @Override
    public void delete(Long id) {
        statutRepository.deleteById(id);
    }
}
