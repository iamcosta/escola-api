package app.netlify.iambarroso.escolaapi.rest.dto;

import app.netlify.iambarroso.escolaapi.domain.enums.DiaSemana;
import app.netlify.iambarroso.escolaapi.domain.model.Aula;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;

import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AulaShortDto {
    private Long id;
    private String professor;
    private String turma;
    private DiaSemana diaSemana;
    private LocalTime horaEntrada;
    private LocalTime horaSaida;
    private String disciplina;

    public static AulaShortDto fromEntity(Aula entity) {
        AulaShortDto dto = new AulaShortDto();
        BeanUtils.copyProperties(entity, dto);
        dto.setProfessor(entity.getProfessor().getNome());
        dto.setTurma(entity.getTurma().getDescricao());
        dto.setDisciplina(entity.getDisciplina().getNome());
        return dto;
    }

    public static PageDto<AulaShortDto> createPageDto(PageDto<Aula> entityPage) {
        PageDto<AulaShortDto> pageDto = new PageDto<>();
        BeanUtils.copyProperties(entityPage, pageDto);
        pageDto.setData(
                entityPage
                        .getData()
                        .stream()
                        .map(AulaShortDto::fromEntity)
                        .collect(Collectors.toList())
        );
        return pageDto;
    }
}
