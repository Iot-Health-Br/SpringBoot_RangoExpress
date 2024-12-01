package com.SpringBoot_RangoExpress.DTO;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.util.List;

@Getter
@Setter
@ToString
public class UserDetailsDTO {
    @Id
    private Long id;
    private String nome;
    private String cpf;
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "ENG_ROLE", joinColumns = @JoinColumn(name = "id"))
    @Column(name = "role_id")
    private List<String> roles;
}