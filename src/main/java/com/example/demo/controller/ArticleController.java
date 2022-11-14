package com.example.demo.controller;

import com.example.demo.dto.ArticleDTO;
import com.example.demo.service.ArticleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/article")
@RestController
public class ArticleController {

    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @Operation(summary = "Create Article")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ArticleDTO.class))))
    })
    @PostMapping("/create")
    public ResponseEntity save(@RequestBody @Validated ArticleDTO articleDTO) {
       articleService.save(articleDTO);
       return new ResponseEntity(HttpStatus.CREATED);
    }

    @Operation(summary = "List of Articles")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ArticleDTO.class))))
    })
    @GetMapping("/list")
    public ResponseEntity<List<ArticleDTO>> list() {
        return articleService.findAll();
    }

    @Operation(summary = "List of Articles by page")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ArticleDTO.class))))
    })
    @GetMapping("/paginatedList")
    public Page<ArticleDTO> paginatedList(Pageable pageable) {
        return articleService.findAll(pageable);
    }
    @Operation(summary = "List of Articles in the last 7 days visible only for admin")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ArticleDTO.class))))
    })
    @GetMapping("/last7Days")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Integer> findAllCreated7DaysAgo() {

        return ResponseEntity.ok(articleService.findAllByCreationDateTimeIsAfter().size());
    }

}
