package com.yg.dao;

import com.yg.domain.CheckBack;

public interface CheckBackMapper extends BaseMapper<CheckBack> {

    /**
     * 通过一个管理员id获得对应的批复对象
     * @param id 管理员的id
     * @return
     */
    CheckBack getCheckBackByManager(Integer id);
}
