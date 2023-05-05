package com.example.shopping.common.mapper;

import com.example.shopping.common.entity.SysMt;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysMtMapper {
    int insert(String name, String email, String password, int state);

    SysMt findByEmail(String email);

    SysMt findById(int id);

    int count();

    List<SysMt> findPage(int curRow, int pageSize);

    List<SysMt> findAll();

    int deleteById(int id);

    int updateState(String email, int state);

    int update(String name, String password, String email);
}
