package gestionevenements.odcEvents.repository;

import gestionevenements.odcEvents.models.Evenements;
import gestionevenements.odcEvents.models.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EvenementsRepository extends JpaRepository <Evenements,Long> {
    List<Evenements> findAllByStatus(Status status);
}
