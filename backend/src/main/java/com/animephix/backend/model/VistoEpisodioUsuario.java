package com.animephix.backend.model;

import com.animephix.backend.model.compositePK.EpisodioUsuarioId;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@IdClass(EpisodioUsuarioId.class)
@Table(name = "vistoEpisodioUsuario")
public class VistoEpisodioUsuario {
    @Id
    private Long usuario_id;
    @Id
    private Long anime_id;
    @Id
    private Long num_episodio;
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

    @ManyToOne
    @MapsId("num_episodio")
    @JoinColumns({
            @JoinColumn(name = "anime_id", referencedColumnName = "anime_id", insertable = false, updatable = false),
            @JoinColumn(name = "num_episodio", referencedColumnName = "num_episodio")})
    private Episodio episodio;
}
