package gestionevenements.odcEvents.repository;

import gestionevenements.odcEvents.models.Status;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatutRepository extends JpaRepository<Status,Long> {
}
