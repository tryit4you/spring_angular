package pilab.com.takeleaf.services;

import java.util.HashMap;
import java.util.List;


import org.springframework.web.multipart.MultipartFile;

import pilab.com.takeleaf.models.AppUser;
import pilab.com.takeleaf.models.Post;

public interface PostService {
    
    public Post savePost(AppUser appUser,HashMap<String,String> request,String postImageName);

    public List<Post> postList();
    
    public Post getPostById(Long id);

    public List<Post> findPostByUsername(String username);

    public Post deletePost(Post post);

    public String savePostImage(MultipartFile multipartFile,String fileName);
}
