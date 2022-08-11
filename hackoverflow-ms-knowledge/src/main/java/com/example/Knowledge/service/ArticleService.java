package com.example.Knowledge.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.Knowledge.repository.ArticleRepository;
import com.example.Knowledge.entity.Article;
import com.example.Knowledge.exception.ArticleAlreadyExistException;

@Service
public class ArticleService {

    private ArticleRepository articleRepo;

    public ArticleService(ArticleRepository articleRepo) {
        this.articleRepo = articleRepo;
    }

    /**
     * Updates an Article object
     * 
     * @param token
     * @return updated Article
     */
    public Article update(Article article) {
        return articleRepo.save(article);
    }

    /**
     * Creates a new Article
     * 
     * @param token
     * @return newly created Article
     */
    public Article createArticle(Article article) {
        Optional<Article> search = articleRepo.findByArticle(article.getArticle());

        if (search.isPresent()) {
            throw new ArticleAlreadyExistException();
        }

        return articleRepo.save(article);
    }

    /**
     * Delete an Article
     * 
     * @param token
     * @return
     */
    public void deleteArticle(Article article) {
        Optional<Article> search = articleRepo.findByArticle(article.getArticle());

        articleRepo.delete(search.get());

    }

}
