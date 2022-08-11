package com.example.Knowledge.controller;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import com.example.Knowledge.repository.ArticleRepository;
import com.example.Knowledge.DTO.ArticleDTO;
import com.example.Knowledge.DTO.ArticleNameDTO;
import com.example.Knowledge.entity.Article;
import com.example.Knowledge.service.ArticleService;
import com.example.Knowledge.exception.ArticleDoesNotExistException;

@CrossOrigin
@RestController
public class ArticleController {

    private ArticleRepository articleRepo;

    private ArticleService articleService;

    public ArticleController(ArticleRepository articleRepo, ArticleService articleService) {
        this.articleRepo = articleRepo;
        this.articleService = articleService;
    }

    @ResponseStatus(HttpStatus.OK)
    @CrossOrigin
    @RequestMapping(value = "/get-article", method = RequestMethod.POST)
    public ArticleDTO getArticle(@RequestBody ArticleNameDTO articleNameDTO, HttpServletRequest request,
            HttpServletResponse response) {

        Optional<Article> search = articleRepo.findByName(articleNameDTO.getName());

        if (!search.isPresent()) {
            throw new ArticleDoesNotExistException();
        }

        return new ArticleDTO(search.get().getName(), search.get().getDate(), search.get().getArticle(),
                search.get().getImage());
    }

    @ResponseStatus(HttpStatus.OK)
    @CrossOrigin
    @RequestMapping(value = "/get-all-article", method = RequestMethod.POST)
    public List<Article> getAllArticle(HttpServletRequest request,
            HttpServletResponse response) {

        List<Article> allArticles = articleRepo.findAll();
        return allArticles;
    }

    @ResponseStatus(HttpStatus.OK)
    @CrossOrigin
    @RequestMapping(value = "/delete-article", method = RequestMethod.DELETE)
    public void deleteArticle(@RequestBody ArticleNameDTO articleNameDTO, HttpServletRequest request,
            HttpServletResponse response) {

        Optional<Article> search = articleRepo.findByName(articleNameDTO.getName());

        if (!search.isPresent()) {
            throw new ArticleDoesNotExistException();
        }

        articleService.deleteArticle(search.get());
    }

    @ResponseStatus(HttpStatus.OK)
    @CrossOrigin
    @RequestMapping(value = "/create-article", method = RequestMethod.POST)
    public void createArticle(@RequestBody ArticleDTO articleDTO, HttpServletRequest request,
            HttpServletResponse response) {

        Article newArticle = new Article();

        newArticle.setName(articleDTO.getName());
        newArticle.setDate(articleDTO.getDate());
        newArticle.setArticle(articleDTO.getArticle());
        newArticle.setImage(articleDTO.getImage());

        articleService.createArticle(newArticle);
    }

}
