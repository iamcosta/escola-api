package app.netlify.iambarroso.escolaapi.repository.spec.filter;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class TurmaFilter {
    private Integer ano;
    private String descricao;
}
