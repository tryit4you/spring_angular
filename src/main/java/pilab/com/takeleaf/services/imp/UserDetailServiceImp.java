package pilab.com.takeleaf.services.imp;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import pilab.com.takeleaf.models.AppUser;
import pilab.com.takeleaf.models.UserRole;
import pilab.com.takeleaf.services.AccountService;
@Service
@Transactional
public class UserDetailServiceImp  implements UserDetailsService{

    @Autowired
    private AccountService accountService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser =accountService.findByUsername(username);
        if(appUser==null){
            throw new UsernameNotFoundException("username "+username+" was not found");
        }
        Collection<GrantedAuthority> authorities=new ArrayList<>();
        Set<UserRole> userRoles=appUser.getUserRoles();
        ((Set)userRoles).forEach(userRole->{
            authorities.add(new SimpleGrantedAuthority(userRoles.toString()));
        });
        return new User(appUser.getUsername(),appUser.getPassword(),authorities);
    }
    
}
