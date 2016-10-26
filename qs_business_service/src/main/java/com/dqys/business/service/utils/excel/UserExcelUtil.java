package com.dqys.business.service.utils.excel;

import com.dqys.auth.orm.dao.facade.TUserInfoMapper;
import com.dqys.auth.orm.pojo.TUserInfo;
import com.dqys.business.service.dto.excel.ExcelMessage;
import com.dqys.business.service.dto.user.UserFileDTO;
import com.dqys.core.constant.KeyEnum;
import com.dqys.core.constant.SysPropertyTypeEnum;
import com.dqys.core.utils.DateFormatTool;
import com.dqys.core.utils.ExcelTool;
import com.dqys.core.utils.FormatValidateTool;
import com.dqys.core.utils.SysPropertyTool;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Yvan on 16/7/1.
 * <p/>
 * 成员信息导入模块
 */
public class UserExcelUtil {

    /**
     * 用户信息表格上传
     *
     * @return
     */
    public static Map<String, Object> upLoadUserExcel(String fileName, TUserInfoMapper tUserInfoMapper) {
        Map<String, Object> map = new HashMap<String, Object>();
        String name[] = fileName.split("_");
        if (name.length != 3) {
            map.put("result", "error");
            map.put("data", "文件解析错误");
            return map;
        }
        String type = name[0];
        Integer userId = Integer.valueOf(name[1]);
        try {
            // 临时文件地址
            String path = SysPropertyTool.getProperty(SysPropertyTypeEnum.SYS, KeyEnum.SYS_FILE_UPLOAD_PATH_KEY).getPropertyValue()
                    + "/temp/" + type + "/" + userId + "/";
            List<Map<String, Object>> list = ExcelTool.readExcelForList(path, fileName, 1, 0, 0);//借款人
            //判断文件的字段格式
            List<ExcelMessage> error = new ArrayList<ExcelMessage>();//错误信息
            if (!checkUserExcel(list, error, tUserInfoMapper)) {
                //文件的写出成表格文件
                map.put("result", "error");
                map.put("data", error);
            } else {
                //文件格式都正确的就处理表格
                map.put("result", "ok");
                List<UserFileDTO> userFileDTOs = new ArrayList<UserFileDTO>();
                disposeUserExcele(list, userFileDTOs);
                map.put("userFileDTOs", userFileDTOs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 处理用户信息表格
     *
     * @param list
     * @param userFileDTOs
     * @return map(包含key：userFileDTOs)
     */
    private static void disposeUserExcele(List<Map<String, Object>> list, List<UserFileDTO> userFileDTOs) {
        for (int i = 1; i < list.size(); i++) {
            Map<String, Object> map0 = list.get(i);
            if (ExcelUtilAsset.transStringToInteger(ExcelUtilAsset.transMapToString(map0, "var0")) == null) {
                continue;
            }
            UserFileDTO userFileDTO = new UserFileDTO();
            userFileDTO.setRealName(ExcelUtilAsset.transMapToString(map0, "var1"));//真实姓名
            userFileDTO.setUserName(ExcelUtilAsset.transMapToString(map0, "var2"));//用户昵称
            userFileDTO.setSex(ExcelUtilAsset.transMapToString(map0, "var3").equals("男") == true ? 1 : 0);//性别
            userFileDTO.setAccount(ExcelUtilAsset.transMapToString(map0, "var4")); //账号
            userFileDTO.setWechat(ExcelUtilAsset.transMapToString(map0, "var5")); //微信
            userFileDTO.setQq(ExcelUtilAsset.transMapToString(map0, "var6"));//QQ
            userFileDTO.setOfficeTel(ExcelUtilAsset.transMapToString(map0, "var7"));//办公电话
            userFileDTO.setMobile(ExcelUtilAsset.transMapToString(map0, "var8"));//手机号
            userFileDTO.setEmail(ExcelUtilAsset.transMapToString(map0, "var9"));//邮箱
            userFileDTO.setApartment(ExcelUtilAsset.transMapToString(map0, "var10"));// 部门
            userFileDTO.setOccupation(ExcelUtilAsset.transMapToString(map0, "var11"));// 职位
            userFileDTO.setDuty(ExcelUtilAsset.transMapToString(map0, "var12"));//职责名称
            userFileDTO.setDutyMark(ExcelUtilAsset.transMapToString(map0, "var13"));//职责介绍
            userFileDTO.setDutyArea(ExcelUtilAsset.transMapToString(map0, "var14"));//负责区域
            userFileDTO.setRole(ExcelUtilAsset.transMapToString(map0, "var15").equals("管理员") == true ? 1 : ExcelUtilAsset.transMapToString(map0, "var15").equals("管理者") == true ? 2 : 3);//角色
            userFileDTO.setYear(ExcelUtilAsset.transStringToInteger(ExcelUtilAsset.transMapToString(map0, "var16")));// 职业年限
            if (!ExcelUtilAsset.transMapToString(map0, "var17").equals("")) {
                userFileDTO.setJoinAt(DateFormatTool.parse(ExcelUtilAsset.transMapToString(map0, "var17"), DateFormatTool.DATE_FORMAT_10_REG2)); //入职时间
            }
            userFileDTO.setRemark(ExcelUtilAsset.transMapToString(map0, "var19"));//备注
            userFileDTOs.add(userFileDTO);
        }
    }

    /**
     * 验证用户信息表格信息
     *
     * @param list
     * @param error
     * @return
     */
    private static boolean checkUserExcel(List<Map<String, Object>> list, List<ExcelMessage> error, TUserInfoMapper tUserInfoMapper) {
        String[] str = {"序号", "*姓名", "*昵称", "*性别", "*自定义账号", "*微信号", "QQ号", "办公电话", "*手机号", "*工作邮箱", "*部门", "*职位名称",
                "*职责名称", "职责描述", "*职责区域", "*系统角色", "从业年限(年）", "入职时间", "历史业绩（总数量）", "备注"};
        ExcelUtilAsset.templateFormat(str, list.get(0), "用户", "表头信息错误", error);
        boolean flag = true;
        if (error != null && error.size() < 1) {
            //判断每个表格的数据类型
            checkUser(error, list, tUserInfoMapper);
            if (error.size() > 0) {
                flag = false;
            }
        } else {
            flag = false;
        }
        return flag;
    }

    private static void checkUser(List<ExcelMessage> error, List<Map<String, Object>> list, TUserInfoMapper tUserInfoMapper) {
        String name = "用户信息表";
        Map<String, Object> map = list.get(0);
        List<String> emailList = new ArrayList<>();
        for (int i = 1; i < list.size(); i++) {
            Map<String, Object> l = list.get(i);
            if (ExcelUtilAsset.transStringToInteger(ExcelUtilAsset.transMapToString(l, "var0")) == null) {
                continue;
            }
            if (!FormatValidateTool.isDecimals(ExcelUtilAsset.transMapToString(l, "var0"), 0)) {
                ExcelUtilAsset.placeByExcel(error, name, i, 0, ExcelUtilAsset.transMapToString(map, "var0"), "格式错误");
            }
            if (ExcelUtilAsset.transMapToString(l, "var1").equals("")) {//*姓名
                ExcelUtilAsset.placeByExcel(error, name, i, 1, ExcelUtilAsset.transMapToString(map, "var1"), "不能为空");
            }
            if (ExcelUtilAsset.transMapToString(l, "var2").equals("")) {//*昵称
                ExcelUtilAsset.placeByExcel(error, name, i, 2, ExcelUtilAsset.transMapToString(map, "var2"), "不能为空");
            }
            if (ExcelUtilAsset.transMapToString(l, "var3").equals("")) {//*性别
                ExcelUtilAsset.placeByExcel(error, name, i, 3, ExcelUtilAsset.transMapToString(map, "var3"), "不能为空");
            }
            if (ExcelUtilAsset.transMapToString(l, "var4").equals("")) {//*自定义账号
                ExcelUtilAsset.placeByExcel(error, name, i, 4, ExcelUtilAsset.transMapToString(map, "var4"), "不能为空");
            }
            if (ExcelUtilAsset.transMapToString(l, "var5").equals("")) {//*微信号
                ExcelUtilAsset.placeByExcel(error, name, i, 5, ExcelUtilAsset.transMapToString(map, "var5"), "不能为空");
            }
            if (!ExcelUtilAsset.transMapToString(l, "var6").equals("") && !FormatValidateTool.isNumeric(ExcelUtilAsset.transMapToString(l, "var6"))) {//QQ号
                ExcelUtilAsset.placeByExcel(error, name, i, 6, ExcelUtilAsset.transMapToString(map, "var6"), "格式错误");
            }
            if (!ExcelUtilAsset.transMapToString(l, "var7").equals("") && !FormatValidateTool.checkPhone(ExcelUtilAsset.transMapToString(l, "var7"))) {//办公电话
                ExcelUtilAsset.placeByExcel(error, name, i, 7, ExcelUtilAsset.transMapToString(map, "var7"), "格式错误");
            }
            if (!FormatValidateTool.checkMobile(ExcelUtilAsset.transMapToString(l, "var8"))) {//*手机号
                ExcelUtilAsset.placeByExcel(error, name, i, 8, ExcelUtilAsset.transMapToString(map, "var8"), "不能为空");
            }
            if (!FormatValidateTool.checkEmail(ExcelUtilAsset.transMapToString(l, "var9"))) {//*工作邮箱
                ExcelUtilAsset.placeByExcel(error, name, i, 9, ExcelUtilAsset.transMapToString(map, "var9"), "格式错误");
            } else {
                //判断excel表里的邮箱是否 重复
                if (emailList.contains(ExcelUtilAsset.transMapToString(l, "var9"))) {
                    ExcelUtilAsset.placeByExcel(error, name, i, 9, ExcelUtilAsset.transMapToString(map, "var9"), "表内邮箱重复");
                } else {
                    emailList.add(ExcelUtilAsset.transMapToString(l, "var9"));//
                    //判断库里的邮箱是否重复
                    if (verifyUser(tUserInfoMapper, null, null, ExcelUtilAsset.transMapToString(l, "var9"))) {
                        ExcelUtilAsset.placeByExcel(error, name, i, 9, ExcelUtilAsset.transMapToString(map, "var9"), "该邮箱已注册");
                    }
                }
            }
            if (ExcelUtilAsset.transMapToString(l, "var10").equals("")) {//*部门
                ExcelUtilAsset.placeByExcel(error, name, i, 10, ExcelUtilAsset.transMapToString(map, "var10"), "不能为空");
            }
            if (ExcelUtilAsset.transMapToString(l, "var11").equals("")) {//*职位名称
                ExcelUtilAsset.placeByExcel(error, name, i, 11, ExcelUtilAsset.transMapToString(map, "var11"), "不能为空");
            }
            if (ExcelUtilAsset.transMapToString(l, "var12").equals("")) {//*职责名称
                ExcelUtilAsset.placeByExcel(error, name, i, 12, ExcelUtilAsset.transMapToString(map, "var12"), "不能为空");
            }
            if (ExcelUtilAsset.transMapToString(l, "var14").equals("")) {//*职责区域
                ExcelUtilAsset.placeByExcel(error, name, i, 14, ExcelUtilAsset.transMapToString(map, "var14"), "不能为空");
            }
            if (ExcelUtilAsset.transMapToString(l, "var15").equals("")) {//*系统角色
                ExcelUtilAsset.placeByExcel(error, name, i, 15, ExcelUtilAsset.transMapToString(map, "var15"), "不能为空");
            }
            if (!ExcelUtilAsset.transMapToString(l, "var16").equals("") && !FormatValidateTool.isNumeric(ExcelUtilAsset.transMapToString(l, "var16"))) {//从业年限(年）
                ExcelUtilAsset.placeByExcel(error, name, i, 16, ExcelUtilAsset.transMapToString(map, "var16"), "格式错误");
            }
            if (!ExcelUtilAsset.transMapToString(l, "var17").equals("") && !FormatValidateTool.isDate(ExcelUtilAsset.transMapToString(l, "var17"))) {//入职时间
                ExcelUtilAsset.placeByExcel(error, name, i, 17, ExcelUtilAsset.transMapToString(map, "var16"), "格式错误");
            }
        }
    }

    /**
     * 验证是否存在了
     *
     * @param account
     * @param mobile
     * @param email
     * @return （存在true 不存在false）
     */
    private static boolean verifyUser(TUserInfoMapper tUserInfoMapper, String account, String mobile, String email) {
        List<TUserInfo> list = tUserInfoMapper.verifyUser(account, mobile, email);
        if (list.size() > 0) {
            return true;
        } else {
            return false;
        }
    }

}
