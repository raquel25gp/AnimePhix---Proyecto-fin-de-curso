package com.animephix.backend.repository;

import com.animephix.backend.model.Episodio;
import com.animephix.backend.model.compositePK.EpisodioId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EpisodioRepository extends JpaRepository<Episodio, EpisodioId> {
    @Query("Select e FROM Episodio e WHERE e.anime_id = ?1")
    List<Episodio> buscarEpisodiosPorAnime(Long idAnime);

    Optional<Episodio> findById(EpisodioId id);

    void deleteById(EpisodioId id);
}
