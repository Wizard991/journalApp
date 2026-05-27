package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserDetailsServiceImplTests {

    @InjectMocks
    private UserDetailsServiceImpl userDetailsService;

    @Mock
    private UserRepository userRepository;

    @Test
    void loadUserByUsernameTest() {

        when(userRepository.findByUserName(anyString()))
                .thenReturn(
                        User.builder()
                                .userName("ram")
                                .password("inrinrick")
                                .roles(List.of("USER")) // FIXED
                                .build()
                );

        UserDetails user =
                userDetailsService.loadUserByUsername("ram");

        assertNotNull(user);
        assertEquals("ram", user.getUsername());
    }
}

