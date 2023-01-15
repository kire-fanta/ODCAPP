package gestionevenements.odcEvents.repository;

import gestionevenements.odcEvents.models.Evenements;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EvenementsRepository extends JpaRepository <Evenements,Long> {
}
