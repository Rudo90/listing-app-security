package am.itspace.demo.controller;

import am.itspace.demo.model.Category;
import am.itspace.demo.model.Listing;
import am.itspace.demo.model.User;
import am.itspace.demo.service.ListingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/listings")
public class ListingController {

    private final ListingService listingService;

    @GetMapping()
    public ResponseEntity<List<Listing>> getAllListings(){
        try {
            List<Listing> list = listingService.getAll();
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (NoSuchElementException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/byUser/{email}")
    public ResponseEntity<List<Listing>> getByEmail (@PathVariable String email){
        try {
            List<Listing> list = listingService.getByEmail(email);
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (NoSuchElementException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/byCategory/{categoryId}")
    public ResponseEntity<List<Listing>> getByCategoryId (@PathVariable Integer categoryId){
        try {
            List<Listing> list = listingService.getByCategoryId(categoryId);
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (NoSuchElementException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Listing> getById (@PathVariable Integer id){
        try{
            Listing listing = listingService.getById(id);
            return new ResponseEntity<>(listing, HttpStatus.OK);
        } catch (NoSuchElementException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping()
    public void addListing(Listing listing){

        listingService.addListing(listing);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Listing> updateListing(@RequestBody Listing listing, @PathVariable Integer id,
                                                 @RequestBody Category category, @RequestBody User user) {
        try {
            Listing existingListing = listingService.getById(id);
            if (existingListing.getId().equals(id)){
                listing.setId(id);
                listing.setCategory(category);
                listing.setUser(user);
                listingService.addListing(listing);
            }
            return new ResponseEntity<>(listing, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteListing(@PathVariable Integer id){
        try {
            listingService.deleteListing(id);
            String message = "Listing was deleted successfully";
            return new ResponseEntity<>(message, HttpStatus.OK);
        } catch (NoSuchElementException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
