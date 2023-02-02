package gestionevenements.odcEvents.repository;

import gestionevenements.odcEvents.models.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StatutRepository extends JpaRepository<Status,Long> {
    Status findByStatus(String status);
}
