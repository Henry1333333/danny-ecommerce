package pe.idat.backend.controller;

import java.io.IOException;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import pe.idat.backend.model.response.CloudinaryResponse;
import pe.idat.backend.service.CloudinaryService;



@RestController
@RequestMapping("/cloudinary")
@CrossOrigin(origins = "http://localhost:4200")
public class CloudinaryController {
    @Autowired
    CloudinaryService cloudinaryService;

    @PostMapping("/upload")
    @ResponseBody
    public ResponseEntity<CloudinaryResponse> upload(MultipartFile multipartFile) throws IOException {
        Map result = cloudinaryService.upload(multipartFile);
        String imageUrl = (String) result.get("url");
        CloudinaryResponse cloudinaryResponse = new CloudinaryResponse(imageUrl);
        return new ResponseEntity<>(cloudinaryResponse, HttpStatus.OK);
    }
}
