package app.netlify.iambarroso.escolaapi.service;

import app.netlify.iambarroso.escolaapi.domain.model.Disciplina;
import app.netlify.iambarroso.escolaapi.exceptions.NotFoundException;
import app.netlify.iambarroso.escolaapi.repository.DisciplinaRepository;
import app.netlify.iambarroso.escolaapi.repository.spec.DisciplinaSpec;
import app.netlify.iambarroso.escolaapi.repository.spec.filter.DisciplinaFilter;
import app.netlify.iambarroso.escolaapi.rest.dto.PageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DisciplinaService {

    private final DisciplinaRepository repository;

    public PageDto<Disciplina> findAll(DisciplinaFilter filtro, Pageable pageable) {
        Page<Disciplina> page = repository.findAll(DisciplinaSpec.createPredicate(filtro), pageable);
        return PageDto.fromPage(page);
    }

    public Disciplina findById(Long id) {
        return repository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(NotFoundException.DISCIPLINA_NOT_FOUND));
    }

    public Disciplina save(Disciplina disciplina) {
        if (disciplina.getId() != null) findById(disciplina.getId());
        return repository.save(disciplina);
    }

    public void delete(Long id) {
        Disciplina disciplina = findById(id);
        repository.delete(disciplina);
    }
}
