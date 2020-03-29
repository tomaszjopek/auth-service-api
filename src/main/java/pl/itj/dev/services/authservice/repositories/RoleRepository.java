package pl.itj.dev.services.authservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.itj.dev.services.authservice.domain.security.Role;
import pl.itj.dev.services.authservice.domain.security.RoleClass;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(String name);
    Set<Role> findRolesByNameIn(Collection<RoleClass> names);
}
