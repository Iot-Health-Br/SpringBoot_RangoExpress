package com.SpringBoot_RangoExpress;

import com.SpringBoot_RangoExpress.Model.User;
import com.SpringBoot_RangoExpress.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
public class StartApplication implements CommandLineRunner {

    @Autowired
    private UserRepository repository;

    @Transactional
    @Override
    public void run(String... args) throws Exception {
        // Criação do usuário ADMIN
        createUserIfNotExists(
                "admin",
                "000.000.000-00",
                "masculino",
                "21/03/1994",
                "62 99756-2960",
                "admin@gmail.com",
                "Rua da Paz",
                "123",
                "ADMIN"
        );

        // Criação do usuário USER
        createUserIfNotExists(
                "user",
                "111.111.111-11",
                "feminino",
                "21/03/1994",
                "62 996412-9748",
                "user@gmail.com",
                "Rua da Alvorada",
                "123",
                "USER"
        );

        // Criação do usuário MANAGER
        createUserIfNotExists(
                "Igor Leonor Macedo",
                "750.802.731-00",
                "masculino",
                "21/03/1994",
                "62 99820-9756",
                "iglmacedo@gmail.com",
                "Rua Alerto Rassi",
                "123",
                "MANAGER"
        );
    }

    private void createUserIfNotExists(
            String name,
            String cpf,
            String genero,
            String nascimento,
            String telefone,
            String email,
            String endereco,
            String password,
            String role
    ) {
        Optional<User> optionalUser = repository.findByCpf(cpf);

        if (optionalUser.isEmpty()) {
            User user = new User();
            user.setNome(name);
            user.setCpf(cpf);
            user.setGenero(genero);
            user.setNascimento(nascimento);
            user.setTelefone(telefone);
            user.setEmail(email);
            user.setEndereco(endereco);
            user.setPassword(password);
            //user.setPassword(new BCryptPasswordEncoder().encode(password));
            user.getRoles().add(role);
            repository.save(user);
        }
    }
}
