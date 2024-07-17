package com.challenge.forohub.controller;

import com.challenge.forohub.domain.usuario.DatosAutenticacionUsuario;
import com.challenge.forohub.domain.usuario.Usuario;
import com.challenge.forohub.infra.security.DatosTokenJWT;
import com.challenge.forohub.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacionController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity autenticarUsuario(@RequestBody @Valid DatosAutenticacionUsuario datosAutenticacionUsuario) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(datosAutenticacionUsuario.login(), datosAutenticacionUsuario.clave());
        var authentication =  authenticationManager.authenticate(authenticationToken);
        var tokenJWT = tokenService.generarToken((Usuario) authentication.getPrincipal());
        return ResponseEntity.ok(new DatosTokenJWT(tokenJWT));
    }

}
