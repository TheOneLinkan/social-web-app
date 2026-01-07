package se.jensen.linkan.springboot.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import se.jensen.linkan.springboot.dto.*;
import se.jensen.linkan.springboot.exception.UserNotFoundException;
import se.jensen.linkan.springboot.mapper.PostMapper;
import se.jensen.linkan.springboot.mapper.UserMapper;
import se.jensen.linkan.springboot.model.Post;
import se.jensen.linkan.springboot.model.User;
import se.jensen.linkan.springboot.repository.PostRepository;
import se.jensen.linkan.springboot.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final UserMapper userMapper;
    private final PostMapper postMapper;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, UserMapper userMapper,
                       PostMapper postMapper, PostRepository postRepository,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
        this.userMapper = userMapper;
        this.postMapper = postMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public List<UserResponseDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::toResponse)
                .toList();
    }

    public UserResponseDTO createUser(UserRequestDTO userDto) {
        User user = userMapper.toEntity(userDto);
        String hashedPassword = passwordEncoder.encode(userDto.password());
        user.setPassword(hashedPassword);
        User saved = userRepository.save(user);
        return userMapper.toResponse(saved);
    }

    public UserResponseDTO getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> UserNotFoundException.forId(id));

        return userMapper.toResponse(user);
    }

    public UserResponseDTO updateUser(Long id, UserRequestDTO dto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> UserNotFoundException.forId(id));

        userMapper.updateEntity(user, dto);

        User saved = userRepository.save(user);
        return userMapper.toResponse(saved);
    }

    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw UserNotFoundException.forId(id);
        }
        userRepository.deleteById(id);
    }

    public PostResponseDTO createPost(Long id, PostRequestDTO postDto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> UserNotFoundException.forId(id));

        Post post = new Post();
        post.setText(postDto.text());
        post.setCreatedAt(LocalDateTime.now());
        post.setUser(user);

        Post savedPost = postRepository.save(post);
        return postMapper.toResponse(savedPost);
    }

    public UserWithPostsResponseDTO getUserWithPosts(Long id) {
        User user = userRepository.findUserWithPosts(id)
                .orElseThrow(() -> UserNotFoundException.forId(id));

        List<PostResponseDTO> postsDto = user.getPosts()
                .stream()
                .map(postMapper::toResponse)
                .toList();

        UserResponseDTO userDto = userMapper.toResponse(user);

        return new UserWithPostsResponseDTO(userDto, postsDto);
    }

    public UserResponseDTO getUserByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("Användare med username " + username + " hittades inte."));
        return userMapper.toResponse(user);
    }
}
