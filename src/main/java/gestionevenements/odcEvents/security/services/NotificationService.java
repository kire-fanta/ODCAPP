package gestionevenements.odcEvents.security.services;



import gestionevenements.odcEvents.models.Notification;

import java.util.List;

public interface NotificationService {
    Notification envoyerNotification(Notification notification);
    List<Notification> recupererNotifications();
}
