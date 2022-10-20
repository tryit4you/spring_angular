package pilab.com.takeleaf.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import pilab.com.takeleaf.models.AppUser;
public interface AppUserRepository
 extends JpaRepository<AppUser,Long> {
    public AppUser findByUsername(String username);

    public AppUser findByEmail(String email);
    
    @Query("SELECT appUser FROM AppUser appUser WHERE appUser.id=:id")
    public AppUser findUserById(Long id);

    public List<AppUser> findByUsernameContaining(String username);

    
}
