package com.amal.reservations.repos;
import org.springframework.data.jpa.repository.JpaRepository; 
import com.amal.reservations.entities.Image; 
public interface ImageRepository extends JpaRepository<Image , Long> {

}
