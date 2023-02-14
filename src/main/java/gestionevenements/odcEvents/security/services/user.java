package gestionevenements.odcEvents.security.services;

import gestionevenements.odcEvents.MailConfig.EmailConstructor;
import gestionevenements.odcEvents.models.User;
import gestionevenements.odcEvents.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class user implements userService{

    @Autowired
    private UserRepository userRepository;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private EmailConstructor emailConstructor;

    public void updateUserPassword(User user, String newpassword) {
        String encryptedPassword = bCryptPasswordEncoder.encode(newpassword);
        user.setPassword(encryptedPassword);
        userRepository.save(user);
        mailSender.send(emailConstructor.constructResetPasswordEmail(user, newpassword));
    }

    @Override
    public List<User> getAllUtilisateurs() {
        return  userRepository.findAll();
    }





}
