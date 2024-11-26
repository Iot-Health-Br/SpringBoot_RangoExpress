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
                "(62) 9 9756-2960",
                "admin@gmail.com",
                "R.46, Jardim Goiás, CEP:74805-440",
                "-16.704365",
                "-49.236562",
                "123",
                "ADMIN"
        );

        // Criação do usuário USER
        createUserIfNotExists(
                "user",
                "111.111.111-11",
                "feminino",
                "21/03/1994",
                "(62) 9 9412-9748",
                "user@gmail.com",
                "Av.T-15, St. Bueno, CEP:74230-010",
                "-16.710948",
                "-49.271125",
                "123",
                "USER"

        );

        // Criação do usuário MANAGER
        createUserIfNotExists(
                "Igor Leonor Macedo",
                "750.802.731-00",
                "masculino",
                "21/03/1994",
                "(62) 9 9820-9756",
                "iglmacedo@gmail.com",
                "Alameda das Espatódias, Res. Aldeia do Vale, CEP:74680-160",
                "-16.618970",
                "-49.193654",
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
            String latitude,
            String longitude,
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
            user.setLatitude(latitude);
            user.setLongitude(longitude);
            user.setPassword(password);
            //user.setPassword(new BCryptPasswordEncoder().encode(password));
            user.getRoles().add(role);
            repository.save(user);
        }
    }
}
