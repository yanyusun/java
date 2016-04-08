package com.dqys.service.auth.impl;

import com.dqys.core.model.ServiceResult;
import com.dqys.persistent.auth.pojo.TUserInfo;
import com.dqys.service.auth.UserService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author by pan on 16-4-6.
 */
@Service
@Primary
public class UserServiceImpl implements UserService {
    @Override
    public ServiceResult<TUserInfo> registerUser(String userName, String mobile, String email, String pwd) throws Exception {
        return null;
    }

    @Override
    public ServiceResult<TUserInfo> registerCompany(String companyName, String credential, MultipartFile licence, MultipartFile legalPerson, Integer province, Integer city, Integer area, String address) throws Exception {
        return null;
    }

    @Override
    public ServiceResult<TUserInfo> perfectUserInfo(String userName, String mobile, String email, String realName, Boolean isMan) throws Exception {
        return null;
    }
}
