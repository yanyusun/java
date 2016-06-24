package com.dqys.auth.service.facade;

import com.dqys.auth.service.constant.MailVerifyTypeEnum;
import com.dqys.core.model.ServiceResult;
import com.dqys.auth.service.dto.UserDTO;
import com.dqys.auth.orm.pojo.TUserInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author by pan on 16-4-6.
 */
public interface UserService {

    /**
     * 验证账户有效性
     *
     * @param account
     * @param mobile
     * @param email
     * @return
     * @throws Exception
     */
    ServiceResult<Integer> validateUser(String account, String mobile, String email) throws Exception;

    /**
     * 注册用户信息
     *
     * @param account
     * @param mobile
     * @param email
     * @param pwd
     * @return
     * @throws Exception
     */
    ServiceResult<UserDTO> userRegister_tx(String account, String mobile, String email, String pwd) throws Exception;

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
     * 根据用户ID查询用户
     * @param uid
     * @return
     */
    ServiceResult<TUserInfo> queryUserById(Integer uid);

    /**
     * 注册机构管理员
     * @param userType
     * @param tUserInfo
     * @return
     */
    ServiceResult<TUserInfo> registerAdmin_tx(Integer userType, TUserInfo tUserInfo);
}
