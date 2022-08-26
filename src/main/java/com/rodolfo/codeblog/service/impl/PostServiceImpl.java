package com.rodolfo.codeblog.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.rodolfo.codeblog.model.Post;
import com.rodolfo.codeblog.repository.PostRepository;
import com.rodolfo.codeblog.service.PostService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

	private final PostRepository postRepository;

	@Override
	public List<Post> findAll() {
		return postRepository.findAll(sortByIdAsc());
	}

	@Override
	public Optional<Post> findById(long id) {
		return postRepository.findById(id);
	}

	@Override
	public Post save(Post post) {
		return postRepository.save(post);
	}

	private Sort sortByIdAsc() {
		return Sort.by(Sort.Direction.DESC, "id");
	}
}