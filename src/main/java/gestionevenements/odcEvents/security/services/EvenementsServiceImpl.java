package gestionevenements.odcEvents.security.services;

import gestionevenements.odcEvents.models.Evenements;
import gestionevenements.odcEvents.models.Status;
import gestionevenements.odcEvents.repository.EvenementsRepository;
import gestionevenements.odcEvents.repository.StatutRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class EvenementsServiceImpl implements EvenementsService{

    @Autowired
    private EvenementsRepository ER;
    @Autowired
    private StatutRepository statutRepository;
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


    //----------Une methode pour recuperer les evenement par status--------
    @Override
    public List<Evenements> getAllEvenementsByStatus(String status) {
        //Recuperer la liste des evenements par status
        Status status1 = statutRepository.findByStatus(status);
        List<Evenements> evenements = ER.findAllByStatus(status1);
        return evenements;
    }

    @Override
    public Evenements getEvenementById(Long idEvenement) {
        return ER.findById(idEvenement).orElse(null);
}

    @Override
    public void deleteEvenement(Long idEvenement) {
        ER.deleteById(idEvenement);
    }

    @Override
    public byte[] getImage(long id) {
        return new byte[0];
    }



}
