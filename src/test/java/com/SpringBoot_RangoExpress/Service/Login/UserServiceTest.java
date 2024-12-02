package com.SpringBoot_RangoExpress.Service.Login;

import com.SpringBoot_RangoExpress.DTO.LoginResponse;
import com.SpringBoot_RangoExpress.Exception.UserNotFound;
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
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {

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
    void findByUsernameAndPassword_WhenValidCredentials_ReturnsSuccessResponse() throws UserNotFound {
        // Arrange
        User requestUser = new User();
        requestUser.setCpf("12345678900");
        requestUser.setPassword("password123");

        User foundUser = new User();
        foundUser.setId(1L);
        foundUser.setCpf("12345678900");
        foundUser.setPassword("hashedPassword");
        foundUser.setNome("John Doe");
        foundUser.setEndereco("Rua Example, 123");
        foundUser.setLatitude("-23.5505");
        foundUser.setLongitude("-46.6333");
        foundUser.setRoles(List.of("ROLE_USER"));

        when(userRepository.findByCpf("12345678900")).thenReturn(Optional.of(foundUser));
        when(passwordEncoder.matches(anyString(), anyString())).thenReturn(true);

        // Act
        LoginResponse response = userService.findByUsernameAndPassword(requestUser);

        // Assert
        assertTrue(response.isSuccess());
        assertEquals("Usuário autenticado com sucesso", response.getMessage());
        assertEquals("12345678900", response.getCpf());
        assertEquals(1L, response.getUserId());
        assertEquals("John Doe", response.getFullName());
        assertEquals("Rua Example, 123", response.getEndereco());
        assertEquals(-23.5505, response.getLatitude());
        assertEquals(-46.6333, response.getLongitude());
        assertEquals(Arrays.asList("ROLE_USER"), response.getRoles());
    }

    @Test
    void findByUsernameAndPassword_WhenInvalidCredentials_ReturnsFailureResponse() throws UserNotFound {
        // Arrange
        User requestUser = new User();
        requestUser.setCpf("12345678900");
        requestUser.setPassword("wrongpassword");

        User foundUser = new User();
        foundUser.setPassword("hashedPassword");

        when(userRepository.findByCpf("12345678900")).thenReturn(Optional.of(foundUser));
        when(passwordEncoder.matches(anyString(), anyString())).thenReturn(false);

        // Act
        LoginResponse response = userService.findByUsernameAndPassword(requestUser);

        // Assert
        assertFalse(response.isSuccess());
        assertEquals("Usuário ou senha inválidos", response.getMessage());
        assertNull(response.getCpf());
        assertNull(response.getUserId());
        assertNull(response.getFullName());
        assertNull(response.getEndereco());
        assertNull(response.getLatitude());
        assertNull(response.getLongitude());
        assertNull(response.getRoles());
    }

    @Test
    void findByUsernameAndPassword_WhenUserNotFound_ReturnsFailureResponse() throws UserNotFound {
        // Arrange
        User requestUser = new User();
        requestUser.setCpf("99999999999");
        requestUser.setPassword("password123");

        when(userRepository.findByCpf("99999999999")).thenReturn(Optional.empty());

        // Act
        LoginResponse response = userService.findByUsernameAndPassword(requestUser);

        // Assert
        assertFalse(response.isSuccess());
        assertEquals("Usuário ou senha inválidos", response.getMessage());
    }

    @Test
    void findByUsernameAndPassword_WhenRepositoryThrowsException_ThrowsUserNotFound() {
        // Arrange
        User requestUser = new User();
        requestUser.setCpf("12345678900");
        requestUser.setPassword("password123");

        when(userRepository.findByCpf(anyString())).thenThrow(new RuntimeException("Database error"));

        // Act & Assert
        assertThrows(UserNotFound.class, () -> userService.findByUsernameAndPassword(requestUser));
    }
}