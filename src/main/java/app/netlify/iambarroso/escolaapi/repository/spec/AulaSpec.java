package app.netlify.iambarroso.escolaapi.repository.spec;

import app.netlify.iambarroso.escolaapi.domain.model.Aula;
import app.netlify.iambarroso.escolaapi.domain.model.Professor;
import app.netlify.iambarroso.escolaapi.domain.model.Turma;
import app.netlify.iambarroso.escolaapi.repository.spec.filter.AulaFilter;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public class AulaSpec {

    public static Specification<Aula> createPredicate(AulaFilter filtro) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (StringUtils.hasText(filtro.getProfessor())) {
                Join<Aula, Professor> professor = root.join("professor");
                predicates.add(GlobalPredicates.stringPredicate(professor.get("nome"), criteriaBuilder, filtro.getProfessor()));
            }
            if (StringUtils.hasText(filtro.getTurma())) {
                Join<Aula, Turma> turma = root.join("turma");
                predicates.add(GlobalPredicates.stringPredicate(turma.get("descricao"), criteriaBuilder, filtro.getTurma()));
            }
            if (filtro.getDiaSemana() != null) {
                predicates.add(criteriaBuilder.equal(root.get("diaSemana"), filtro.getDiaSemana()));
            }
            if (StringUtils.hasText(filtro.getDisciplina())) {
                Join<Aula, Professor> disciplina = root.join("professor").join("disciplina");
                predicates.add(GlobalPredicates.stringPredicate(disciplina.get("nome"), criteriaBuilder, filtro.getDisciplina()));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
