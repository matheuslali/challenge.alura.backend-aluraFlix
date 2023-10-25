package br.com.alura.desafio.service;

import br.com.alura.desafio.domain.model.Videos;

import java.util.List;

public interface VideosService {
    List<Videos> findAll();
    Videos findById(Long id);
    Videos create(Videos newVideo);
    Videos update (Long id, Videos videoToUpdate);
    void delete(Long id);

}
