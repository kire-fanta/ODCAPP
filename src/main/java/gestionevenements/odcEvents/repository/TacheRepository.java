package gestionevenements.odcEvents.repository;

import gestionevenements.odcEvents.models.Tache;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TacheRepository extends JpaRepository<Tache,Long>  {
}
