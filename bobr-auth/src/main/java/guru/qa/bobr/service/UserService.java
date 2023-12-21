package guru.qa.bobr.service;

import guru.qa.bobr.entity.AuthorityEntity;
import guru.qa.bobr.entity.UserEntity;
import guru.qa.bobr.enums.Authority;
import guru.qa.bobr.repository.UserRepository;
import jakarta.annotation.Nonnull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public @Nonnull String registerUser(@Nonnull String username, @Nonnull String password) {
        UserEntity userEntity = new UserEntity();
        userEntity.setEnabled(true);
        userEntity.setAccountNonExpired(true);
        userEntity.setCredentialsNonExpired(true);
        userEntity.setAccountNonLocked(true);
        userEntity.setUsername(username);
        userEntity.setPassword(passwordEncoder.encode(password));

        AuthorityEntity readAuthorityEntity = new AuthorityEntity();
        readAuthorityEntity.setAuthority(Authority.read);
        AuthorityEntity writeAuthorityEntity = new AuthorityEntity();
        writeAuthorityEntity.setAuthority(Authority.write);
        userEntity.addAuthorities(readAuthorityEntity, writeAuthorityEntity);

        return userRepository.save(userEntity).getUsername();
    }
}
