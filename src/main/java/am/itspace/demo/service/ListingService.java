package am.itspace.demo.service;

import am.itspace.demo.model.Category;
import am.itspace.demo.model.Listing;
import am.itspace.demo.model.User;
import am.itspace.demo.repositories.ListingRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ListingService {

    private final ListingRepo listingRepo;

    public List<Listing> getAll(){
        return listingRepo.findAll();
    }

    public List<Listing> getByEmail (String email){
       return listingRepo.getByUserEmail(email);
    }

    public List<Listing> getByCategoryId(Integer id){
       return listingRepo.getByCategoryId(id);
    }

    public Listing getById(Integer id){
        return listingRepo.findById(id).get();
    }

    public void addListing(Listing listing){
        listingRepo.save(listing);
    }

    public void deleteListing(Integer id){
        listingRepo.deleteById(id);
    }



}
