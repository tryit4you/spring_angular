package pilab.com.takeleaf.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pilab.com.takeleaf.models.AppUser;
public interface AppUserRepository
 extends JpaRepository<AppUser,Long> {
    public AppUser findByUsername(String username);

    public AppUser findByEmail(Long id);

    public AppUser findUserById(Long id);

    public List<AppUser> findByUsernameContaining(String username);

    
}
