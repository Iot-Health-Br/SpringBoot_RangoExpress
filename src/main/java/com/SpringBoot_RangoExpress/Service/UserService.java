package com.SpringBoot_RangoExpress.Service;

import com.SpringBoot_RangoExpress.DTO.LoginResponse;
import com.SpringBoot_RangoExpress.DTO.UserDetailsDTO;
import com.SpringBoot_RangoExpress.Exception.NotFoundUserList;
import com.SpringBoot_RangoExpress.Exception.UserNotFound;
import com.SpringBoot_RangoExpress.Exception.UserWasRegistred;
import com.SpringBoot_RangoExpress.Model.User;
import com.SpringBoot_RangoExpress.Repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public String save(User user) throws UserWasRegistred {
        Optional<User> foundUser = userRepository.findByCpf(
                user.getCpf()
        );
        if (foundUser.isPresent()) {
            throw new UserWasRegistred("CPF já cadastrado!");}
        else {
            user.setRoles(Collections.singletonList("USER"));
            user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
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
            user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
            userRepository.save(user);
            return "Adm cadastrado com sucesso!";}
    }


    public LoginResponse findByUsernameAndPassword(User user) throws UserNotFound {

        try {
            Optional<User> foundUser = userRepository.findByCpf(user.getCpf());

            if (foundUser.isPresent() && passwordEncoder.matches(user.getPassword(), foundUser.get().getPassword())) {
                User authenticatedUser = foundUser.get();
                return new LoginResponse(
                        true,
                        "Usuário autenticado com sucesso",
                        authenticatedUser.getCpf(),
                        authenticatedUser.getRoles(),
                        authenticatedUser.getId(),
                        authenticatedUser.getNome(),
                        authenticatedUser.getEndereco(),
                        authenticatedUser.getLatitude(),
                        authenticatedUser.getLongitude()
                        );
            }else{
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
            throw new UserNotFound("Erro durante autenticação");
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
