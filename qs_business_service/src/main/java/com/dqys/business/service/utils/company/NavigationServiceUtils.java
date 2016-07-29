package com.dqys.business.service.utils.company;

import com.dqys.business.orm.pojo.company.Navigation;
import com.dqys.business.service.dto.company.NavigationDTO;
import com.dqys.core.utils.CommonUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yvan on 16/7/25.
 * 导航栏工具栏
 */
public class NavigationServiceUtils {

    public static List<NavigationDTO> toNavigationDTO(List<Navigation> navigationList){
        if(CommonUtil.checkParam(navigationList)){
            return null;
        }
        List<NavigationDTO> navigationDTOList = new ArrayList<>();

        navigationList.forEach(navigation -> {
            navigationDTOList.add(toNavigationDTO(navigation));
        });

        return navigationDTOList;
    }

    public static NavigationDTO toNavigationDTO(Navigation navigation){
        if(CommonUtil.checkParam(navigation, navigation.getValue(), navigation.getName())){
            return null;
        }
        NavigationDTO navigationDTO = new NavigationDTO();

        navigationDTO.setValue(navigation.getName());
        navigationDTO.setKey(navigation.getId());
        navigationDTO.setPath(navigation.getValue());
        navigationDTO.setIcon(navigation.getIcon());
        navigationDTO.setChild(navigation.getChild());

        return navigationDTO;
    }

}
