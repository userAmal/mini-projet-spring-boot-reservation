package com.amal.reservations.service;
import java.io.IOException;
import java.util.List;

import org.springframework.http.ResponseEntity; 
import org.springframework.web.multipart.MultipartFile; 
import com.amal.reservations.entities.Image; 
public interface ImageService {
    Image uplaodImage(MultipartFile file) throws IOException; 
    Image getImageDetails(Long id) throws IOException; 
    ResponseEntity<byte[]> getImage(Long id) throws IOException; 
    void deleteImage(Long id) ; 
    
    Image uplaodImageRes(MultipartFile file,Long idRes) throws IOException; 
    List<Image> getImagesParRes(Long resId);
}
