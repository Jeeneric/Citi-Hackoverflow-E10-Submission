package com.example.Knowledge.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Knowledge.entity.Article;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {

    Optional<Article> findByArticle(String article);

    Optional<Article> findByName(String name);
}