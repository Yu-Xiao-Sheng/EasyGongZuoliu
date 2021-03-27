package com.yg.dao;

import com.yg.domain.Manager;

import java.util.List;

public interface ManagerMapper extends BaseMapper<ManagerMapper> {
    List<Manager> findByNameAndPass(Manager mgr);

    Manager findByName(String name);

    /**
     * 通过管理员的id找到管理员
     * @param id
     * @return
     */
    Manager findManagerById(Integer id);
}
