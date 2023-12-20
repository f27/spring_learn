package guru.qa.bobr.data.repository;

import guru.qa.bobr.data.Authority;
import guru.qa.bobr.data.AuthorityEntity;
import guru.qa.bobr.data.UserEntity;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
public class UserRepository {
    private final static Map<String, UserEntity> users = new HashMap<>();

    static {

        UserEntity admin = new UserEntity();
        admin.setId(UUID.randomUUID());
        admin.setUsername("admin");
        admin.setPassword("{noop}12345");

        AuthorityEntity authorityAdmin = new AuthorityEntity();
        authorityAdmin.setAuthority(Authority.ADMIN);
        authorityAdmin.setUser(admin);

        AuthorityEntity authorityUser = new AuthorityEntity();
        authorityUser.setAuthority(Authority.USER);
        authorityUser.setUser(admin);
        admin.addAuthorities(authorityAdmin, authorityUser);

        users.put("admin", admin);
    }

    @Nullable
    public UserEntity findByUsername(@Nonnull String username) {
        return users.get(username);
    }
}
