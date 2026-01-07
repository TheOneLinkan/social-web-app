package se.jensen.linkan.springboot.service;

import org.springframework.stereotype.Service;
import se.jensen.linkan.springboot.dto.PostRequestDTO;
import se.jensen.linkan.springboot.dto.PostResponseDTO;
import se.jensen.linkan.springboot.exception.PostNotFoundException;
import se.jensen.linkan.springboot.mapper.PostMapper;
import se.jensen.linkan.springboot.model.Post;
import se.jensen.linkan.springboot.repository.PostRepository;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final PostMapper postMapper;

    public PostService(PostRepository postRepository, PostMapper postMapper) {
        this.postRepository = postRepository;
        this.postMapper = postMapper;
    }

    public PostResponseDTO updatePost(Long postId, PostRequestDTO postDto) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> PostNotFoundException.forId(postId));

        postMapper.updateEntity(post, postDto);

        Post savedPost = postRepository.save(post);
        return postMapper.toResponse(savedPost);
    }

    public void deletePost(Long postId) {
        if (!postRepository.existsById(postId)) {
            throw PostNotFoundException.forId(postId);
        }
        postRepository.deleteById(postId);
    }
}
