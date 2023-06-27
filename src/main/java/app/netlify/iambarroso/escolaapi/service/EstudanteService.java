package app.netlify.iambarroso.escolaapi.service;

import app.netlify.iambarroso.escolaapi.domain.model.Disciplina;
import app.netlify.iambarroso.escolaapi.domain.model.Estudante;
import app.netlify.iambarroso.escolaapi.domain.model.Turma;
import app.netlify.iambarroso.escolaapi.exceptions.NotFoundException;
import app.netlify.iambarroso.escolaapi.repository.DisciplinaRepository;
import app.netlify.iambarroso.escolaapi.repository.EstudanteRepository;
import app.netlify.iambarroso.escolaapi.repository.TurmaRepository;
import app.netlify.iambarroso.escolaapi.repository.spec.EstudanteSpec;
import app.netlify.iambarroso.escolaapi.repository.spec.filter.EstudanteFilter;
import app.netlify.iambarroso.escolaapi.rest.dto.EstudanteSaveDto;
import app.netlify.iambarroso.escolaapi.rest.dto.PageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EstudanteService {

    private final EstudanteRepository repository;
    private final TurmaService turmaService;

    public PageDto<Estudante> findAll(EstudanteFilter filtro, Pageable pageable) {
        Page<Estudante> page = repository.findAll(EstudanteSpec.createPredicate(filtro), pageable);
        return PageDto.fromPage(page);
    }

    public Estudante findById(Long id) {
        return repository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(NotFoundException.ESTUDANTE_NOT_FOUND));
    }

    public Estudante save(EstudanteSaveDto estudante) {
        if (estudante.getId() != null) findById(estudante.getId());
        Turma turma = turmaService.findById(estudante.getTurmaId());
        Estudante entity = estudante.toEntity();
        entity.setTurma(turma);
        return repository.save(entity);
    }

    public void delete(Long id) {
        Estudante estudante = findById(id);
        repository.delete(estudante);
    }
}
