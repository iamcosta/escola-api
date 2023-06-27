package app.netlify.iambarroso.escolaapi.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
public class Turma {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descricao;
    private Integer ano;

    @OneToMany(mappedBy = "turma")
    @JsonIgnoreProperties("turma")
    private Set<Estudante> estudantes;

    @OneToMany(mappedBy = "turma")
    @JsonIgnoreProperties("turma")
    private List<Aula> aulas;
}
