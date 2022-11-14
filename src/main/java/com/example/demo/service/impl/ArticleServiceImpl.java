package com.example.demo.service.impl;

import com.example.demo.dto.ArticleDTO;
import com.example.demo.entity.Article;
import com.example.demo.repository.ArticleRepository;
import com.example.demo.service.ArticleService;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {
    private final ArticleRepository repository;

    public ArticleServiceImpl(ArticleRepository repository) {
        this.repository = repository;
    }

    @Override
    public void save(ArticleDTO dto) {
        if (dto.getCreationDateTime() == null)
            dto.setCreationDateTime(LocalDateTime.now());
        Article article=new Article();
        BeanUtils.copyProperties(dto,article);
        repository.save(article);
    }

    @Override
    public ResponseEntity<List<ArticleDTO>> findAll() {
        List<Article> articles= (List<Article>) repository.findAll();
        List<ArticleDTO> dtos=new ArrayList<>();
        articles.forEach(article -> {
            ArticleDTO dto=new ArticleDTO();
            BeanUtils.copyProperties(article,dto);
            dtos.add(dto);
        });
        return ResponseEntity.ok(dtos);
    }

    @Override
    public Page<ArticleDTO> findAll(Pageable pageable) {
        Page<Article> entityPage = repository.findAll(pageable);
        List<ArticleDTO> dtoList = entityPage
                .stream()
                .map(Article::getArticleDto)
                .toList();
        return new PageImpl<>(dtoList, pageable, entityPage.getTotalElements());
    }

    @Override
    public List<Article> findAllByCreationDateTimeIsAfter() {
        LocalDateTime sevenDaysAgo = LocalDateTime.now().minusDays(7);
        return repository.findAllByCreationDateTimeIsAfter(sevenDaysAgo);
    }
}
