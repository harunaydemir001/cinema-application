package com.harun.movie.dto;

import com.harun.actorserviceapi.dto.ActorDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(setterPrefix = "with")
public class MovieAndActorDTO {
    private List<ActorDTO> actorDTOList;
    private MovieDTO movieDTO;
}
