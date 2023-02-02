package gestionevenements.odcEvents.controllers;

import gestionevenements.odcEvents.models.Notification;
import gestionevenements.odcEvents.security.services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:8100",allowCredentials = "true")

@RestController
@RequestMapping("/notifications")
public class NotificationController {
    @Autowired
    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PostMapping("/add")
    public Notification envoyerNotification(@RequestBody Notification notification) {
        return notificationService.envoyerNotification(notification);
    }

    @GetMapping("/all")
    public List<Notification> recupererNotifications() {
        return notificationService.recupererNotifications();
    }
}
