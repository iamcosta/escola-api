package app.netlify.iambarroso.escolaapi.repository.spec.filter;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProfessorFilter {
    private String nome;
    private String disciplina;
}
