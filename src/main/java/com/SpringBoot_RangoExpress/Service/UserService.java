package com.SpringBoot_RangoExpress.Service;

import com.SpringBoot_RangoExpress.DTO.LoginResponse;
import com.SpringBoot_RangoExpress.DTO.UserDetailsDTO;
import com.SpringBoot_RangoExpress.Exception.NotFoundUserList;
import com.SpringBoot_RangoExpress.Exception.UserWasRegistred;
import com.SpringBoot_RangoExpress.Model.User;
import com.SpringBoot_RangoExpress.Repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Optional<User> findByUsername(String username) {
        return userRepository.findByCpf(username);
    }

    public String save(User user) throws UserWasRegistred {
        Optional<User> foundUser = userRepository.findByCpf(
                user.getCpf()
        );
        if (foundUser.isPresent()) {
            throw new UserWasRegistred("CPF já cadastrado!");}
        else {
            user.setRoles(Collections.singletonList("USER"));
            userRepository.save(user);
            return "Usuário cadastrado com sucesso!";}
    }

    public String saveAdm(User user) throws UserWasRegistred {
        Optional<User> foundUser = userRepository.findByCpf(
                user.getCpf()
        );
        if (foundUser.isPresent()) {
            throw new UserWasRegistred("CPF já cadastrado!");}
        else {
            userRepository.save(user);
            return "Adm cadastrado com sucesso!";}
    }


    public LoginResponse findByUsernameAndPassword(User user) {
        System.out.println("Login Recebido Service: " + user.getCpf());
        System.out.println("A Senha Recebida Service: " + user.getPassword());
        //user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        try {
            Optional<User> foundUser = userRepository.findByCpfAndPassword(
                    user.getCpf(),
                    user.getPassword()
            );

            System.out.println("Retorno da Consulta Service: " + foundUser);

            if (foundUser.isPresent()) {
                User authenticatedUser = foundUser.get();
                return new LoginResponse(
                        true,
                        "Usuário autenticado com sucesso",
                        authenticatedUser.getCpf(),
                        authenticatedUser.getRoles(), // Agora retornamos a lista de roles
                        authenticatedUser.getId(),        // Novo campo
                        authenticatedUser.getNome(),   // Novo campo
                        authenticatedUser.getEndereco(),
                        authenticatedUser.getLatitude(),
                        authenticatedUser.getLongitude()
                );
            } else {
                return new LoginResponse(
                        false,
                        "Usuário ou senha inválidos",
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null
                );
            }
        } catch (Exception e) {
            return new LoginResponse(
                    false,
                    "Erro ao processar a autenticação: " + e.getMessage(),
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null
            );
        }
    }

    public List<UserDetailsDTO> getAllUsers()throws NotFoundUserList {
        List<User> users = userRepository.findAll();
        if (users.isEmpty()) {
            throw new NotFoundUserList("Nenhum usuário encontrado");}
        else{
            return users.stream()
                    .map(this::convertToDTO)
                    .collect(Collectors.toList());}
    }
    private UserDetailsDTO convertToDTO(User user) {
        UserDetailsDTO dto = new UserDetailsDTO();
        BeanUtils.copyProperties(user, dto);
        return dto;
    }
}
