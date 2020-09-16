package br.gov.sp.fatec.springbootapp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.test.annotation.Rollback;

import br.gov.sp.fatec.springbootapp.repository.UsuarioRepository;
import br.gov.sp.fatec.springbootapp.entity.Usuario;

@SpringBootTest
@Transactional
@Rollback
class SpringBootAppApplicationTests {

    @Autowired
    private UsuarioRepository usuarioRepo;

    @Test
    void contextLoads() {
    }

    @Test
    void testeInsercao() {

        Usuario usuario = new Usuario();
        usuario.setNome("Guilherme");
        usuario.setSenha("senha");
        usuarioRepo.save(usuario);
        assertNotNull(usuario.getId());

    }

    @Test
    void testeAutorizacao() {

        Usuario usuario = usuarioRepo.findById(1L).get();
        assertEquals("Role_Admin", usuario.getAutorizacoes().iterator().next().getNome());
       

    }

}
