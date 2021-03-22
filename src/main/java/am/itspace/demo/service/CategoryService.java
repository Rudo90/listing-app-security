package am.itspace.demo.service;

import am.itspace.demo.model.Category;
import am.itspace.demo.repositories.CategoryRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepo categoryRepo;


    public List<Category> getAll(){
       return categoryRepo.findAll();
    }

    public Category getCategory (Integer id){
        return categoryRepo.findById(id).get();
    }

    public void addCategory(Category category){
        categoryRepo.save(category);
    }

    public void deleteCategoryById(int id){
       categoryRepo.deleteById(id);
    }



}
