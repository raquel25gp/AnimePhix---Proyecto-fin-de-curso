package com.animephix.backend.repository;

import com.animephix.backend.model.Anime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AnimeRepository extends JpaRepository<Anime, Long> {
    @Query("Select a FROM Anime a WHERE a.genero.id = ?1")
    boolean buscarGeneroPorId(Long idGenero);

    @Query("Select a FROM Anime a WHERE a.estado.id = ?1")
    boolean buscarEstadoPorId(Long idEstado);
}
