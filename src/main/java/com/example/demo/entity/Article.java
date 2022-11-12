package com.example.demo.entity;


import com.example.demo.dto.ArticleDTO;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Data
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "required field")
    @Column(length = 100)
    @Size(max = 100)
    private String title;

    @NotBlank(message = "required field")
    private String author;

    @NotBlank(message = "required field")
    private String content;


    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonSerialize(using = ToStringSerializer.class)
    private LocalDateTime creationDateTime;

    public ArticleDTO getArticleDto(){

        ArticleDTO dto = new ArticleDTO();
        BeanUtils.copyProperties(this, dto);
        return dto;
    }

}
