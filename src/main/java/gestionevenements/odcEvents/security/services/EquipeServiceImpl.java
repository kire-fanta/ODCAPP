package gestionevenements.odcEvents.security.services;

import gestionevenements.odcEvents.models.Equipe;
import gestionevenements.odcEvents.repository.EquipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EquipeServiceImpl extends EquipeService {

    @Autowired
    private EquipeRepository equipeRepository;

    @Override
    public Equipe save(Equipe equipe) {
        // Ajout de logique supplémentaire avant de sauvegarder en base de données
        // ...
        return equipeRepository.save(equipe);
    }

    @Override
    public List<Equipe> findAll() {
        // Ajout de logique supplémentaire avant de récupérer toutes les équipes en base de données
        // ...
        return equipeRepository.findAll();
    }

    @Override
    public Equipe findById(Long id) {
        // Ajout de logique supplémentaire avant de récupérer une équipe en base de données
        // ...
        return equipeRepository.findById(id).orElse(null);
    }

    @Override
    public Equipe update(Equipe equipe) {
        Optional<Equipe> equipeToUpdate = equipeRepository.findById(equipe.getIdEquipe());
        if (equipeToUpdate.isPresent()) {
            Equipe e = equipeToUpdate.get();
            e.setNom(equipe.getNom());
            e.setNomTache(equipe.getNomTache());
            e.setMembres(equipe.getMembres());
            e.setNotification(equipe.getNotification());
            e.setEvenements(equipe.getEvenements());
            return equipeRepository.save(e);
        } else {
            return null;
        }
    }


    @Override
    public void delete(Long id) {
        // Ajout de logique supplémentaire avant de supprimer une équipe en base de donné
equipeRepository.deleteById(id);
    }
}