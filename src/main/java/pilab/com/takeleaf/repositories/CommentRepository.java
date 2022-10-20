package pilab.com.takeleaf.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import pilab.com.takeleaf.models.Comment;

public interface CommentRepository extends JpaRepository<Comment,Long> {
    
}
