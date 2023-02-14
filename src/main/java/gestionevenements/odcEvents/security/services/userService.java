package gestionevenements.odcEvents.security.services;

import gestionevenements.odcEvents.models.User;

import java.util.List;

public interface userService {
    public void updateUserPassword(User user, String newPassword) ;


    List<User> getAllUtilisateurs();
}
