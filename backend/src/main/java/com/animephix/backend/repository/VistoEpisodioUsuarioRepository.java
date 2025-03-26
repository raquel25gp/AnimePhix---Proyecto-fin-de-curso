package com.animephix.backend.repository;

import com.animephix.backend.model.FavoritoAnimeUsuario;
import com.animephix.backend.model.VistoEpisodioUsuario;
import com.animephix.backend.model.compositePK.EpisodioUsuarioId;
import com.animephix.backend.model.compositePK.FavoritoAnimeUsuarioId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VistoEpisodioUsuarioRepository extends JpaRepository<VistoEpisodioUsuario, EpisodioUsuarioId> {
    @Query("Select v FROM VistoEpisodioUsuario v WHERE v.usuario_id = ?1")
    List<VistoEpisodioUsuario> buscarTodosPorUsuario(Long idUsuario);

    @Query("Select v FROM VistoEpisodioUsuario v WHERE v.usuario_id = ?1 AND v.anime_id = ?2")
    List<VistoEpisodioUsuario> buscarTodosPorUsuarioYAnime(Long idUsuario, Long idAnime);

    Optional<VistoEpisodioUsuario> findById(EpisodioUsuarioId vistoId);

    void deleteById(EpisodioUsuarioId vistoId);
}
