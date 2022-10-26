package pilab.com.takeleaf.services.imp;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import pilab.com.takeleaf.models.AppUser;
import pilab.com.takeleaf.models.Post;
import pilab.com.takeleaf.repositories.PostRepository;
import pilab.com.takeleaf.services.FileValidationService;
import pilab.com.takeleaf.services.PostService;
import pilab.com.takeleaf.Utility.*;;

@Service
@Transactional
public class PostServiceImp implements PostService {

    @Autowired
    private PostRepository postRepo;

    @Autowired
    private FileValidationService validateService;

    @Override
    public Post savePost(AppUser appUser, HashMap<String, String> request, String postImageName) {
        String caption = request.get("caption");
        String location = request.get("location");
        Post post = new Post();
        post.setCaption(caption);
        post.setLocation(location);
        post.setUsername(appUser.getUsername());
        post.setPostedDate(new Date());
        post.setUserImageId(appUser.getId());
        appUser.setPost(post);
        return postRepo.save(post);
    }

    @Override
    public List<Post> postList() {
        return postRepo.findAll();
    }

    @Override
    public Post getPostById(Long id) {
        return postRepo.findPostById(id);
    }

    @Override
    public List<Post> findPostByUsername(String username) {
        return postRepo.findByUsername(username);
    }

    @Override
    public Post deletePost(Post post) {
        try {
            Files.deleteIfExists(Paths.get(Constants.POST_FOLDER + "/" + post.getName() + ".png"));
            return postRepo.deletePostById(post.getId());
        } catch (Exception e) {

        }
        return null;
    }

    @Override
    public String savePostImage(MultipartFile multipartFile, String fileName) {
        
        // FileValidationService validate= validateService.init(multipartFile).validate();
        // if(validate.validateResult()==false){
        //     return validate.validateDetail();
        // }
        Path root=Paths.get(Constants.POST_FOLDER);
        try {
            byte[] bytes = multipartFile.getBytes();

            Path path = Paths.get(Constants.POST_FOLDER + fileName + ".png");
            
            Files.copy(multipartFile.getInputStream(), root.resolve(fileName+".png"));
            if (Files.exists(path)) {
                Files.write(path, bytes, StandardOpenOption.CREATE);
            }
        } catch (IOException e) {
            System.out.println("Error occured. Photo not saved!");
            return "Error occured. Photo not saved!";
        }
        System.out.println("Photo saved successfully!");
        return "Photo saved successfully!";
    }

}
