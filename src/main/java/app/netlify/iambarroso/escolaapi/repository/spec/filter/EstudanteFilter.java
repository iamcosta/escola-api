package app.netlify.iambarroso.escolaapi.repository.spec.filter;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EstudanteFilter {
    private String nome;
    private String matricula;
    private String turma;
}
