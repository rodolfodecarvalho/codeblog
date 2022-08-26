package com.rodolfo.codeblog.utils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.rodolfo.codeblog.model.Post;
import com.rodolfo.codeblog.repository.PostRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DummyData implements CommandLineRunner {

	private final PostRepository postRepository;

	@Override
	public void run(String... args) throws Exception {
		List<Post> posts = postRepository.findAll();

		if (posts.isEmpty()) {
			List<Post> postList = new ArrayList<>();
			Post post1 = new Post();
			post1.setAutor("Bruno Alexandre");
			post1.setData(LocalDate.now());
			post1.setTitulo("Docker");
			post1.setTexto(
					"Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.");

			Post post2 = new Post();
			post2.setAutor("Michelli Brito");
			post2.setData(LocalDate.now());
			post2.setTitulo("API REST");
			post2.setTexto(
					"Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.");

			postList.add(post1);
			postList.add(post2);

			postList.forEach(post -> postRepository.save(post));
		}
	}
}