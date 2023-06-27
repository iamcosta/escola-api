package app.netlify.iambarroso.escolaapi.domain.model;

import app.netlify.iambarroso.escolaapi.domain.enums.DiaSemana;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalTime;

@Entity
@Getter
@Setter
public class Aula {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JsonIgnoreProperties(value = "disciplina")
    private Professor professor;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(referencedColumnName = "id", name = "turma_id")
    @JsonIgnoreProperties(value = "aulas")
    private Turma turma;

    @Enumerated(EnumType.STRING)
    private DiaSemana diaSemana;

    private LocalTime horaEntrada;
    private LocalTime horaSaida;

    @Transient
    @JsonIgnoreProperties(value = "professores")
    private Disciplina disciplina;

    public Disciplina getDisciplina() {
        return professor.getDisciplina();
    }
}
