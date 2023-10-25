package br.com.alura.desafio.controller;

import br.com.alura.desafio.domain.model.Videos;
import br.com.alura.desafio.service.VideosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/videos")
public class VideosController {

    @Autowired
    private VideosService videosService;

    @GetMapping
    public ResponseEntity<List<Videos>> findAllVideos(){
        var videos = videosService.findAll();
        return ResponseEntity.ok(videos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Videos> findVideoById(@PathVariable Long id){
        var video = videosService.findById(id);
        return ResponseEntity.ok(video);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Videos> updateVideos(@PathVariable Long id, @RequestBody Videos video){
        var videoUpdate = videosService.update(id, video);
        return ResponseEntity.ok(videoUpdate);
    }

    @PostMapping
    public ResponseEntity<Videos> createVideo(@RequestBody Videos newVideo){
        videosService.create(newVideo);
        URI localtion = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newVideo.getId())
                .toUri();
        return ResponseEntity.created(localtion).body(newVideo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVideo (@PathVariable Long id){
        videosService.findById(id);
        return ResponseEntity.noContent().build();
    }

}
