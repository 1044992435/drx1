package com.jk.service;

import com.jk.model.AdminUser;
import com.jk.model.Tree;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface MainService {
    List<Tree> finMenuTree();

    boolean userName(String username);

    boolean regUser(AdminUser adminUser);

    AdminUser login(AdminUser adminUser);


}
