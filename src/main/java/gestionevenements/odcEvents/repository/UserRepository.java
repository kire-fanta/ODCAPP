package gestionevenements.odcEvents.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import gestionevenements.odcEvents.models.User;

import javax.transaction.Transactional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findByUsername(String username);

  Boolean existsByUsername(String username);

  Boolean existsByEmail(String email);

    User findByEmail(String email);
   // List<User> findByEmail(List<String> email);
   @Modifying
   @Transactional
   @Query(value = "INSERT INTO users (nom ,prenom,username,email,password)" +
           "  VALUES ('Kaloga', 'Fatoumata', 'Fanta', 'kirefanta@gmail.com', :password);",
           nativeQuery = true)
   void creationadmin(String password);

}
