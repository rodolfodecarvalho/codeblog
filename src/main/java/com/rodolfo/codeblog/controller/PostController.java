package com.rodolfo.codeblog.controller;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.rodolfo.codeblog.model.Post;
import com.rodolfo.codeblog.service.PostService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class PostController {

	private final PostService postService;

	@GetMapping("/")
	public String index() {
		return "index";
	}

	@GetMapping(value = "/posts")
	public ModelAndView getPosts() {
		ModelAndView mv = new ModelAndView("posts");

		List<Post> posts = postService.findAll();
		mv.addObject("posts", posts);

		return mv;
	}

	@GetMapping(value = "/{id}")
	public ModelAndView getPostDetails(@PathVariable("id") long id) {
		ModelAndView mv = new ModelAndView("postDetails");

		Post post = postService.findById(id).get();
		mv.addObject("post", post);

		return mv;
	}

	@GetMapping(value = "/newpost")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	public String getPostForm() {
		return "postForm";
	}

	@PostMapping(value = "/newpost")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	public String savePost(@Valid Post post, BindingResult result, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			attributes.addFlashAttribute("mensagem", "Verifique se os campos obrigatórios foram preenchidos!");
			return "redirect:/newpost";
		}

		post.setData(LocalDate.now());

		postService.save(post);

		return "redirect:/posts";
	}
}