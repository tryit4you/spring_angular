package pilab.com.takeleaf.repositories;



import org.springframework.data.jpa.repository.JpaRepository;

import pilab.com.takeleaf.models.Role;

public interface RoleRepository extends JpaRepository<Role,Long>{
    public Role findRoleByName(String name);
}
