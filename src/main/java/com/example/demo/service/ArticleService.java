package com.example.demo.service;

import com.example.demo.dto.ArticleDTO;
import com.example.demo.entity.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.List;

public interface ArticleService {

    void save(ArticleDTO dto);

    ResponseEntity<List<ArticleDTO>> findAll();

    Page<Article> findAll(Pageable pageable);

    List<Article> findAllByCreationDateTimeIsAfter(LocalDateTime creationDateTime);
}
