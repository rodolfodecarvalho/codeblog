package com.rodolfo.codeblog.service;

import java.util.List;
import java.util.Optional;

import com.rodolfo.codeblog.model.Post;

public interface PostService {
	List<Post> findAll();

	Optional<Post> findById(long id);

	Post save(Post post);
}