package org.cloud.manager.login.service.impl;

import javax.annotation.Resource;

import org.cloud.manager.login.dao.LoginDao;
import org.cloud.manager.login.service.LoginService;
import org.cloud.manager.login.vo.LoginAccessVo;
import org.springframework.stereotype.Service;

@Service("adminLoginService")
public class AdminLoginService implements LoginService {

    @Resource(name="adminLoginDao")
    LoginDao adminLoginDao;
    
    @Override
    public Boolean loginAdmin(LoginAccessVo vo) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void logoutAdmin() throws Exception {
        // TODO Auto-generated method stub
        
    }

}
