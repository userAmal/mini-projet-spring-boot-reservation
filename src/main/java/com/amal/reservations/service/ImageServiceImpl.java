package com.amal.reservations.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.amal.reservations.entities.Image;
import com.amal.reservations.entities.Reservation;
import com.amal.reservations.repos.ImageRepository;
import com.amal.reservations.repos.ReservationRepository;

@Service // Ajoutez cette annotation
public class ImageServiceImpl implements ImageService {
    @Autowired 
    ImageRepository imageRepository; 

    @Autowired 
    ReservationService reservationService;
    
    @Autowired
    ReservationRepository reservationRepository;
    @Override 
    public Image uplaodImage(MultipartFile file) throws IOException { 
        return imageRepository.save(Image.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .image(file.getBytes())
                .build());
    }

    @Override 
    public Image getImageDetails(Long id) throws IOException { 
        final Optional<Image> dbImage = imageRepository.findById(id);
        return Image.builder()
                .idImage(dbImage.get().getIdImage())
                .name(dbImage.get().getName())
                .type(dbImage.get().getType())
                .image(dbImage.get().getImage())
                .build();
    }

    @Override 
    public ResponseEntity<byte[]> getImage(Long id) throws IOException { 
        final Optional<Image> dbImage = imageRepository.findById(id);
        return ResponseEntity
                .ok()
                .contentType(MediaType.valueOf(dbImage.get().getType()))
                .body(dbImage.get().getImage());
    }

    @Override 
    public void deleteImage(Long id) { 
        imageRepository.deleteById(id);
    }

	@Override
	public Image uplaodImageRes(MultipartFile file, Long idRes) throws IOException {
		 Reservation r = new Reservation(); 
		  r.setIdReservation(idRes); 
		  return imageRepository.save(Image.builder() 
		                .name(file.getOriginalFilename()) 
		                .type(file.getContentType()) 
		                .image(file.getBytes()) 
		                .reservation(r).build() ); 
	}

	@Override
	public List<Image> getImagesParRes(Long resId) {
		Reservation r = reservationRepository.findById(resId).get(); 
		  return r.getImages(); 
	}
}
