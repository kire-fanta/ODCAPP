package gestionevenements.odcEvents;

import gestionevenements.odcEvents.models.Status;
import gestionevenements.odcEvents.models.StatusRepository;
import gestionevenements.odcEvents.repository.RoleRepository;
import gestionevenements.odcEvents.repository.UserRepository;
import gestionevenements.odcEvents.security.services.StatutService;
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
	@Autowired
	private StatusRepository statusRepository;
	@Autowired
	private StatutService statutService;

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
		if(statusRepository.findAll().size()==0){
			statutService.create(new Status(null,"encour",null));
			statutService.create(new Status(null,"termine",null));
			statutService.create(new Status(null,"avenir",null));
		}
	}
}
