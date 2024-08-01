package secondSpringBootAppWithSpringBootData.service.productServise;

import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import secondSpringBootAppWithSpringBootData.config.SupabaseConfig;
import secondSpringBootAppWithSpringBootData.entity.Product;
import secondSpringBootAppWithSpringBootData.repository.ProductRepository;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;
import java.util.UUID;

@Service
public class SupabaseService {

    @Autowired
    private SupabaseConfig supabaseConfig;

    @Autowired
    private ProductRepository productRepository;

    public String uploadImage(MultipartFile image) throws IOException {
        String originalFileName = image.getOriginalFilename();
        String uniqueFileName = UUID.randomUUID().toString() + "_" + originalFileName;
        String endpoint = String.format("%s/storage/v1/object/%s/%s",
                supabaseConfig.getSupabaseUrl(), supabaseConfig.getBucketName(), uniqueFileName);

        URL url = new URL(endpoint);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setDoOutput(true);
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Authorization", "Bearer " + supabaseConfig.getSupabaseServiceRoleSecret());
        connection.setRequestProperty("Content-Type", image.getContentType());

        try (InputStream inputStream = image.getInputStream();
             OutputStream outputStream = connection.getOutputStream()) {

            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
        }

        int responseCode = connection.getResponseCode();
        if (responseCode != HttpURLConnection.HTTP_OK) {
            throw new IOException("Failed to upload image: " + connection.getResponseMessage());
        }

        // Возвращаем URL загруженного изображения
        return String.format("%s/storage/v1/object/public/%s/%s",
                supabaseConfig.getSupabaseUrl(), supabaseConfig.getBucketName(), uniqueFileName);
    }
}
