package com.animephix.backend.repository;

import com.animephix.backend.model.ComentaEpisodioUsuario;
import com.animephix.backend.model.compositePK.EpisodioUsuarioId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ComentaEpisodioUsuarioRepository extends JpaRepository<ComentaEpisodioUsuario, EpisodioUsuarioId> {

    @Query("Select c FROM ComentaEpisodioUsuario c WHERE c.usuario_id = ?1")
    List<ComentaEpisodioUsuario> buscarTodosPorUsuario(Long idUsuario);

    List<ComentaEpisodioUsuario> findAllById(EpisodioUsuarioId comentaId);

    @Query("Select c FROM ComentaEpisodioUsuario c WHERE c.anime_id = ?1 = c.numEpisodio = ?2")
    List<ComentaEpisodioUsuario> buscarTodosPorAnimeYEpisodio(Long idAnime, Long numEpisodio);

    Optional<ComentaEpisodioUsuario> findById(EpisodioUsuarioId comentaId);

    void deleteById(EpisodioUsuarioId comentaId);
}
