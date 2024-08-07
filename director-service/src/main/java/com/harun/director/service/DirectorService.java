package com.harun.director.service;


import com.harun.common.enums.StatusEnum;
import com.harun.common.util.ResponseExceptionUtil;
import com.harun.director.constant.DirectorErrorCodeConstant;
import com.harun.director.mapper.MapperGenerator;
import com.harun.director.mapper.MapperGeneratorSingleton;
import com.harun.director.mapper.PageMapper;
import com.harun.director.model.Director;
import com.harun.director.projection.IdProjection;
import com.harun.director.repository.DirectorRepository;
import com.harun.directorserviceapi.dto.DirectorDTO;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
@RequiredArgsConstructor
public class DirectorService implements IDirectorService {
    private static final Logger logger = LoggerFactory.getLogger(DirectorService.class);
    MapperGenerator mapper = MapperGeneratorSingleton.INSTANCE;

    private final DirectorRepository directorRepository;
    private final ResponseExceptionUtil responseExceptionUtil;

    @Override
    public DirectorDTO save(DirectorDTO directorDTO) {
        Director director = mapper.directorDTOToDirector(directorDTO);
        return mapper.directorToDirectorDTO(directorRepository.save(director));
    }

    @Override
    @CachePut(value = "director", key = "#result.id")
    @TimeLimiter(name = "timeLimiterApi")
    public DirectorDTO update(DirectorDTO directorDTO, Long id) {
        if(directorDTO.getEmail().contains("fdg")){
            responseExceptionUtil.throwResponseException(HttpStatus.NOT_ACCEPTABLE, DirectorErrorCodeConstant.EMAIL_CANT_CONTAINS_FDG);
        }
        Director incomingDirector = directorRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        Director director = mapper.updateDirectorFromDTO(directorDTO, incomingDirector);
        return mapper.directorToDirectorDTO(directorRepository.save(director));
    }

    @Override
    @Cacheable(value = "director", key = "#id")
    @Retry(name = "retryApi")
    public DirectorDTO get(Long id) {
        int a = 10;
        int b =  a;
        a = 20;
        System.out.println(a);
        System.out.println(b);

        Map<String ,Integer> amap= new HashMap<String, Integer>();
        amap.put("value", 10);
        Map<String ,Integer> bmap= new HashMap<String, Integer>();
        bmap = amap;

        amap.put("value", 20);
        System.out.println(amap);
        System.out.println(bmap);
        Director director = directorRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        return mapper.directorToDirectorDTO(director);
    }

    @Override
    @CacheEvict(value = "director", key = "#id")
    public void delete(Long id) {
        Director director = directorRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        director.setStatus(StatusEnum.DELETED);
        if (logger.isInfoEnabled())
            logger.info("Director status change to " + StatusEnum.DELETED + " with id: " + id);
        directorRepository.save(director);
    }

    @Override
    @RateLimiter(name = "rateLimiterApi")
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

    @Override
    public Collection<IdProjection> findIdByName(String name) {
        return directorRepository.findIdByName(name);
    }
}
