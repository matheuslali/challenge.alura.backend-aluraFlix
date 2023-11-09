package br.com.alura.desafio.controller;

import br.com.alura.desafio.controller.dto.CategoryDto;
import br.com.alura.desafio.domain.model.Category;
import br.com.alura.desafio.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/videos")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<CategoryDto>> findAllCategory(){
        var videos = categoryService.findAll();
        var categoryDto = videos.stream().map(CategoryDto::new).toList();
        return ResponseEntity.ok(categoryDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> findCategoryById(@PathVariable Long id){
        var category = categoryService.findById(id);
        return ResponseEntity.ok(new CategoryDto(category));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryDto> updateCategory(@PathVariable Long id, @RequestBody CategoryDto category){
        var categoryUpdate = categoryService.update(id, category.toModel());
        return ResponseEntity.ok(new CategoryDto(categoryUpdate));
    }

    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody CategoryDto category){
        var newCategory = categoryService.create(category.toModel());
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newCategory.getId())
                .toUri();
        return ResponseEntity.created(location).body(newCategory);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory (@PathVariable Long id){
        categoryService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
