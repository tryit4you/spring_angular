package pilab.com.takeleaf.models;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AppUser {
    
    public AppUser() {
    }

    public AppUser(Integer id, String username, String password, String email, String bio, Date createdDate,
            Set<UserRole> userRoles, List<Post> post, List<Post> likedPost) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.bio = bio;
        this.createdDate = createdDate;
        this.userRoles = userRoles;
        this.post = post;
        this.likedPost = likedPost;
    }
    private Integer id;
    private String username;
    private String password;
    private String email;
    private String bio;
    private Date createdDate;

    private Set<UserRole> userRoles=new HashSet<>();
    private List<Post> post;
    private List<Post> likedPost;
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getBio() {
        return bio;
    }
    public void setBio(String bio) {
        this.bio = bio;
    }
    public Date getCreatedDate() {
        return createdDate;
    }
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
    public Set<UserRole> getUserRoles() {
        return userRoles;
    }
    public void setUserRoles(Set<UserRole> userRoles) {
        this.userRoles = userRoles;
    }
    public List<Post> getPost() {
        return post;
    }
    public void setPost(List<Post> post) {
        this.post = post;
    }
    public List<Post> getLikedPost() {
        return likedPost;
    }
    public void setLikedPost(List<Post> likedPost) {
        this.likedPost = likedPost;
    }
}
