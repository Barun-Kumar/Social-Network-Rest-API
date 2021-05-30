package com.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.model.Post;
import com.app.repository.PostRepository;

@Service
public class PostService {
	@Autowired
	PostRepository postRepository;
	public void createPost(Post post) {
		postRepository.save(post);
	}
}
