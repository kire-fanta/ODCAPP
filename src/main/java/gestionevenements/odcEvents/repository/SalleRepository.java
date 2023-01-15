package gestionevenements.odcEvents.repository;

import gestionevenements.odcEvents.models.Salles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SalleRepository extends JpaRepository<Salles,Long>  {
    List<Salles> findByDisponibilite(Boolean disponibilite);

    Salles findByLibelle(String libelle);
}
