package com.sda.controlles;
import com.sda.entity.Rental;
import com.sda.repository.RentalRepository;
import com.sda.service.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/rentals")
public class RentalController {
    @Autowired
    private RentalService rentalService;
    @Autowired
    private RentalRepository rentalRepository;
    @PostMapping("/rental/createRental")
    public Rental createRental(@RequestBody Rental rental){
        return rentalService.createRental(rental);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Rental> updateRental(@PathVariable Long id, @RequestBody Rental rentalDetails) {
        try {
            Rental updatedRental = rentalService.updateRental(id, rentalDetails);
            return ResponseEntity.ok(updatedRental);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/api/rentals/{id}")
    public void deleteRental(@PathVariable Long id){
       rentalRepository.deleteById(id);
    }
}