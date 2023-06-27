package app.netlify.iambarroso.escolaapi.service;

import app.netlify.iambarroso.escolaapi.domain.model.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UsuarioService usuarioService;

    @Override
    public UserDetails loadUserByUsername(String username) {
        Usuario user = usuarioService.findByLogin(username);
        return new User(
                user.getLogin(), user.getSenha(), new ArrayList<>());
    }
}
