package pilab.com.takeleaf.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pilab.com.takeleaf.models.Post;

public interface PostRepository extends JpaRepository<Post,Long>{
    
    @Query("SELECT p FROM Post order by p.postedDate DESC")
    public List<Post> findAll();

    @Query("SELECT p FROM Post p WHERE p.username = :username ORDER BY p.postedDate DESC")
    public List<Post> findByUsername(@Param("username") String username);

    @Query("SELECT p FROM Post p WHERE p.id=:id")
    public Post findPostById(@Param("id") Long id);
    
    @Query("DELETE Post p WHERE p.id=:id")
    public Post deletePostById(@Param("id") Long id);
}
