package gestionevenements.odcEvents;

import gestionevenements.odcEvents.repository.RoleRepository;
import gestionevenements.odcEvents.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication

public class SpringBootSecurityJwtApplication implements CommandLineRunner {
	@Autowired
	PasswordEncoder encoder;
	private String password = "Fatoumata12345";
	@Autowired
	private  UserRepository UserRepository ;
	@Autowired
	private RoleRepository roleRepository;

	public static void main(String[] args) {
    SpringApplication.run(SpringBootSecurityJwtApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		if(UserRepository.findAll().size() == 0){
			UserRepository.creationadmin(encoder.encode(password));
			roleRepository.addRoleToUser();

		}
		if(roleRepository.findAll().size()==0){
			roleRepository.creationrole();
		}
	}
}
