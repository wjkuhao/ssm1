package com.wangjian.service;

import com.wangjian.SysLog;

import java.util.List;

public interface SysLogService {
    List<SysLog> findAll();

    void save(SysLog sysLog);
}
