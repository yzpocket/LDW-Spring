package com.citefred.ldwspring.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;

public interface PostsRepository extends JpaRepository<Posts, Long>, QuerydslPredicateExecutor<Posts>, PostRepositoryCustom {
    @Query("SELECT p FROM Posts p ORDER By p.id DESC")
    List<Posts> findAllDesc();
}
