package am.itspace.demo.services;

import am.itspace.demo.model.Category;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {

    List<Category> getAll();


    Category getCategory(Integer id);


    void addCategory(Category category);


    void deleteCategoryById(Integer id);


}
