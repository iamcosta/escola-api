package app.netlify.iambarroso.escolaapi.repository.spec;

import app.netlify.iambarroso.escolaapi.domain.model.Turma;
import app.netlify.iambarroso.escolaapi.repository.spec.filter.TurmaFilter;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public class TurmaSpec {
    public static Specification<Turma> createPredicate(TurmaFilter filtro) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (filtro.getAno() != null) {
                predicates.add(criteriaBuilder.equal(root.get("ano"), filtro.getAno()));
            }
            if (StringUtils.hasText(filtro.getDescricao())) {
                predicates.add(GlobalPredicates.stringPredicate(root.get("descricao"), criteriaBuilder, filtro.getDescricao()));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
