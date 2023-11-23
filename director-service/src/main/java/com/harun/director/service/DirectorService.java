package com.harun.director.service;


import com.harun.common.enums.StatusEnum;
import com.harun.directorserviceapi.dto.DirectorDTO;
import com.harun.director.mapper.MapperGenerator;
import com.harun.director.mapper.MapperGeneratorSingleton;
import com.harun.director.mapper.PageMapper;
import com.harun.director.model.Director;
import com.harun.director.repository.DirectorRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class DirectorService implements IDirectorService {
    private static final Logger logger = LoggerFactory.getLogger(DirectorService.class);
    MapperGenerator mapper = MapperGeneratorSingleton.INSTANCE;

    private final DirectorRepository directorRepository;

    @Override
    public DirectorDTO save(DirectorDTO directorDTO) {
        Director director = mapper.directorDTOToDirector(directorDTO);
        directorRepository.save(director);
        logger("save", director.getId());
        return get(director.getId());
    }

    @Override
    public DirectorDTO update(DirectorDTO directorDTO, Long id) {
        directorRepository.save(mapper.updateDirectorFromDTO(directorDTO, getDirectorById(id)));
        logger("update", id);
        return get(id);
    }

    @Override
    public DirectorDTO get(Long id) {
        Director director = getDirectorById(id);
        return mapper.directorToDirectorDTO(director);
    }

    @Override
    public void delete(Long id) {
        Director director = getDirectorById(id);
        director.setStatus(StatusEnum.DELETED);
        directorRepository.save(director);
    }

    @Override
    public Page<DirectorDTO> filter(Pageable pageable, DirectorDTO directorDTO) {
        Page<Director> page = directorRepository.findByFilter(pageable, directorDTO);
        List<DirectorDTO> directorDTOList = mapper.directorToDirectorDTO(page.getContent());
        return PageMapper.toPage(page, directorDTOList);
    }

    @Override
    public Page<DirectorDTO> getAll(Pageable pageable) {
        Page<Director> directorPage = directorRepository.findAll(pageable);
        List<DirectorDTO> directorDTOList = mapper.directorToDirectorDTO(directorPage.getContent());
        return new PageImpl<>(directorDTOList, pageable, directorPage.getTotalElements());
    }

    private Director getDirectorById(Long id) {
        return directorRepository.findById(id)
                .orElseThrow(() -> new NullPointerException("Director not exist with id: " + id));
    }
    private void logger(String action, Long id) {
        if (logger.isInfoEnabled())
            logger.info("Director " + action + " with id {}", id);
        else
            logger.debug("Director " + action + " successfully with id {}", id);
    }
}
