package br.com.alura.desafio.service.impl;

import br.com.alura.desafio.domain.model.Category;
import br.com.alura.desafio.domain.repository.CategoryRepository;
import br.com.alura.desafio.service.CategoryService;
import br.com.alura.desafio.service.exception.BusinessException;
import br.com.alura.desafio.service.exception.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    public CategoryServiceImpl(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }
    @Override
    public List<Category> findAll() {
       return categoryRepository.findAll();
    }

    @Override
    public Category findById(Long id) {
        return categoryRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    @Override
    public Category create(Category newCategory) {
        if(newCategory.getId().equals(categoryRepository.findById(newCategory.getId()))){
            throw new BusinessException("This video id already exist");
        }
        return categoryRepository.save(newCategory);
    }

    @Override
    public Category update(Long id, Category categoryToUpdate) {
        Category dbCategory = findById(id);
        if(!dbCategory.getId().equals(categoryToUpdate.getId())){
            throw new BusinessException("Update ID's must be the same");
        }
        dbCategory.setTitle(categoryToUpdate.getTitle());
        dbCategory.setColor(categoryToUpdate.getColor());

        return categoryRepository.save(dbCategory);
    }

    @Override
    public void delete(Long id) {
        var dbCategory = findById(id);
        categoryRepository.delete(dbCategory);
    }


}
