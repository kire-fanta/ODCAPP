package gestionevenements.odcEvents.security.services;

import gestionevenements.odcEvents.models.Notification;
import gestionevenements.odcEvents.repository.NotificationRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service

@AllArgsConstructor
public class NotificationServiceImpl implements NotificationService{
    @Autowired
    private NotificationRepository NR;

    @Override
    public Notification envoyerNotification(Notification notification) {
        return NR.save(notification);
    }

    @Override
    public List<Notification> recupererNotifications() {
        return NR.findAll();
    }
}
