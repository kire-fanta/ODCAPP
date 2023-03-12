package gestionevenements.odcEvents.repository;

import gestionevenements.odcEvents.models.Notification;
import gestionevenements.odcEvents.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository <Notification,Long> {
    List<Notification> findByUser(User user);
}
