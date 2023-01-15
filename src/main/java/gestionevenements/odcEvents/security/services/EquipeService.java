package gestionevenements.odcEvents.security.services;

import gestionevenements.odcEvents.models.Equipe;
import gestionevenements.odcEvents.repository.EquipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EquipeService {
    // Injection de dépendance de l'interface de la classe EquipeRepository
    @Autowired
    private EquipeRepository equipeRepository;

    // Méthode pour sauvegarder une nouvelle équipe en base de données
    public Equipe save(Equipe equipe) {
        return equipeRepository.save(equipe);
    }

    // Méthode pour récupérer toutes les équipes enregistrées en base de données
    public List<Equipe> findAll() {
        return equipeRepository.findAll();
    }

    // Méthode pour récupérer une équipe spécifique en fonction de son identifiant
    public Equipe findById(Long id) {
        return equipeRepository.findById(id).orElse(null);
    }

    // Méthode pour mettre à jour une équipe existante en base de données
    public Equipe update(Equipe equipe) {
        return equipeRepository.save(equipe);
    }

    // Méthode pour supprimer une équipe en base de données
    public void delete(Long id) {
        equipeRepository.deleteById(id);
    }
}
