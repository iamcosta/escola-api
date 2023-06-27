package app.netlify.iambarroso.escolaapi.repository.spec;

import app.netlify.iambarroso.escolaapi.util.StringUtil;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;

public class GlobalPredicates {
    public static Predicate stringPredicate(Expression<String> expression, CriteriaBuilder criteriaBuilder, String value) {
        return criteriaBuilder
                .like(
                        criteriaBuilder.function(
                                "unaccent",
                                String.class,
                                criteriaBuilder.upper(expression)
                        ),
                        "%" + StringUtil.unaccent(value.toUpperCase())+ "%"
                );
    }
}
