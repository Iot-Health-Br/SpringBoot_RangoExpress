package com.SpringBoot_RangoExpress.Controller;

import com.SpringBoot_RangoExpress.DTO.LoginResponse;
import com.SpringBoot_RangoExpress.DTO.UserDetailsDTO;
import com.SpringBoot_RangoExpress.Error.ErrorResponse;
import com.SpringBoot_RangoExpress.Exception.NotFoundUserList;
import com.SpringBoot_RangoExpress.Exception.UserWasRegistred;
import com.SpringBoot_RangoExpress.Model.User;
import com.SpringBoot_RangoExpress.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("user")
@CrossOrigin(origins = "http://localhost:4200", methods = {RequestMethod.GET, RequestMethod.POST}) // Aplica CORS para todos os métodos desse controlador
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody User user) {
        try {
            String resultado = String.valueOf(userService.save(user));
            return ResponseEntity.ok(resultado);
        } catch (UserWasRegistred e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno ao salvar a pessoa.");
        }
    }
    @PostMapping("/saveAdm")
    public ResponseEntity<String> saveAdm(@RequestBody User user) {
        try {
            String resultado = String.valueOf(userService.saveAdm(user));
            return ResponseEntity.ok(resultado);
        } catch (UserWasRegistred e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno ao salvar a pessoa.");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody User user) {
        System.out.println("Login Recebido Controller: " + user.getCpf());
        System.out.println("A Senha Recebida Controller: " + user.getPassword());
        LoginResponse response = userService.findByUsernameAndPassword(user);
        System.out.println("Retorno da Consulta: " + response);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/getUser")
    public ResponseEntity<?> getAllUsers() {
        try {
            List<UserDetailsDTO> users = userService.getAllUsers();
            return ResponseEntity.ok(users);
        } catch (NotFoundUserList e) {
            // Retorna um objeto com a mensagem de erro
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse("Erro interno ao buscar a lista de usuários."));
        }
    }
}
