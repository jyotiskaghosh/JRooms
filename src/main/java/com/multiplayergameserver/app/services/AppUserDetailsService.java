package com.multiplayergameserver.app.services;

import com.multiplayergameserver.app.repositories.UserRepository;
import com.multiplayergameserver.app.game.models.User;
import com.multiplayergameserver.app.models.AppUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AppUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);

        user.orElseThrow(() -> new UsernameNotFoundException("Not found: " + username));

        return user.map(AppUserDetails::new).get();
    }
}
