package com.harun.movie.dto;

import com.harun.actorserviceapi.dto.ActorDTO;
import lombok.Data;

import java.util.List;

@Data
public class MovieAndActorDTO {
    private List<ActorDTO> actorDTOList;
    private MovieDTO movieDTO;
}
