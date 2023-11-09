package br.com.alura.desafio.service.impl;

import br.com.alura.desafio.domain.model.Videos;
import br.com.alura.desafio.domain.repository.CategoryRepository;
import br.com.alura.desafio.domain.repository.VideosRepository;
import br.com.alura.desafio.service.VideosService;
import br.com.alura.desafio.service.exception.BusinessException;
import br.com.alura.desafio.service.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideosServiceImpl implements VideosService {

    private final VideosRepository videosRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public VideosServiceImpl(VideosRepository videosRepository, CategoryRepository categoryRepository){
        this.videosRepository = videosRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Videos> findAll() {
       return videosRepository.findAll();
    }

    @Override
    public Videos findById(Long id) {
        return videosRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    @Override
    public Videos create(Videos newVideo) {
        if(videosRepository.findById(newVideo.getId()).isPresent()){
            throw new BusinessException("This video id already exist");
        }if(newVideo.getCategory() == null){
            var defaultCategory = categoryRepository.findById(1L).orElse(null);
            if(defaultCategory != null){
                newVideo.setCategory(defaultCategory);
            }
        }
        return videosRepository.save(newVideo);
    }

    @Override
    public Videos update(Long id, Videos videoToUpdate) {
        Videos dbVideo = findById(id);
        if(!dbVideo.getId().equals(videoToUpdate.getId())){
            throw new BusinessException("Update ID's must be the same");
        }
        dbVideo.setTitle(videoToUpdate.getTitle());
        dbVideo.setDescription(videoToUpdate.getDescription());
        dbVideo.setUrl(videoToUpdate.getUrl());
        dbVideo.setCategory(videoToUpdate.getCategory());

        return videosRepository.save(dbVideo);
    }

    @Override
    public void delete(Long id) {
        var dbUser = findById(id);
        videosRepository.delete(dbUser);
    }


}
