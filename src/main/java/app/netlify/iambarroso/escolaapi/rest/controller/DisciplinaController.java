package app.netlify.iambarroso.escolaapi.rest.controller;

import app.netlify.iambarroso.escolaapi.domain.model.Disciplina;
import app.netlify.iambarroso.escolaapi.repository.spec.filter.DisciplinaFilter;
import app.netlify.iambarroso.escolaapi.rest.dto.PageDto;
import app.netlify.iambarroso.escolaapi.service.DisciplinaService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/disciplinas")
@RequiredArgsConstructor
public class DisciplinaController {

    private final DisciplinaService service;

    @GetMapping
    public PageDto<Disciplina> findAll(
            @PageableDefault Pageable pageable,
            @RequestParam(required = false) String nome
    ) {
        DisciplinaFilter filtro = new DisciplinaFilter(nome);
        return service.findAll(filtro, pageable);
    }

    @GetMapping(path = "{id}")
    public Disciplina findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Disciplina create(@Valid @RequestBody Disciplina disciplina) {
        return service.save(disciplina);
    }

    @PutMapping(path = "{id}")
    public Disciplina update(@PathVariable Long id, @RequestBody Disciplina disciplina) {
        disciplina.setId(id);
        return service.save(disciplina);
    }

    @DeleteMapping(path = "{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
