package com.example.demo.service.impl;

import com.example.demo.dto.ArticleDTO;
import com.example.demo.entity.Article;
import com.example.demo.repository.ArticleRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDateTime;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)
class ArticleServiceImplTest {

    @Mock
    private ArticleRepository repository;

    @InjectMocks
    private ArticleServiceImpl articleService;

    @Test
    void save() {
        ArticleDTO articleDTO = new ArticleDTO();
        articleDTO.setAuthor("Author");
        articleDTO.setContent("Content");
        articleDTO.setTitle("Title");
        articleDTO.setId(1L);
        articleDTO.setCreationDateTime(LocalDateTime.of(2015, Month.JULY,19,19,30,40));

        Article article = new Article();
        article.setAuthor("Author");
        article.setContent("Content");
        article.setTitle("Title");
        article.setId(1L);
        article.setCreationDateTime(LocalDateTime.of(2015, Month.JULY,19,19,30,40));

        Mockito.when(repository.save(ArgumentMatchers.any(Article.class))).thenReturn(article);

        articleService.save(articleDTO);
        Mockito.verify(repository).save(article);
    }

    @Test
    void findAll() {
    }

    @Test
    void testFindAll() {
    }

    @Test
    void findAllByCreationDateTimeIsAfter() {
    }
}