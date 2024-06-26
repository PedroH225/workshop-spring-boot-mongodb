package com.example.workshopmongo.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.workshopmongo.domain.Post;
import com.example.workshopmongo.repository.PostRepository;
import com.example.workshopmongo.service.exception.ObjectNotFoundException;

@Service
public class PostService {

    @Autowired
    private PostRepository repository;

    public Post findById(String id) {
        Optional<Post> post = repository.findById(id);
        
        if (post.isEmpty()) {
            throw new ObjectNotFoundException("Post not found!");
        }
        
        return post.get();
    }

    public List<Post> findByTitle(String text) {
        return repository.searchTitle(text);
    }

    public List<Post> fullSeach(String text, Date minDate, Date maxDate) {
        maxDate = new Date(maxDate.getTime() + 24 * 60 * 60 * 1000 - 1);
        return repository.fullSearch(text, minDate, maxDate);
    }
}
