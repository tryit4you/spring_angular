package pilab.com.takeleaf.services;

import java.util.HashMap;
import java.util.List;


import org.springframework.web.multipart.MultipartFile;

import pilab.com.takeleaf.models.AppUser;
import pilab.com.takeleaf.models.Role;

public interface AccountService {
    public void saveUser(AppUser appUser);

    public AppUser findByUsername(String username);

    public AppUser findByEmail(String email);

    public List<AppUser> userList();

    public Role findUserRoleByName(String role);

    public Role saveRole(Role role);

    public void updateUser(AppUser appUser,HashMap<String,String> request);

    public void updateUserPassword(AppUser appUser,String newPassword);

    public AppUser findById(Long id);

    public void deleteUser(AppUser appUser);

    public void resetPassword(AppUser appUser);

    public List<AppUser> getUserListByUsername(String username);

    public AppUser simpleSave(AppUser appUser); 

	public String saveUserImage(MultipartFile multipartFile, Long userImageId);

}
