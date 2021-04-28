package am.itspace.demo.serviceImpl;

import am.itspace.demo.model.Listing;
import am.itspace.demo.repositories.ListingRepo;
import am.itspace.demo.services.ListingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ListingServiceImpl implements ListingService {

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
