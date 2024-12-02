package com.SpringBoot_RangoExpress.Controller.Login;

import com.SpringBoot_RangoExpress.Controller.UserController;
import com.SpringBoot_RangoExpress.DTO.LoginResponse;
import com.SpringBoot_RangoExpress.Error.ErrorResponse;
import com.SpringBoot_RangoExpress.Exception.UserNotFound;
import com.SpringBoot_RangoExpress.Model.User;
import com.SpringBoot_RangoExpress.Service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void login_WhenValidCredentials_ReturnsOk() throws UserNotFound {
        // Arrange
        User user = new User();
        user.setCpf("12345678900");
        user.setPassword("password123");

        LoginResponse expectedResponse = new LoginResponse(
                true,
                "Usuário autenticado com sucesso",
                "12345678900",
                Arrays.asList("ROLE_USER"),
                1L,
                "John Doe",
                "Rua Example, 123",
                "-23.5505",
                "-46.6333"
        );

        when(userService.findByUsernameAndPassword(any(User.class))).thenReturn(expectedResponse);

        // Act
        ResponseEntity<?> response = userController.login(user);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        LoginResponse actualResponse = (LoginResponse) response.getBody();
        assertNotNull(actualResponse);
        assertTrue(actualResponse.isSuccess());
        assertEquals("Usuário autenticado com sucesso", actualResponse.getMessage());
        assertEquals("12345678900", actualResponse.getCpf());
    }

    @Test
    void login_WhenUserNotFound_ReturnsNotFound() throws UserNotFound {
        // Arrange
        User user = new User();
        user.setCpf("99999999999");
        user.setPassword("wrongpassword");

        when(userService.findByUsernameAndPassword(any(User.class)))
                .thenThrow(new UserNotFound("Usuário não encontrado"));

        // Act
        ResponseEntity<?> response = userController.login(user);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        ErrorResponse errorResponse = (ErrorResponse) response.getBody();
        assertNotNull(errorResponse);
        assertEquals("Usuário não encontrado", errorResponse.getMessage());
    }

    @Test
    void login_WhenInternalError_ReturnsInternalServerError() throws UserNotFound {
        // Arrange
        User user = new User();
        user.setCpf("12345678900");
        user.setPassword("password123");

        when(userService.findByUsernameAndPassword(any(User.class)))
                .thenThrow(new RuntimeException("Erro interno"));

        // Act
        ResponseEntity<?> response = userController.login(user);

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        ErrorResponse errorResponse = (ErrorResponse) response.getBody();
        assertNotNull(errorResponse);
        assertEquals("Erro interno ao fazer login.", errorResponse.getMessage());
    }
}
