package com.dqys.business.service.dto.company;

/**
 * Created by Yvan on 16/6/30.
 * 公司信息
 * @apiDefine CompanyDTO
 * @apiSuccessExample {json} CompanyDTO-Response:
 * HTTP/1.1 2000 ok
 * {
 *     id:1,
 *     name:'name',
 *     province:'浙江省',
 *     city:'杭州市',
 *     district:'江干区'
 * }
 * git 地址: http://114.215.239.181:6080/qs_plat/java/blob/master/qs_business_service/src/main/java/com/dqys/business/service/dto/company/CompanyDTO.java
 */
public class CompanyDTO {

    private Integer id; // 主键

    private String name; // 公司名称
    private String province; // 省份
    private String city; // 城市
    private String district; // 区域

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }
}
