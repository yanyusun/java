package com.dqys.service.auth;

import com.dqys.core.model.ServiceResult;
import com.dqys.persistent.auth.pojo.TUserInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author by pan on 16-4-6.
 */
public interface UserService {

    /**
     * 注册用户信息
     *
     * @param userName
     * @param mobile
     * @param email
     * @param pwd
     * @return
     * @throws Exception
     */
    ServiceResult<TUserInfo> registerUser(String userName, String mobile, String email, String pwd) throws Exception;


    /**
     * 注册公司信息
     * @param companyName
     * @param credential
     * @param licence
     * @param legalPerson
     * @param province
     * @param city
     * @param area
     * @param address
     * @return
     * @throws Exception
     */
    ServiceResult<TUserInfo> registerCompany(String companyName, String credential, MultipartFile licence, MultipartFile legalPerson,
                                             Integer province, Integer city, Integer area, String address) throws Exception;

    /**
     * 完善个人信息
     * @param userName
     * @param mobile
     * @param email
     * @param realName
     * @param isMan
     * @return
     * @throws Exception
     */
    ServiceResult<TUserInfo> perfectUserInfo(String userName, String mobile, String email, String realName, Boolean isMan) throws Exception;


}
