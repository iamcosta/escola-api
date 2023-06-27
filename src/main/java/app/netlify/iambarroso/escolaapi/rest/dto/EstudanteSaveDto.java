package app.netlify.iambarroso.escolaapi.rest.dto;

import app.netlify.iambarroso.escolaapi.domain.model.Estudante;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EstudanteSaveDto {

    private Long id;

    @NotBlank(message = "Nome é obrigatório")
    private String nome;

    @NotBlank(message = "Matrícula é obrigatória")
    private String matricula;

    @NotNull(message = "Turma é obrigatória")
    private Long turmaId;

    public Estudante toEntity() {
        Estudante entity = new Estudante();
        BeanUtils.copyProperties(this, entity);
        return entity;
    }
}
