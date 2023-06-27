package app.netlify.iambarroso.escolaapi.domain.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.repository.Temporal;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
public class Frequencia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate data;

    @OneToOne
    private Aula aula;

    @OneToMany
    private Set<Estudante> estudantesPresentes;

    @Transient
    private Set<Estudante> estudantesFaltosos;

    public Set<Estudante> getEstudantesFaltosos() {
        Set<Estudante> estudantes = aula.getTurma().getEstudantes();
        return estudantes
                .stream()
                .filter(estudante -> estudantesPresentes
                                .stream()
                                .noneMatch(ep -> Objects.equals(estudante.getId(), ep.getId())))
                .collect(Collectors.toSet());
    }
}
