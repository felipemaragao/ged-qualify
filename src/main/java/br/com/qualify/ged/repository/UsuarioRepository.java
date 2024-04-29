package br.com.qualify.ged.repository;

import br.com.qualify.ged.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email);
    public List<Usuario> findByPermissoesDescricao(String permissaoDescricao);
}
