package com.harun.director.mapper;

import com.harun.directorserviceapi.dto.DirectorDTO;
import com.harun.director.model.Director;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface MapperGenerator {
    Director directorDTOToDirector(DirectorDTO directorDTO);

    DirectorDTO directorToDirectorDTO(Director director);

    List<DirectorDTO> directorToDirectorDTO(List<Director> movie);
    @Mapping(target = "id", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Director updateDirectorFromDTO(DirectorDTO directorDTO, @MappingTarget() Director director);

}