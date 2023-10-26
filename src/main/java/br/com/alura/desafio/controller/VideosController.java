package br.com.alura.desafio.controller;

import br.com.alura.desafio.controller.dto.VideosDto;
import br.com.alura.desafio.domain.model.Videos;
import br.com.alura.desafio.service.VideosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/videos")
public class VideosController {

    @Autowired
    private VideosService videosService;

    @GetMapping
    public ResponseEntity<List<VideosDto>> findAllVideos(){
        var videos = videosService.findAll();
        var videosDto = videos.stream().map(VideosDto::new).toList();
        return ResponseEntity.ok(videosDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VideosDto> findVideoById(@PathVariable Long id){
        var video = videosService.findById(id);
        return ResponseEntity.ok(new VideosDto(video));
    }

    @PutMapping("/{id}")
    public ResponseEntity<VideosDto> updateVideos(@PathVariable Long id, @RequestBody VideosDto video){
        var videoUpdate = videosService.update(id, video.toModel());
        return ResponseEntity.ok(new VideosDto(videoUpdate));
    }

    @PostMapping
    public ResponseEntity<Videos> createVideo(@RequestBody VideosDto video){
        var newVideo = videosService.create(video.toModel());
        URI localtion = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newVideo.getId())
                .toUri();
        return ResponseEntity.created(localtion).body(newVideo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVideo (@PathVariable Long id){
        videosService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
