package com.dqys.service.auth;

import com.dqys.constant.MailVerifyTypeEnum;
import com.dqys.core.model.ServiceResult;
import com.dqys.dto.UserDTO;
import com.dqys.persistent.auth.pojo.TUserInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author by pan on 16-4-6.
 */
public interface UserService {

    /**
     * 验证账户有效性
     *
     * @param userName
     * @param mobile
     * @param email
     * @return
     * @throws Exception
     */
    ServiceResult<Integer> validateUser(String userName, String mobile, String email) throws Exception;

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
    ServiceResult<UserDTO> userRegister_tx(String userName, String mobile, String email, String pwd) throws Exception;

    /**
     * 用户登录
     *
     *
     * @param uid
     * @param userName
     * @param mobile
     * @param email
     * @param pwd
     * @return
     * @throws Exception
     */
    ServiceResult<UserDTO> userLogin(Integer uid, String userName, String mobile, String email, String pwd) throws Exception;

    /**
     *  用户重置
     *
     *
     * @param uid
     * @param userName
     * @param email
     * @param mobile
     * @param pwd
     * @return
     * @throws Exception
     */
    ServiceResult userReset(Integer uid, String userName, String email, String mobile, String pwd) throws Exception;

    /**
     * 发送确认邮件
     * @param e
     * @param uid
     */
    void sendConfirmMail(MailVerifyTypeEnum e, Integer uid);

    /**
     *  确认邮箱验证
     *
     * @param e
     * @param uid
     * @param confirmKey
     * @param pwd
     * @return
     */
    ServiceResult confirmMail(MailVerifyTypeEnum e, Integer uid, String confirmKey, String pwd) throws Exception;



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
