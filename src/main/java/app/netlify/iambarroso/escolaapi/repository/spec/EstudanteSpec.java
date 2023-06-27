package app.netlify.iambarroso.escolaapi.repository.spec;

import app.netlify.iambarroso.escolaapi.domain.model.Estudante;
import app.netlify.iambarroso.escolaapi.domain.model.Turma;
import app.netlify.iambarroso.escolaapi.repository.spec.filter.EstudanteFilter;
import app.netlify.iambarroso.escolaapi.util.StringUtil;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public class EstudanteSpec {
    public static Specification<Estudante> createPredicate(EstudanteFilter filtro) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (StringUtils.hasText(filtro.getNome())) {
                predicates.add(GlobalPredicates.stringPredicate(root.get("nome"), criteriaBuilder, filtro.getNome()));
            }
            if (StringUtils.hasText(filtro.getMatricula())) {
                predicates.add(criteriaBuilder
                        .like(
                                criteriaBuilder.upper(root.get("matricula")),
                                "%" + filtro.getNome().toUpperCase()+ "%"
                        )
                );
            }
            if (StringUtils.hasText(filtro.getTurma())) {
                Join<Estudante, Turma> turma = root.join("turma");
                predicates.add(GlobalPredicates.stringPredicate(turma.get("descricao"), criteriaBuilder, filtro.getTurma()));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
