package com.amal.reservations.restcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.amal.reservations.entities.Image;
import com.amal.reservations.entities.Reservation;
import com.amal.reservations.service.ImageService;
import com.amal.reservations.service.ReservationService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/api/image")
@CrossOrigin(origins = "*")
public class ImageRestController {
	@Autowired
	ImageService imageService;
	@Autowired
	ReservationService reservationService;
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public Image uploadImage(@RequestParam("image") MultipartFile file) throws IOException {
		return imageService.uplaodImage(file);
	}

	@RequestMapping(value = "/get/info/{id}", method = RequestMethod.GET)
	public Image getImageDetails(@PathVariable("id") Long id) throws IOException {
		return imageService.getImageDetails(id);
	}

	@RequestMapping(value = "/load/{id}", method = RequestMethod.GET)
	public ResponseEntity<byte[]> getImage(@PathVariable("id") Long id) throws IOException {
		return imageService.getImage(id);
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public void deleteImage(@PathVariable("id") Long id) {
		imageService.deleteImage(id);
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public Image UpdateImage(@RequestParam("image") MultipartFile file) throws IOException {
		return imageService.uplaodImage(file);
	}

	@PostMapping(value = "/uplaodImageRes/{idRes}")
	public Image uploadMultiImages(@RequestParam("image") MultipartFile file, @PathVariable("idRes") Long idRes)
			throws IOException {
		return imageService.uplaodImageRes(file, idRes);
	}

	@RequestMapping(value = "/getImagesRes/{idRes}", method = RequestMethod.GET)
	public List<Image> getImagesRes(@PathVariable("idRes") Long idRes) throws IOException {
		return imageService.getImagesParRes(idRes);
	}
	@RequestMapping(value = "/uploadFS/{id}" , method = RequestMethod.POST) 
    public void uploadImageFS(@RequestParam("image") MultipartFile 
file,@PathVariable("id") Long id) throws IOException { 
     Reservation p = reservationService.getReservation(id); 
     p.setImagePath(id+".jpg"); 
    
Files.write(Paths.get(System.getProperty("user.home")+"/images/"+p.getImagePath())
 , file.getBytes()); 
reservationService.saveReservation(p); 
   
    } 
 
  @RequestMapping(value = "/loadfromFS/{id}" ,  
       method = RequestMethod.GET, 
    produces = org.springframework.http.MediaType.IMAGE_JPEG_VALUE) 
    public byte[] getImageFS(@PathVariable("id") Long id) throws IOException { 
       
	  Reservation p = reservationService.getReservation(id); 
     return 
Files.readAllBytes(Paths.get(System.getProperty("user.home")+"/images/"+p.getImagePath())); 
    }
}
