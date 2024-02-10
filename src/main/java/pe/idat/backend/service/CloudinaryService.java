package pe.idat.backend.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

@Service
public class CloudinaryService {
    Cloudinary cloudinary;

    public CloudinaryService() {
        Map<String, String> valuesMap = new HashMap<>();
        valuesMap.put("cloud_name", "dynsqtcbm");
        valuesMap.put("api_key", "664778685461919");
        valuesMap.put("api_secret", "l6-ikt0BWCzOHSs9XG8JbV2fkXo");
        cloudinary = new Cloudinary(valuesMap);
    }

    public Map upload(MultipartFile multipartFile) throws IOException {
        File file = convert(multipartFile);
        try {
            Map result = cloudinary.uploader().upload(file, ObjectUtils.emptyMap());
            return result;
        } finally {
            if (!Files.deleteIfExists(file.toPath())) {
                throw new IOException("Error al eliminar el archivo temporal: " + file.getAbsolutePath());
            }
        }
    }
    
    private File convert(MultipartFile multipartFile) throws IOException {
        File file = new File(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        try (FileOutputStream fo = new FileOutputStream(file)) {
            fo.write(multipartFile.getBytes());
        }
        file.deleteOnExit();
        return file;
    }
}
