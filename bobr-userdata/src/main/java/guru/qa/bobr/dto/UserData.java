package guru.qa.bobr.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UserData {
    private UUID id;
    private String username;
    private String email;
    private String firstname;
    private String lastname;
}
