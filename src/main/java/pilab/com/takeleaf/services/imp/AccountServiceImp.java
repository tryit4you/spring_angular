package pilab.com.takeleaf.services.imp;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import pilab.com.takeleaf.Utility.EmailConstructor;
import pilab.com.takeleaf.models.AppUser;
import pilab.com.takeleaf.models.Role;
import pilab.com.takeleaf.repositories.AppUserRepository;
import pilab.com.takeleaf.repositories.RoleRepository;
import pilab.com.takeleaf.services.*;
import pilab.com.takeleaf.Utility.*;;
@Service
@Transactional
public class AccountServiceImp implements AccountService {

    @Autowired
    private BCryptPasswordEncoder bcrypt;

    @Autowired
    private AppUserRepository userRepo;

    @Autowired
    private RoleRepository roleRepo;

    @Autowired
    private EmailConstructor emailConstructor;

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void saveUser(AppUser appUser) {
        String password = RandomStringUtils.randomAlphanumeric(10);
        String encryptedPassword = bcrypt.encode(password);
        appUser.setPassword(encryptedPassword);
        userRepo.save(appUser);
        mailSender.send(emailConstructor.constructNewUserMail(appUser, password));

    }

    @Override
    public AppUser findByUsername(String username) {

        return userRepo.findByUsername(username);
    }

    @Override
    public AppUser findByEmail(String email) {
        return userRepo.findByEmail(email);
    }

    @Override
    public List<AppUser> userList() {
        return userRepo.findAll();
    }

    @Override
    public Role findUserRoleByName(String role) {
        return roleRepo.findRoleByName(role);
    }

    @Override
    public Role saveRole(Role role) {
        return roleRepo.save(role);
    }
    @Override
    public void updateUser(AppUser appUser,HashMap<String,String> request) {
        AppUser userStore=userRepo.findUserById(appUser.getId());

        userStore.setName(request.get("name"));
        userStore.setUsername(request.get("username"));
        userStore.setBio(request.get("bio"));
        
        userRepo.save(appUser);
    }

    @Override
    public void updateUserPassword(AppUser appUser,String newPassword) {
        String encryptPssword=bcrypt.encode(newPassword);
        appUser.setPassword(encryptPssword);
        userRepo.save(appUser);
        mailSender.send(emailConstructor.constructUpdateUserMail(appUser));
    }

    @Override
    public AppUser findById(Long id) {
        return userRepo.findUserById(id);
    }

    @Override
    public void deleteUser(AppUser appUser) {
        userRepo.delete(appUser);
    }

    @Override
    public void resetPassword(AppUser appUser) {
        String password=RandomStringUtils.randomAlphanumeric(10);
        String encryptedPassword=bcrypt.encode(password);
        appUser.setPassword(encryptedPassword)
        ;
        userRepo.save(appUser);
        mailSender.send(emailConstructor.constructNewUserMail(appUser, password));

    }

    @Override
    public List<AppUser> getUserListByUsername(String username) {
        
        return userRepo.findByUsernameContaining(username);
    }

    @Override
    public AppUser simpleSave(AppUser appUser) {
        userRepo.save(appUser);
        mailSender.send(emailConstructor.constructUpdateUserMail(appUser));
        return  appUser;
    }
    @Override
	public String saveUserImage(MultipartFile multipartFile, Long userImageId) {
		/*
		 * MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)
		 * request; Iterator<String> it = multipartRequest.getFileNames(); MultipartFile
		 * multipartFile = multipartRequest.getFile(it.next());
		 */
		byte[] bytes;
		try {
			Files.deleteIfExists(Paths.get(Constants.USER_FOLDER + "/" + userImageId + ".png"));
			bytes = multipartFile.getBytes();
			Path path = Paths.get(Constants.USER_FOLDER + userImageId + ".png");
			Files.write(path, bytes);
			return "User picture saved to server";
		} catch (IOException e) {
			return "User picture Saved";
		}
	}
}
