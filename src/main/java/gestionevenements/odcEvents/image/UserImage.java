package gestionevenements.odcEvents.image;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
@Component
public class UserImage {
    public static final String USER_FOLDER = "src/main/resources/static/images/user//";

    public String saveUserImage(MultipartFile multipartFile, Long userImageId) {
        byte[] bytes;
        try {
            Files.deleteIfExists(Paths.get(USER_FOLDER + "/" + userImageId + ".png"));
            bytes = multipartFile.getBytes();
            Path path = Paths.get(USER_FOLDER + userImageId + ".png");
            Files.write(path, bytes);
            return "User picture saved to server";
        } catch (IOException e) {
            return "User picture Saved";
        }
    }
}
