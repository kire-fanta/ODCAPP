package gestionevenements.odcEvents.MailConfig;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import gestionevenements.odcEvents.models.Tache;
import gestionevenements.odcEvents.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;


@Component
public class EmailConstructor {

	@Autowired
	private Environment env;

    @Autowired
	private TemplateEngine templateEngine;

	public MimeMessagePreparator constructAssignerTacheEmail(User utilisateur, Tache tache) {
		Context context = new Context();
		context.setVariable("utilisateur", utilisateur);
		context.setVariable("tache", tache);
		String text = templateEngine.process("AssignerTacheEmailTemplate", context);
		MimeMessagePreparator messagePreparator = new MimeMessagePreparator() {
			@Override
			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper email = new MimeMessageHelper(mimeMessage);
				email.setPriority(1);
				email.setTo(utilisateur.getEmail());
				email.setSubject("Assiner Tache sur ODC Events");
				email.setText(text, true);
				email.setFrom(new InternetAddress(env.getProperty("support.email")));
			}
		};
		return messagePreparator;
	}

	public MimeMessagePreparator constructResetPasswordEmail(User utilisateur, String password) {
		Context context = new Context();
		context.setVariable("utilisateur", utilisateur);
		context.setVariable("password", password);
		String text = templateEngine.process("resetPasswordEmailTemplate", context);
		MimeMessagePreparator messagePreparator = new MimeMessagePreparator() {
			@Override
			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper email = new MimeMessageHelper(mimeMessage);
				email.setPriority(1);
				email.setTo(utilisateur.getEmail());
				email.setSubject("Nouveau mots de passe - ODC Events");
				email.setText(text, true);
				email.setFrom(new InternetAddress(env.getProperty("support.email")));
			}
		};
		return messagePreparator;
	}

	public MimeMessagePreparator constructUpdateUserProfileEmail(User utilisateur) {
		Context context = new Context();
		context.setVariable("utilisateur", utilisateur);
		String text = templateEngine.process("updateUserProfileEmailTemplate", context);
		MimeMessagePreparator messagePreparator = new MimeMessagePreparator() {
			@Override
			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper email = new MimeMessageHelper(mimeMessage);
				email.setPriority(1);
				email.setTo(utilisateur.getEmail());
				email.setSubject("Profile Modifié - ODC Events");
				email.setText(text, true);
				email.setFrom(new InternetAddress(env.getProperty("support.email")));
			}
		};
		return messagePreparator;
	}
}
