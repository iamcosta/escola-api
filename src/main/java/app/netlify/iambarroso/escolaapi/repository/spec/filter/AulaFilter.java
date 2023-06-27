package app.netlify.iambarroso.escolaapi.repository.spec.filter;

import app.netlify.iambarroso.escolaapi.domain.enums.DiaSemana;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AulaFilter {
    private String professor;
    private String turma;
    private DiaSemana diaSemana;
    private String disciplina;
}
