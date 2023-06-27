package app.netlify.iambarroso.escolaapi.service;

import app.netlify.iambarroso.escolaapi.domain.model.Aula;
import app.netlify.iambarroso.escolaapi.domain.model.Professor;
import app.netlify.iambarroso.escolaapi.domain.model.Turma;
import app.netlify.iambarroso.escolaapi.exceptions.BusinessException;
import app.netlify.iambarroso.escolaapi.exceptions.NotFoundException;
import app.netlify.iambarroso.escolaapi.repository.AulaRepository;
import app.netlify.iambarroso.escolaapi.repository.spec.AulaSpec;
import app.netlify.iambarroso.escolaapi.repository.spec.filter.AulaFilter;
import app.netlify.iambarroso.escolaapi.rest.dto.AulaSaveDto;
import app.netlify.iambarroso.escolaapi.rest.dto.AulaShortDto;
import app.netlify.iambarroso.escolaapi.rest.dto.PageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AulaService {

    private final AulaRepository repository;
    private final ProfessorService professorService;
    private final TurmaService turmaService;

    public PageDto<AulaShortDto> findAll(AulaFilter filtro, Pageable pageable) {
        Page<Aula> page = repository.findAll(AulaSpec.createPredicate(filtro), pageable);
        return AulaShortDto.createPageDto(PageDto.fromPage(page));
    }

    public Aula findById(Long id) {
        return repository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(NotFoundException.AULA_NOT_FOUND));
    }

    public Aula save(AulaSaveDto aula) {
        validateAula(aula);
        Aula entity = aula.toEntity();
        Professor professor = professorService.findById(aula.getProfessorId());
        Turma turma = turmaService.findById(aula.getTurmaId());
        entity.setProfessor(professor);
        entity.setTurma(turma);
        return repository.save(entity);
    }

    public void delete(Long id) {
        Aula aula = findById(id);
        repository.delete(aula);
    }

    private void validateAula(AulaSaveDto aula) {
        if (aula.getId() != null) findById(aula.getId());
        if (aula.getHoraEntrada().isAfter(aula.getHoraSaida()))
            throw new BusinessException("Hora de entrada não pode ser posterior à de saída");
    }
}
