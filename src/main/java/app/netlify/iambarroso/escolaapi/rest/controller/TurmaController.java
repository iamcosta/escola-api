package app.netlify.iambarroso.escolaapi.rest.controller;

import app.netlify.iambarroso.escolaapi.domain.model.Turma;
import app.netlify.iambarroso.escolaapi.repository.spec.filter.TurmaFilter;
import app.netlify.iambarroso.escolaapi.rest.dto.PageDto;
import app.netlify.iambarroso.escolaapi.service.TurmaService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/turmas")
@RequiredArgsConstructor
public class TurmaController {

    private final TurmaService service;

    @GetMapping
    @Secured({"ADMIN"})
    public PageDto<Turma> findAll(
            @PageableDefault Pageable pageable,
            @RequestParam(required = false) Integer ano,
            @RequestParam(required = false) String descricao
    ) {
        TurmaFilter filtro = new TurmaFilter(ano, descricao);
        return service.findAll(filtro, pageable);
    }

    @GetMapping(path = "{id}")
    public Turma findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Turma create(@RequestBody Turma turma) {
        return service.save(turma);
    }

    @PutMapping(path = "{id}")
    public Turma update(@PathVariable Long id, @RequestBody Turma turma) {
        turma.setId(id);
        return service.save(turma);
    }

    @DeleteMapping(path = "{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
