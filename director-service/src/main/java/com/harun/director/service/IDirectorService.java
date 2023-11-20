package com.harun.director.service;

import com.harun.common.base.BaseService;
import com.harun.director.dto.DirectorDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IDirectorService extends BaseService<DirectorDTO, Long> {
    Page<DirectorDTO> filter(Pageable pageable, DirectorDTO directorDTO);

    Page<DirectorDTO> getAll(Pageable pageable);
}
