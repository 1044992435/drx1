package com.jk.service;

import com.alibaba.fastjson.JSONObject;
import com.jk.model.*;

import java.util.List;

public interface PowerService {
    JSONObject queryadmin(Integer rows, Integer page);

    JSONObject queryrole(Integer rows, Integer page);

    List<AdminRole> queryRole(Integer uid);

    void saveRole(Integer userId, String[] split);

    void addpower(Integer rid, String[] split);

    void deleteadmin(Integer uid);

    void deleterole(Integer id);

    void addroles(Role role);

    void updateroles(Role role);

    Role queryRoles(Integer id);

    int deletes(String ids);

    List<RoleTree> queryroletree(Integer rid);

    List<Tree> querypower(int pid);
}
