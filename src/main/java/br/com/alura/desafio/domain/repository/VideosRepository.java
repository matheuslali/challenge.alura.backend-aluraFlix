package br.com.alura.desafio.domain.repository;

import br.com.alura.desafio.domain.model.Videos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideosRepository extends JpaRepository<Videos, Long> {
}
