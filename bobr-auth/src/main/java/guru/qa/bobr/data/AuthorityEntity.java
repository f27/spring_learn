package guru.qa.bobr.data;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class AuthorityEntity {
    private UUID id = UUID.randomUUID();
    private Authority authority;
    private UserEntity user;
}
