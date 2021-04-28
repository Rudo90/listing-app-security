package am.itspace.demo.controller;

import am.itspace.demo.model.Listing;
import am.itspace.demo.serviceImpl.ListingServiceImpl;
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

    private final ListingServiceImpl listingServiceImpl;

    @GetMapping()
    public ResponseEntity<List<Listing>> getAllListings(){
        try {
            List<Listing> list = listingServiceImpl.getAll();
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (NoSuchElementException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/byUser/{email}")
    public ResponseEntity<List<Listing>> getByEmail (@PathVariable String email){
        try {
            List<Listing> list = listingServiceImpl.getByEmail(email);
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (NoSuchElementException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/byCategory/{categoryId}")
    public ResponseEntity<List<Listing>> getByCategoryId (@PathVariable Integer categoryId){
        try {
            List<Listing> list = listingServiceImpl.getByCategoryId(categoryId);
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (NoSuchElementException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Listing> getById (@PathVariable Integer id){
        try{
            Listing listing = listingServiceImpl.getById(id);
            return new ResponseEntity<>(listing, HttpStatus.OK);
        } catch (NoSuchElementException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping()
    public void addListing(@RequestBody Listing listing){

        listingServiceImpl.addListing(listing);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Listing> updateListing(@PathVariable Integer id, @RequestBody Listing listing){

        try{
            Listing existingListing = listingServiceImpl.getById(id);
            if (existingListing.getId().equals(id)){
                listing.setId(id);
                listingServiceImpl.addListing(listing);
            }
            return new ResponseEntity<>(listing, HttpStatus.OK);
        } catch (NoSuchElementException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteListing(@PathVariable Integer id){
        try {
            listingServiceImpl.deleteListing(id);
            String message = "Listing was deleted successfully";
            return new ResponseEntity<>(message, HttpStatus.OK);
        } catch (NoSuchElementException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
