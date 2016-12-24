package com.dqys.sale.orm.mapper;


import com.dqys.sale.orm.pojo.UserDetailDTO;



public interface SaleUserMapper {

    UserDetailDTO getUserDetail(Integer userId);
}