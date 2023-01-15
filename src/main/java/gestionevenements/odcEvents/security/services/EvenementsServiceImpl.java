package gestionevenements.odcEvents.security.services;

import gestionevenements.odcEvents.models.Evenements;
import gestionevenements.odcEvents.repository.EvenementsRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class EvenementsServiceImpl implements EvenementsService{

    @Autowired
    private EvenementsRepository ER;
    @Override
    public Evenements addEvenement(Evenements evenement) {

        return  ER.save(evenement);
    }

    @Override
    public Evenements updateEvenement(Evenements evenement,Long idevent) {
        Optional<Evenements> evenements1 = ER.findById(idevent);

        if (evenements1.isPresent()) {
            Evenements evenements = ER.findById(idevent).get();
            evenements.setUser(evenements.getUser());
            evenements.setDescription(evenement.getDescription());
            evenements.setNomEvenement(evenement.getNomEvenement());
            evenements.setDate(evenement.getDate());
            evenements.setHeure(evenement.getHeure());
            evenements.setLieu(evenement.getLieu());
            evenements.setTypeEvenement(evenement.getTypeEvenement());


            ER.save(evenements);
            return evenements;
        } else {
            return null;
        }

    }

    @Override
    public void deongeteEvenement(Long idEvenement) {

    }


    @Override
    public List<Evenements> getAllEvenements() {
        return ER.findAll();
    }

    @Override
    public Evenements getEvenementById(Long idEvenement) {
        return ER.findById(idEvenement).orElse(null);
}

    @Override
    public void deleteEvenement(Long idEvenement) {
        ER.deleteById(idEvenement);
    }
}
