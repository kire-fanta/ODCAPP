package gestionevenements.odcEvents.repository;

import java.util.Optional;

import gestionevenements.odcEvents.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gestionevenements.odcEvents.models.ERole;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
  Optional<Role> findByName(ERole name);
}
