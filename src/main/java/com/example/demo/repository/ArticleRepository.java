package com.example.demo.repository;

import com.example.demo.entity.Article;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ArticleRepository extends PagingAndSortingRepository<Article,Long> {

    List<Article> findAllByCreationDateTimeIsAfter(LocalDateTime dateTime);

}
