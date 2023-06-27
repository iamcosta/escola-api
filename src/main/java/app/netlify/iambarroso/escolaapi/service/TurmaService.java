package app.netlify.iambarroso.escolaapi.service;

import app.netlify.iambarroso.escolaapi.domain.model.Turma;
import app.netlify.iambarroso.escolaapi.exceptions.NotFoundException;
import app.netlify.iambarroso.escolaapi.repository.TurmaRepository;
import app.netlify.iambarroso.escolaapi.repository.spec.TurmaSpec;
import app.netlify.iambarroso.escolaapi.repository.spec.filter.TurmaFilter;
import app.netlify.iambarroso.escolaapi.rest.dto.PageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TurmaService {

    private final TurmaRepository repository;

    public PageDto<Turma> findAll(TurmaFilter filtro, Pageable pageable) {
        Page<Turma> page = repository.findAll(TurmaSpec.createPredicate(filtro), pageable);
        return PageDto.fromPage(page);
    }

    public Turma findById(Long id) {
        return repository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(NotFoundException.TURMA_NOT_FOUND));
    }

    public Turma save(Turma turma) {
        return repository.save(turma);
    }

    public void delete(Long id) {
        Turma turma = findById(id);
        repository.delete(turma);
    }
}
