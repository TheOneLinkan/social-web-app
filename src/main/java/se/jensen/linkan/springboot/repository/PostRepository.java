package se.jensen.linkan.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.jensen.linkan.springboot.model.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
    Long id(long id);
}
