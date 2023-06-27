package app.netlify.iambarroso.escolaapi.repository.spec;

import app.netlify.iambarroso.escolaapi.domain.model.Disciplina;
import app.netlify.iambarroso.escolaapi.repository.spec.filter.DisciplinaFilter;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public class DisciplinaSpec {
    public static Specification<Disciplina> createPredicate(DisciplinaFilter filtro) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (StringUtils.hasText(filtro.getNome())) {
                predicates.add(GlobalPredicates.stringPredicate(root.get("nome"), criteriaBuilder, filtro.getNome()));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
