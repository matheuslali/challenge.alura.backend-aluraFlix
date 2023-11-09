package br.com.alura.desafio.controller.dto;

import br.com.alura.desafio.domain.model.Category;
import br.com.alura.desafio.domain.model.Videos;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CategoryDto(
        @NotNull(message = "The 'id' field is required")
        Long id,
        @NotBlank(message = "The 'title' field is required")
        String title,
        @NotBlank(message = "The 'color' field is required")
        String color
                        ) {
    public CategoryDto(Category category){
        this(category.getId(), category.getTitle(), category.getColor());
    }

    public Category toModel(){
        Category model = new Category();
        model.setId(this.id);
        model.setTitle(this.title);
        model.setColor(this.color);
        return model;
    }
}
