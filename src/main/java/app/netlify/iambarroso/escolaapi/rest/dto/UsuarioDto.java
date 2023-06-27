package app.netlify.iambarroso.escolaapi.rest.dto;

import app.netlify.iambarroso.escolaapi.domain.model.Usuario;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

@Getter
@Setter
public class UsuarioDto {
    private Long id;
    private String login;

    public static UsuarioDto fromEntity(Usuario entity) {
        UsuarioDto dto = new UsuarioDto();
        BeanUtils.copyProperties(entity, dto);
        return dto;
    }
}
