package am.itspace.demo.services;

import am.itspace.demo.model.Listing;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ListingService {

    List<Listing> getAll();

    List<Listing> getByEmail (String email);

    List<Listing> getByCategoryId(Integer id);

    Listing getById(Integer id);

    void addListing(Listing listing);

    void deleteListing(Integer id);
}
