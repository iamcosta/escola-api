package app.netlify.iambarroso.escolaapi.rest.dto;

import app.netlify.iambarroso.escolaapi.domain.model.Professor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ProfessorSaveDto {
    private Long id;
    @NotBlank(message = "Nome é obrigatório")
    private String nome;
    @NotNull(message = "Disciplina é obrigatória")
    protected Long disciplinaId;

    public Professor toEntity() {
        Professor professor = new Professor();
        BeanUtils.copyProperties(this, professor);
        return professor;
    }
}
