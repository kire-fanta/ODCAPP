package gestionevenements.odcEvents.security.services;

import gestionevenements.odcEvents.models.Status;
import gestionevenements.odcEvents.repository.StatutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatutService {

    @Autowired
    private StatutRepository statutRepository;

    // Méthode pour trouver tous les statuts en base de données
    public List<Status> findAll() {
        return statutRepository.findAll();
    }

    // Méthode pour trouver un statut par son identifiant
    public Status findById(Long id) {
        return statutRepository.findById(id).orElse(null);
    }

    // Méthode pour créer un nouveau statut en base de données
    public Status create(Status statut) {
        return statutRepository.save(statut);
    }

    // Méthode pour mettre à jour un statut existant en base de données
    public Status update(Status statut) {
        return statutRepository.save(statut);
    }

    // Méthode pour supprimer un statut en base de données
    public void delete(Long id) {
        statutRepository.deleteById(id);
    }
}
