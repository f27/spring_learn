package guru.qa.bobr.data;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class UserEntity {
    private UUID id = UUID.randomUUID();
    private String username;
    private String password;
    private Boolean enabled = true;
    private Boolean accountNonExpired = true;
    private Boolean accountNonLocked = true;
    private Boolean credentialsNonExpired = true;
    private List<AuthorityEntity> authorities = new ArrayList<>();

    public void addAuthorities(AuthorityEntity... authorities) {
        for (AuthorityEntity authority : authorities) {
            this.authorities.add(authority);
            authority.setUser(this);
        }
    }

    public void removeAuthority(AuthorityEntity authority) {
        this.authorities.remove(authority);
        authority.setUser(null);
    }
}
