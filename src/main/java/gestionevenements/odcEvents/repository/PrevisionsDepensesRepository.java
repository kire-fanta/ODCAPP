package gestionevenements.odcEvents.repository;

import gestionevenements.odcEvents.models.Evenements;
import gestionevenements.odcEvents.models.PrevisionsDepenses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrevisionsDepensesRepository extends JpaRepository<PrevisionsDepenses,Long> {
    List<PrevisionsDepenses> findByEvenements(Evenements evenements);
}
