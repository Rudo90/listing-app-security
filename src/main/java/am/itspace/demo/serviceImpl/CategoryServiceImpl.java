package am.itspace.demo.serviceImpl;

import am.itspace.demo.model.Category;
import am.itspace.demo.repositories.CategoryRepo;
import am.itspace.demo.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

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

    public void deleteCategoryById(Integer id){
       categoryRepo.deleteById(id);
    }



}
