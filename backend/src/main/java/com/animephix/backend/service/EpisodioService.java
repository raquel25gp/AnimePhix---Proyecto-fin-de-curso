package com.animephix.backend.service;

import com.animephix.backend.model.Anime;
import com.animephix.backend.model.Episodio;
import com.animephix.backend.model.compositePK.EpisodioId;
import com.animephix.backend.repository.AnimeRepository;
import com.animephix.backend.repository.EpisodioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class EpisodioService {
    private EpisodioRepository episodioRepository;
    private AnimeRepository animeRepository;

    @Autowired
    public EpisodioService (EpisodioRepository episodioRepository, AnimeRepository animeRepository) {
        this.episodioRepository = episodioRepository;
        this.animeRepository = animeRepository;
    }

    //Operaciones CRUD
    @Transactional
    public Episodio crear (Episodio episodio) {
        return episodioRepository.save(episodio);
    }

    @Transactional(readOnly = true)
    public List<Episodio> listarTodos() {
        return episodioRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Episodio> listarPorAnimeId (Long idAnime) {
        Optional<Anime> animeOptional = animeRepository.findById(idAnime);
        if (animeOptional.isPresent()) {
            return episodioRepository.buscarEpisodiosPorAnime(idAnime);
        } else {
            throw new RuntimeException("Ese anime no existe");
        }
    }

    @Transactional
    public Episodio actualizar (Episodio episodio, Long idAnime, Long idEpisodio) {
        Optional<Anime> animeOptional = animeRepository.findById(idAnime);
        if (animeOptional.isPresent()) {
            EpisodioId episodioId = new EpisodioId(idAnime, idEpisodio);
            Optional<Episodio> episodioOptional = episodioRepository.findById(episodioId);
            if (episodioOptional.isPresent()) {
                Episodio episodioDB = episodioOptional.get();
                if (Objects.nonNull(episodio.getUrl()) && !"".equalsIgnoreCase(episodio.getUrl())) {
                    episodioDB.setUrl(episodio.getUrl());
                }
                episodioRepository.save(episodioDB);
                return episodioDB;
            } else {
                throw new RuntimeException("Ese episodio no existe para este anime");
            }
        } else {
            throw new RuntimeException("Ese anime no existe");
        }
    }

    @Transactional
    public String eliminarUnEpisodioPorId(Long idAnime, Long idEpisodio) {
        Optional<Anime> animeOptional = animeRepository.findById(idAnime);
        if (animeOptional.isPresent()) {
            EpisodioId episodioId = new EpisodioId(idAnime, idEpisodio);
            Optional<Episodio> episodioOptional = episodioRepository.findById(episodioId);
            if (episodioOptional.isPresent()) {
                episodioRepository.deleteById(episodioId);
                return "Se ha eliminado el episodio " + idEpisodio + " del anime " + animeOptional.get().getNombre();
            } else {
                return "Ese episodio no existe para este anime";
            }
        } else {
            return "Ese anime no existe";
        }
    }
}
