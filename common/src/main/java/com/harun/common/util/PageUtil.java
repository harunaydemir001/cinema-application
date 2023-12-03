package com.harun.common.util;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;

public class PageUtil {
    public static Page<String> EmptyPage(Pageable pageable) {
        var list = new ArrayList<String>();
        list.add("Empty Page");
        return new PageImpl<>(list, pageable, list.size());
    }
}
