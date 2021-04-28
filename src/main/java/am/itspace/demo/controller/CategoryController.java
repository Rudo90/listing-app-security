package am.itspace.demo.controller;

import am.itspace.demo.model.Category;
import am.itspace.demo.serviceImpl.CategoryServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryServiceImpl categoryServiceImpl;

    @GetMapping()
    public ResponseEntity<List<Category>> getAllCategories(){
        try{
          List<Category> list = categoryServiceImpl.getAll();
           return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (NoSuchElementException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategory(@PathVariable Integer id){

        try {
            Category category  = categoryServiceImpl.getCategory(id);
            return new ResponseEntity<>(category, HttpStatus.OK);
        } catch (NoSuchElementException e){
           return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping()
    public void addCategory(@RequestBody Category category){
        categoryServiceImpl.addCategory(category);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategory(@RequestBody Category category, @PathVariable Integer id){
        try{
            Category existingCategory = categoryServiceImpl.getCategory(id);
            if (existingCategory.getId().equals(id)){
                category.setId(id);
                categoryServiceImpl.addCategory(category);
            }
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable Integer id){
        try{
            categoryServiceImpl.deleteCategoryById(id);
            String message = "successfully deleted!";
            return new ResponseEntity<>(message, HttpStatus.OK);
        } catch (NoSuchElementException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
