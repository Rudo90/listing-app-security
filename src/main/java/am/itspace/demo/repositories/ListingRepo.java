package am.itspace.demo.repositories;

import am.itspace.demo.model.Category;
import am.itspace.demo.model.Listing;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ListingRepo extends JpaRepository<Listing, Integer> {

    public List<Listing> getByUserEmail(String email);
    public List<Listing> getByCategoryId (int id);
}
