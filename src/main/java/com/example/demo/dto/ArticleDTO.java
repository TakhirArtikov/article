package com.example.demo.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class ArticleDTO {

    private Long id;

    @NotBlank(message = "required field")
    @Size(max = 100)
    private String title;

    @NotBlank(message = "required field")
    private String author;

    @NotBlank(message = "required field")
    private String content;


    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonSerialize(using = ToStringSerializer.class)
    private LocalDateTime creationDateTime;
}