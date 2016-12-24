package com.dqys.sale.orm.mapper;


import com.dqys.sale.orm.dto.UserDetailDTO;

public interface SaleUserMapper {


    UserDetailDTO getUserDetail(Integer userId);
}