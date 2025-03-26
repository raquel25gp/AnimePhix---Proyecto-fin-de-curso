package com.animephix.backend.service;

import com.animephix.backend.model.Anime;
import com.animephix.backend.repository.AnimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class AnimeService {
    private AnimeRepository animeRepository;

    @Autowired
    public AnimeService (AnimeRepository animeRepository) {
        this.animeRepository = animeRepository;
    }

    //Operaciones CRUD
    public Anime crear (Anime nuevoAnime) {
        return animeRepository.save(nuevoAnime);
    }

    public List<Anime> listarTodos() {
        return animeRepository.findAll();
    }

    public Anime listarPorId (Long idAnime) {
        Optional<Anime> animeOptional = animeRepository.findById(idAnime);
        if (animeOptional.isPresent()) {
            Anime anime = animeOptional.get();
            return anime;
        } else {
            throw new RuntimeException("Ese anime no existe");
        }
    }

    public Anime actualizar(Anime anime, Long idAnime) {
        Optional<Anime> animeOptional = animeRepository.findById(idAnime);
        if (animeOptional.isPresent()) {
            Anime animeDB = animeOptional.get();

            if (Objects.nonNull(anime.getNombre()) && !"".equalsIgnoreCase(anime.getNombre())) {
                animeDB.setNombre(anime.getNombre());
            }
            if (Objects.nonNull(anime.getDescripcion()) && !"".equalsIgnoreCase(anime.getDescripcion())) {
                animeDB.setDescripcion(anime.getDescripcion());
            }
            if (Objects.nonNull(anime.getFechaInicio())) {
                animeDB.setFechaInicio(anime.getFechaInicio());
            }
            if (Objects.nonNull(anime.getFechaFin())) {
                animeDB.setFechaFin(anime.getFechaFin());
            }
            if (Objects.nonNull(anime.getDiaSemana())) {
                animeDB.setDiaSemana(anime.getDiaSemana());
            }
            if (!anime.isVisible()) {
                animeDB.setVisible(true);
            }

            if (animeRepository.buscarGeneroPorId(anime.getGenero().getId_genero())) {
                animeDB.setGenero(anime.getGenero());
            } else {
                throw new RuntimeException("Ese género no existe");
            }

            if (animeRepository.buscarEstadoPorId(anime.getEstado().getId_estado())) {
                animeDB.setEstado(anime.getEstado());
            } else {
                throw new RuntimeException("Ese género no existe");
            }

            animeRepository.save(animeDB);
            return animeDB;
        } else {
            throw new RuntimeException("Ese anime no existe");
        }
    }

    public String eliminarPorId(Long idAnime) {
        Optional<Anime> animeOptional = animeRepository.findById(idAnime);
        if (animeOptional.isPresent()) {
            animeRepository.deleteById(idAnime);
            return "Se ha eliminado: " + animeOptional.get().getNombre();
        } else {
            return "El anime indicado no existe.";
        }
    }
}
