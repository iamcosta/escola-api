package app.netlify.iambarroso.escolaapi.rest.controller;

import app.netlify.iambarroso.escolaapi.rest.dto.UsuarioLoginDto;
import app.netlify.iambarroso.escolaapi.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService service;

    @PostMapping(path = "/login")
    public String login(@RequestBody UsuarioLoginDto usuarioLogin) {
       return service.login(usuarioLogin.getLogin(), usuarioLogin.getSenha());
    }
}
