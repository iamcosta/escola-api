package app.netlify.iambarroso.escolaapi.rest.controller;

import app.netlify.iambarroso.escolaapi.domain.model.Professor;
import app.netlify.iambarroso.escolaapi.repository.spec.filter.ProfessorFilter;
import app.netlify.iambarroso.escolaapi.rest.dto.PageDto;
import app.netlify.iambarroso.escolaapi.rest.dto.ProfessorSaveDto;
import app.netlify.iambarroso.escolaapi.service.ProfessorService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/professores")
@RequiredArgsConstructor
public class ProfessorController {

    private final ProfessorService service;

    @GetMapping
    public PageDto<Professor> findAll(
            @PageableDefault Pageable pageable,
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) String disciplina
    ) {
        ProfessorFilter filtro = new ProfessorFilter(nome, disciplina);
        return service.findAll(filtro, pageable);
    }

    @GetMapping(path = "{id}")
    public Professor findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Professor create(@Valid @RequestBody ProfessorSaveDto professor) {
        return service.save(professor);
    }

    @PutMapping(path = "{id}")
    public Professor update(@PathVariable Long id, @Valid @RequestBody ProfessorSaveDto professor) {
        professor.setId(id);
        return service.save(professor);
    }

    @DeleteMapping(path = "{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
