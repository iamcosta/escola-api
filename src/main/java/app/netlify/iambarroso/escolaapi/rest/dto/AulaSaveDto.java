package app.netlify.iambarroso.escolaapi.rest.dto;

import app.netlify.iambarroso.escolaapi.domain.enums.DiaSemana;
import app.netlify.iambarroso.escolaapi.domain.model.Aula;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotNull;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AulaSaveDto {
    private Long id;

    @NotNull(message = "Professor é obrigatório")
    private Long professorId;

    @NotNull(message = "Turma é obrigatória")
    private Long turmaId;

    @NotNull(message = "Dia da semana é obrigatório")
    private DiaSemana diaSemana;

    @NotNull(message = "Hora de entrada é obrigatória")
    private LocalTime horaEntrada;

    @NotNull(message = "Hora de saída é obrigatória")
    private LocalTime horaSaida;

    public Aula toEntity() {
        Aula entity = new Aula();
        BeanUtils.copyProperties(this, entity);
        return entity;
    }
}
