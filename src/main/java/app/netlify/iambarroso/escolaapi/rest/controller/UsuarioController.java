package app.netlify.iambarroso.escolaapi.rest.controller;

import app.netlify.iambarroso.escolaapi.domain.model.Usuario;
import app.netlify.iambarroso.escolaapi.rest.dto.UsuarioDto;
import app.netlify.iambarroso.escolaapi.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService service;

    @PostMapping
    public UsuarioDto create(@RequestBody Usuario usuario) {
        return service.save(usuario);
    }

}
