package com.harun.director.service;

import com.harun.common.base.BaseService;
import com.harun.director.projection.IdProjection;
import com.harun.directorserviceapi.dto.DirectorDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collection;

/**
 * Access Layer for {@link DirectorService}
 */

public interface IDirectorService extends BaseService<DirectorDTO, Long> {
    Page<DirectorDTO> filter(Pageable pageable, DirectorDTO directorDTO);

    Page<DirectorDTO> getAll(Pageable pageable);
    Collection<IdProjection> findIdByName(String name);
}
