package com.harun.movie.mapper;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;

public final class PageMapper {

    private PageMapper() {
    }

    public static <T, E> Page<E> toPage(Page<T> page, List<E> content) {
        return new PageImpl<>(content, page.getPageable(), page.getTotalElements());
    }
}
