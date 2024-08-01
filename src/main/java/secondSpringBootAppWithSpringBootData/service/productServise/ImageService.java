package secondSpringBootAppWithSpringBootData.service.productServise;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class ImageService {
    private final String imageStoragePath = "path/to/your/image/storage";

    public String saveImage(byte[] imageBytes) throws IOException {
        String fileName = "image_" + System.currentTimeMillis() + ".jpg"; // or other logic for generating a unique file name
        Path path = Paths.get(imageStoragePath, fileName);
        Files.write(path, imageBytes);
        return path.toString(); // or a URL if you want to return a link
    }
}
