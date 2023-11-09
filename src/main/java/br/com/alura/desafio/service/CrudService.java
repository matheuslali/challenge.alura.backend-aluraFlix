package br.com.alura.desafio.service;

import br.com.alura.desafio.domain.model.Videos;

import java.util.List;

public interface CrudService <ID, T>{
    List<T> findAll();
    T findById(ID id);
    T create(T entity);
    T update (ID id, T entityUpdate);
    void delete(ID id);

}
