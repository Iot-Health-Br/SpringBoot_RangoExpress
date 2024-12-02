package com.SpringBoot_RangoExpress.DTO;

import lombok.*;

import java.util.List;
@Data
@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
public class LoginResponse {
    private boolean authenticated;
    private String message;
    private String cpf;  // CPF Vem por aqui
    private List<String> roles;
    private Long userId;
    private String fullName;
    private String endereco;
    private String latitude;
    private String longitude;

    public boolean isSuccess() {
        if (authenticated) {
            return true;
        }
        return false;
    }
}
