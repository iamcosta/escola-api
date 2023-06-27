package app.netlify.iambarroso.escolaapi.repository;

import app.netlify.iambarroso.escolaapi.domain.model.Aula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface AulaRepository extends JpaRepository<Aula, Long>, JpaSpecificationExecutor<Aula> {
}
