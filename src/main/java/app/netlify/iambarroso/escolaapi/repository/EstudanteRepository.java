package app.netlify.iambarroso.escolaapi.repository;

import app.netlify.iambarroso.escolaapi.domain.model.Estudante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface EstudanteRepository extends JpaRepository<Estudante, Long>, JpaSpecificationExecutor<Estudante> {
}
