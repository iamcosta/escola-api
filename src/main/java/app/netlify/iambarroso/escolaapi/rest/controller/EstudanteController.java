package app.netlify.iambarroso.escolaapi.rest.controller;

import app.netlify.iambarroso.escolaapi.domain.model.Estudante;
import app.netlify.iambarroso.escolaapi.repository.spec.filter.EstudanteFilter;
import app.netlify.iambarroso.escolaapi.rest.dto.EstudanteSaveDto;
import app.netlify.iambarroso.escolaapi.rest.dto.PageDto;
import app.netlify.iambarroso.escolaapi.service.EstudanteService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/estudantes")
@RequiredArgsConstructor
public class EstudanteController {

    private final EstudanteService service;

    @GetMapping
    public PageDto<Estudante> findAll(
            @PageableDefault Pageable pageable,
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) String matricula,
            @RequestParam(required = false) String turma
    ) {
        EstudanteFilter filtro = new EstudanteFilter(nome, matricula, turma);
        return service.findAll(filtro, pageable);
    }

    @GetMapping(path = "{id}")
    public Estudante findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Estudante create(@Valid @RequestBody EstudanteSaveDto estudante) {
        return service.save(estudante);
    }

    @PutMapping(path = "{id}")
    public Estudante update(@PathVariable Long id, @Valid @RequestBody EstudanteSaveDto estudante) {
        estudante.setId(id);
        return service.save(estudante);
    }

    @DeleteMapping(path = "{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
