package app.netlify.iambarroso.escolaapi.repository.spec;

import app.netlify.iambarroso.escolaapi.domain.model.Disciplina;
import app.netlify.iambarroso.escolaapi.domain.model.Professor;
import app.netlify.iambarroso.escolaapi.repository.spec.filter.ProfessorFilter;
import app.netlify.iambarroso.escolaapi.util.StringUtil;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public class ProfessorSpec {
    public static Specification<Professor> createPredicate(ProfessorFilter filtro) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (StringUtils.hasText(filtro.getNome())) {
                predicates.add(GlobalPredicates.stringPredicate(root.get("nome"), criteriaBuilder, filtro.getNome()));
            }
            if (StringUtils.hasText(filtro.getDisciplina())) {
                Join<Professor, Disciplina> disciplina = root.join("disciplina");
                predicates.add(GlobalPredicates.stringPredicate(disciplina.get("nome"), criteriaBuilder, filtro.getDisciplina()));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
