package guru.qa.bobr.service;

import guru.qa.bobr.data.UserEntity;
import guru.qa.bobr.data.repository.UserRepository;
import guru.qa.bobr.domain.BobrUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class BobrUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public BobrUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new BobrUserDetails(user);
    }
}
