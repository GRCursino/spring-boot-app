package br.gov.sp.fatec.springbootapp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.test.annotation.Rollback;

import br.gov.sp.fatec.springbootapp.repository.UsuarioRepository;
import br.gov.sp.fatec.springbootapp.repository.AutorizacaoRepository;
import br.gov.sp.fatec.springbootapp.entity.Usuario;
import br.gov.sp.fatec.springbootapp.entity.Autorizacao;

@SpringBootTest
//@Transactional
//@Rollback
class SpringBootAppApplicationTests {

    @Autowired
    private UsuarioRepository usuarioRepo;

    @Autowired
    private AutorizacaoRepository autRepo;

   @Test
    void contextLoads() {
    }

    @Test
    void testeInsercao() {

        Usuario usuario = new Usuario();
        usuario.setNome("Usuario");
        usuario.setSenha("senha");
        usuario.setAutorizacoes(new HashSet<Autorizacao>());
        Autorizacao aut = new Autorizacao();
        aut.setNome("ROLE_USUARIO");
        autRepo.save(aut);
        usuario.getAutorizacoes().add(aut);
        usuarioRepo.save(usuario);
        assertNotNull(usuario.getAutorizacoes().iterator().next().getId());

    }

    @Test
    void testeInsercaoAutorizacao() {

        Usuario usuario = new Usuario();
        usuario.setNome("Usuario2");
        usuario.setSenha("senha");
        usuarioRepo.save(usuario);
        
        Autorizacao aut = new Autorizacao();
        aut.setNome("ROLE_USUARIO2");
        aut.setUsuario(new HashSet<Usuario>());
        aut.getUsuarios().add(usuario);
        autRepo.save(aut);
        assertNotNull(aut.getUsuarios().iterator().next().getId());

    }
    
    @Test
    void testeUsuario() {

        Usuario usuario = usuarioRepo.findById(1L).get();
        assertEquals("ROLE_ADMIN", usuario.getAutorizacoes().iterator().next().getNome());
       

    }

    @Test
    void testeAutorizacao() {

        Autorizacao aut = autRepo.findById(1L).get();
        assertEquals("Guilherme", aut.getUsuarios().iterator().next().getNome());
       

    }


}



