package com.rodolfo.codeblog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rodolfo.codeblog.model.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

}