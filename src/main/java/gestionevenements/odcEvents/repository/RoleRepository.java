package gestionevenements.odcEvents.repository;

import java.util.Optional;

import gestionevenements.odcEvents.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import gestionevenements.odcEvents.models.ERole;

import javax.transaction.Transactional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
  Optional<Role> findByName(ERole name);


  @Modifying
  @Transactional
  @Query(value = "INSERT INTO roles (name) VALUES ( \" ROLE_ADMIN \" ),( \" ROLE_USER \" );" , nativeQuery = true )
  void creationrole () ;
  @Modifying
  @Transactional
  @Query (value = "INSERT INTO user_roles (user_id,role_id) VALUES (1,1);" , nativeQuery = true )
  void addRoleToUser () ;
}
