package gestionevenements.odcEvents.security.services;

import gestionevenements.odcEvents.models.Salles;
import gestionevenements.odcEvents.repository.SalleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SalleServiceImpl implements SalleService{
    @Autowired
    SalleRepository salleRepository;

    @Override
    public Salles create(Salles salles) {

        return salleRepository.save(salles);
    }

    @Override
    public Salles read(long id) {
        return salleRepository.findById(id).get();
    }

    @Override
    public Salles update(Salles salle, long id) {
        Optional<Salles> salleOptional = salleRepository.findById(id);

        if (salleOptional.isPresent()) {
            Salles salleToUpdate = salleOptional.get();
            salleToUpdate.setNombreplace(salle.getNombreplace());
            salleToUpdate.setLibelle(salle.getLibelle());
            salleToUpdate.setEtage(salle.getEtage());
            salleToUpdate.setDisponibilite(salle.getDisponibilite());
            salleToUpdate.setDescription(salle.getDescription());

            return salleRepository.save(salleToUpdate);
        } else {
            return null;
        }
    }




    @Override
    public void delete(long id) {
        salleRepository.deleteById(id);
    }

    @Override
    public List<Salles> getAll() {
        return salleRepository.findAll();
    }

    @Override
    public Salles getByLibelle(String libelle) {
        return salleRepository.findByLibelle(libelle);
    }

    @Override
    public Salles getByIdsalle(long id) {
        return salleRepository.findById(id).get();
    }

    @Override
    public List<Salles> ParEtat(Boolean disponibilite) {
        // TODO Auto-generated method stub
        return salleRepository.findByDisponibilite(disponibilite);

    }

    @Override
    public byte[] getImage(long id) {
        return new byte[0];
    }
}
