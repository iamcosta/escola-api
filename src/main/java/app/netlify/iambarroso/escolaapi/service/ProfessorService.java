package app.netlify.iambarroso.escolaapi.service;

import app.netlify.iambarroso.escolaapi.domain.model.Disciplina;
import app.netlify.iambarroso.escolaapi.domain.model.Professor;
import app.netlify.iambarroso.escolaapi.exceptions.NotFoundException;
import app.netlify.iambarroso.escolaapi.repository.ProfessorRepository;
import app.netlify.iambarroso.escolaapi.repository.spec.ProfessorSpec;
import app.netlify.iambarroso.escolaapi.repository.spec.filter.ProfessorFilter;
import app.netlify.iambarroso.escolaapi.rest.dto.PageDto;
import app.netlify.iambarroso.escolaapi.rest.dto.ProfessorSaveDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfessorService {

    private final ProfessorRepository repository;
    private final DisciplinaService disciplinaService;

    public PageDto<Professor> findAll(ProfessorFilter filtro, Pageable pageable) {
        Page<Professor> page = repository
                .findAll(ProfessorSpec.createPredicate(filtro), pageable);
        return PageDto.fromPage(page);
    }

    public Professor findById(Long id) {
        return repository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(NotFoundException.PROFESSOR_NOT_FOUND));
    }

    public Professor save(ProfessorSaveDto professor) {
        if (professor.getId() != null) findById(professor.getId());
        Disciplina disciplina = disciplinaService.findById(professor.getDisciplinaId());
        Professor entity = professor.toEntity();
        entity.setDisciplina(disciplina);
        return repository.save(entity);
    }

    public void delete(Long id) {
        Professor professor = findById(id);
        repository.delete(professor);
    }
}
