package gestionevenements.odcEvents.repository;

import gestionevenements.odcEvents.models.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository <Notification,Long> {
}
