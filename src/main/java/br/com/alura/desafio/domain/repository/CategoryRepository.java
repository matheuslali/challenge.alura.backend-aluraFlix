package br.com.alura.desafio.domain.repository;

import br.com.alura.desafio.domain.model.Category;
import br.com.alura.desafio.domain.model.Videos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
