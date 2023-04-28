package com.example.shopping.common.mapper;

import com.example.shopping.common.entity.SysUi;
import org.springframework.stereotype.Repository;

@Repository
public interface SysUiMapper {
    SysUi findByMark(int width, int height);

    int updateUrl(String url, int width, int height);

    int insert(String url, int width, int height);

    int deleteByMark(int width, int height);
}
