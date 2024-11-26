package com.SpringBoot_RangoExpress.Repository;

import com.SpringBoot_RangoExpress.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByCpf(String cpf);
    Optional<User> findByCpfAndPassword(String cpf, String password);
}
