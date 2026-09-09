package com.devteria.post.service;

import com.devteria.post.dto.request.PostRequest;
import com.devteria.post.dto.response.PostResponse;
import com.devteria.post.entity.Post;
import com.devteria.post.mapper.PostMapper;
import com.devteria.post.repository.PostRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.net.Authenticator;
import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PostService {
    PostRepository postRepository;
    PostMapper postMapper;
    public PostResponse createPost(PostRequest request){
        Authentication authenticator = SecurityContextHolder.getContext().getAuthentication();
        Post post = Post.builder()
                .userId(authenticator.getName())
                .content(request.getContent())
                .createdDate(Instant.now())
                .modifiedDate(Instant.now())
                .content(request.getContent())
                .build();
        postRepository.save(post);
        return postMapper.toPostResponse(post);
    }

    public List<PostResponse> getMyPost(PostRequest request) {
        Authentication authenticator = SecurityContextHolder.getContext().getAuthentication();
        List<Post> posts = postRepository.findAllByUserId(authenticator.getName());
        return posts.stream().map(postMapper::toPostResponse).toList();
    }
}
