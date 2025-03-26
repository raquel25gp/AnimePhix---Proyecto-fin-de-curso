package com.animephix.backend.model.compositePK;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EpisodioUsuarioId implements Serializable {
    private Long usuario_id;
    private Long anime_id;
    private Long num_episodio;
}
