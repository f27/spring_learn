package guru.qa.bobr.service;

import guru.qa.bobr.domain.UserDomain;
import guru.qa.bobr.entity.UserEntity;
import guru.qa.bobr.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
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
        return new UserDomain(user);
    }
}
