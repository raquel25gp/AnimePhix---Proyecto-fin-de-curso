package com.animephix.backend.model;

import com.animephix.backend.model.compositePK.EpisodioId;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
@IdClass(EpisodioId.class)
@Table(name = "episodio")
public class Episodio {
    @Id
    private Long anime_id;
    @Id
    private Long num_episodio;
    @Size(min = 3, max = 200)
    private String url;

    @ManyToOne
    @MapsId("anime_id")
    @JoinColumn(name = "anime_id")
    private Anime anime;
}
