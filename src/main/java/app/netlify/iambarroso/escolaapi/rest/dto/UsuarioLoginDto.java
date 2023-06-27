package app.netlify.iambarroso.escolaapi.rest.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioLoginDto {
    private String login;
    private String senha;
}
