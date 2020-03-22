package pl.itj.dev.services.authservice.services.impl;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.itj.dev.services.authservice.domain.User;
import pl.itj.dev.services.authservice.domain.security.DatabaseUserDetails;
import pl.itj.dev.services.authservice.repositories.UserRepository;

import java.util.List;

@Service
@Qualifier(value = "customUserService")
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDetailsServiceImpl implements UserDetailsService {

    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(username).orElseThrow(() -> new UsernameNotFoundException("Username not found"));

        List<GrantedAuthority> grantedAuthorities = AuthorityUtils.createAuthorityList(
                user.getRoles().stream().map(role -> role.getName().name()).toArray(String[]::new)
        );

        return new DatabaseUserDetails(user.getUserName(), user.getPassword(), grantedAuthorities);
    }
}
