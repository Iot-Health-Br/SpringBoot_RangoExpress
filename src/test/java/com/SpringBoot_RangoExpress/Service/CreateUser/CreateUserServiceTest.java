package com.SpringBoot_RangoExpress.Service.CreateUser;

import com.SpringBoot_RangoExpress.Exception.UserWasRegistred;
import com.SpringBoot_RangoExpress.Model.User;
import com.SpringBoot_RangoExpress.Repository.UserRepository;
import com.SpringBoot_RangoExpress.Service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class CreateUserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void save_WhenNewUser_ReturnsSuccessMessage() throws UserWasRegistred {
        // Arrange
        User user = new User();
        user.setCpf("12345678900");
        user.setPassword("password123");

        when(userRepository.findByCpf("12345678900")).thenReturn(Optional.empty());
        when(userRepository.save(any(User.class))).thenReturn(user);

        // Act
        String result = userService.save(user);

        // Assert
        assertEquals("Usuário cadastrado com sucesso!", result);
        verify(userRepository).save(any(User.class));
        assertTrue(user.getRoles().contains("USER"));
    }

    @Test
    void save_WhenUserExists_ThrowsUserWasRegistred() {
        // Arrange
        User user = new User();
        user.setCpf("12345678900");
        user.setPassword("password123");

        when(userRepository.findByCpf("12345678900")).thenReturn(Optional.of(user));

        // Act & Assert
        assertThrows(UserWasRegistred.class, () -> userService.save(user));
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void saveAdm_WhenNewAdmin_ReturnsSuccessMessage() throws UserWasRegistred {
        // Arrange
        User admin = new User();
        admin.setCpf("12345678900");
        admin.setPassword("password123");
        admin.setRoles(Arrays.asList("ADMIN"));

        when(userRepository.findByCpf("12345678900")).thenReturn(Optional.empty());
        when(userRepository.save(any(User.class))).thenReturn(admin);

        // Act
        String result = userService.saveAdm(admin);

        // Assert
        assertEquals("Adm cadastrado com sucesso!", result);
        verify(userRepository).save(any(User.class));
    }

    @Test
    void saveAdm_WhenAdminExists_ThrowsUserWasRegistred() {
        // Arrange
        User admin = new User();
        admin.setCpf("12345678900");
        admin.setPassword("password123");
        admin.setRoles(Arrays.asList("ADMIN"));

        when(userRepository.findByCpf("12345678900")).thenReturn(Optional.of(admin));

        // Act & Assert
        assertThrows(UserWasRegistred.class, () -> userService.saveAdm(admin));
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void save_VerifyPasswordEncoding() throws UserWasRegistred {
        // Arrange
        User user = new User();
        user.setCpf("12345678900");
        user.setPassword("password123");

        when(userRepository.findByCpf("12345678900")).thenReturn(Optional.empty());
        when(userRepository.save(any(User.class))).thenReturn(user);

        // Act
        userService.save(user);

        // Assert
        assertNotEquals("password123", user.getPassword());
        assertTrue(user.getPassword().startsWith("$2a$")); // BCrypt hash prefix
    }
}
