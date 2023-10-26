package br.com.alura.desafio.controller.dto;

import br.com.alura.desafio.domain.model.Videos;
import jakarta.validation.constraints.NotBlank;

public record VideosDto(
        @NotBlank
        Long id,
        @NotBlank
        String title,
        @NotBlank
        String description,
        @NotBlank
        String url
                        ) {
    public VideosDto(Videos video){
        this(video.getId(), video.getTitle(), video.getDescription(), video.getUrl());
    }

    public Videos toModel(){
        Videos model = new Videos();
        model.setId(this.id);
        model.setTitle(this.title);
        model.setUrl(this.url);
        model.setDescription(this.description);
        return model;
    }
}
