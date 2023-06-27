package app.netlify.iambarroso.escolaapi.rest.controller;

import app.netlify.iambarroso.escolaapi.domain.enums.DiaSemana;
import app.netlify.iambarroso.escolaapi.domain.model.Aula;
import app.netlify.iambarroso.escolaapi.repository.spec.filter.AulaFilter;
import app.netlify.iambarroso.escolaapi.rest.dto.AulaSaveDto;
import app.netlify.iambarroso.escolaapi.rest.dto.AulaShortDto;
import app.netlify.iambarroso.escolaapi.rest.dto.PageDto;
import app.netlify.iambarroso.escolaapi.service.AulaService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/aulas")
@RequiredArgsConstructor
public class AulaController {

    private final AulaService service;

    @GetMapping
    public PageDto<AulaShortDto> findAll(
            @PageableDefault Pageable pageable,
            @RequestParam(required = false) String professor,
            @RequestParam(required = false) String turma,
            @RequestParam(required = false, name = "diasemana") DiaSemana diaSemana,
            @RequestParam(required = false) String disciplina
    ) {
        AulaFilter filtro = new AulaFilter(professor, turma, diaSemana, disciplina);
        return service.findAll(filtro, pageable);
    }

    @GetMapping(path = "{id}")
    public Aula findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Aula create(@RequestBody AulaSaveDto aula) {
        return service.save(aula);
    }

    @PutMapping(path = "{id}")
    public Aula update(@PathVariable Long id, @RequestBody AulaSaveDto aula) {
        aula.setId(id);
        return service.save(aula);
    }

    @DeleteMapping(path = "{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
