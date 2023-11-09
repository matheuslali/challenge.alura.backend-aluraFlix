package br.com.alura.desafio.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name = "tb_category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String color;

    public Category(){
        this.id = 1L;
        this.title = "FREE";
        this.color = "Grey";
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String titulo) {
        this.title = titulo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String cor) {
        this.color = cor;
    }
}
