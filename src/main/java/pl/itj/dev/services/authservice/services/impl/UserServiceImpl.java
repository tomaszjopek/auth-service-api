package pl.itj.dev.services.authservice.services.impl;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.itj.dev.services.authservice.domain.User;
import pl.itj.dev.services.authservice.domain.auth.CreatedUserData;
import pl.itj.dev.services.authservice.domain.auth.SignUpData;
import pl.itj.dev.services.authservice.domain.security.Role;
import pl.itj.dev.services.authservice.domain.security.RoleClass;
import pl.itj.dev.services.authservice.repositories.RoleRepository;
import pl.itj.dev.services.authservice.repositories.UserRepository;
import pl.itj.dev.services.authservice.services.ifc.UserService;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.Set;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    UserRepository userRepository;
    RoleRepository roleRepository;
    PasswordEncoder passwordEncoder;

    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public Optional<CreatedUserData> createUser(SignUpData signUpData) {
        Set<Role> roles = prepareRoles(signUpData);

        User user = User.builder()
                .email(signUpData.getEmail())
                .password(passwordEncoder.encode(signUpData.getPassword()))
                .userName(signUpData.getUsername())
                .roles(roles)
                .build();

        User savedUser = userRepository.save(user);
        CreatedUserData createdUserData = new CreatedUserData(savedUser.getUserName(), Boolean.TRUE);

        return Optional.of(createdUserData);
    }

    private Set<Role> prepareRoles(SignUpData signUpData) {
        return roleRepository.findRolesByNameIn(Set.of(RoleClass.ROLE_SUPERADMIN));
    }
}
