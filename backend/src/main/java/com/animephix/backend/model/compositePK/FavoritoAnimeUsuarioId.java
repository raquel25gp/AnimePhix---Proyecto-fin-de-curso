package com.animephix.backend.model.compositePK;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FavoritoAnimeUsuarioId implements Serializable {
    private Long usuario_id;
    private Long anime_id;
}
