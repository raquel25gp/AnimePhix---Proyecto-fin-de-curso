package com.animephix.backend.model;

import com.animephix.backend.model.compositePK.FavoritoAnimeUsuarioId;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@IdClass(FavoritoAnimeUsuarioId.class)
@Table(name = "favoritoAnimeUsuario")
public class FavoritoAnimeUsuario {
    @Id
    private Long usuario_id;
    @Id
    private Long anime_id;
    @Column(nullable = false)
    private boolean habilitado = true;

    @ManyToOne
    @MapsId("usuario_id")
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToOne
    @MapsId("anime_id")
    @JoinColumn(name = "anime_id")
    private Anime anime;
}
