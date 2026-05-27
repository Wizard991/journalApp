package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTests {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Test
    void testSaveNewUser() {

        User user = User.builder()
                .userName("ram")
                .password("12345")
                .build();

        // Mock repository save method
        when(userRepository.save(any(User.class)))
                .thenReturn(user);

        // Actual method call
        boolean result = userService.saveNewUser(user);

        // Verification
        assertTrue(result);
    }
}