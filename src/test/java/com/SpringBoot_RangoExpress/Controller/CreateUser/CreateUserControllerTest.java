package com.SpringBoot_RangoExpress.Controller.CreateUser;

import com.SpringBoot_RangoExpress.Controller.UserController;
import com.SpringBoot_RangoExpress.Exception.UserWasRegistred;
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

class CreateUserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void save_WhenValidUser_ReturnsOk() throws UserWasRegistred {
        // Arrange
        User user = new User();
        user.setCpf("12345678900");
        user.setPassword("password123");

        when(userService.save(any(User.class))).thenReturn("Usuário cadastrado com sucesso!");

        // Act
        ResponseEntity<String> response = userController.save(user);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Usuário cadastrado com sucesso!", response.getBody());
    }

    @Test
    void save_WhenUserAlreadyExists_ReturnsBadRequest() throws UserWasRegistred {
        // Arrange
        User user = new User();
        user.setCpf("12345678900");
        user.setPassword("password123");

        when(userService.save(any(User.class)))
                .thenThrow(new UserWasRegistred("CPF já cadastrado!"));

        // Act
        ResponseEntity<String> response = userController.save(user);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("CPF já cadastrado!", response.getBody());
    }

    @Test
    void save_WhenInternalError_ReturnsInternalServerError() throws UserWasRegistred {
        // Arrange
        User user = new User();
        user.setCpf("12345678900");
        user.setPassword("password123");

        when(userService.save(any(User.class)))
                .thenThrow(new RuntimeException("Erro interno"));

        // Act
        ResponseEntity<String> response = userController.save(user);

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Erro interno ao salvar a pessoa.", response.getBody());
    }

    @Test
    void saveAdm_WhenValidAdmin_ReturnsOk() throws UserWasRegistred {
        // Arrange
        User admin = new User();
        admin.setCpf("12345678900");
        admin.setPassword("password123");
        admin.setRoles(Arrays.asList("ADMIN"));

        when(userService.saveAdm(any(User.class))).thenReturn("Adm cadastrado com sucesso!");

        // Act
        ResponseEntity<String> response = userController.saveAdm(admin);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Adm cadastrado com sucesso!", response.getBody());
    }

    @Test
    void saveAdm_WhenAdminAlreadyExists_ReturnsBadRequest() throws UserWasRegistred {
        // Arrange
        User admin = new User();
        admin.setCpf("12345678900");
        admin.setPassword("password123");

        when(userService.saveAdm(any(User.class)))
                .thenThrow(new UserWasRegistred("CPF já cadastrado!"));

        // Act
        ResponseEntity<String> response = userController.saveAdm(admin);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("CPF já cadastrado!", response.getBody());
    }

    @Test
    void saveAdm_WhenInternalError_ReturnsInternalServerError() throws UserWasRegistred {
        // Arrange
        User admin = new User();
        admin.setCpf("12345678900");
        admin.setPassword("password123");

        when(userService.saveAdm(any(User.class)))
                .thenThrow(new RuntimeException("Erro interno"));

        // Act
        ResponseEntity<String> response = userController.saveAdm(admin);

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Erro interno ao salvar a pessoa.", response.getBody());
    }
}
