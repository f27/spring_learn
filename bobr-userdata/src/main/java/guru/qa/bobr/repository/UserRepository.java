package guru.qa.bobr.repository;

import guru.qa.bobr.dto.UserData;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class UserRepository {

    public UserData findByUsername(String username) {
        UserData userData = new UserData();
        userData.setId(UUID.randomUUID());
        userData.setUsername(username);
        userData.setEmail("test@fake.local");
        userData.setFirstname("first_name1");
        userData.setLastname("last_name1");
        return userData;
    }
}
