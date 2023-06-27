package app.netlify.iambarroso.escolaapi.service;

import app.netlify.iambarroso.escolaapi.domain.model.Usuario;
import app.netlify.iambarroso.escolaapi.exceptions.NotFoundException;
import app.netlify.iambarroso.escolaapi.repository.UsuarioRepository;
import app.netlify.iambarroso.escolaapi.rest.dto.UsuarioDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository repository;
    private final PasswordEncoder passwordEncoder;

    public Usuario findByLogin(String login) {
        return repository
                .findByLogin(login)
                .orElseThrow(() -> new NotFoundException("Usuario não encontrado"));
    }

    public UsuarioDto save(Usuario usuario) {
        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        return UsuarioDto.fromEntity(repository.save(usuario));
    }
}
