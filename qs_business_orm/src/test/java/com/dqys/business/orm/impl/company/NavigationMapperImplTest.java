package com.dqys.business.orm.impl.company;

import com.dqys.business.orm.base.BaseTest;
import com.dqys.business.orm.mapper.company.NavigationMapper;
import com.dqys.business.orm.pojo.company.Navigation;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Yvan on 16/7/25.
 */
public class NavigationMapperImplTest extends BaseTest {

    @Autowired
    private NavigationMapper navigationMapper;

    @Test
    public void test(){
        Navigation navigation = new Navigation();
        navigation.setName("mingcheng");
        navigation.setValue("asdasd/asdasda/asdasda");
        navigation.setSort(99);
        Integer result = navigationMapper.insert(navigation);
        Assert.assertNotNull(result);

        Integer id = navigation.getId();
        Assert.assertNotNull(id);

        Navigation navigation1 = navigationMapper.get(id);
        Assert.assertNotNull(navigation1);
        Assert.assertEquals(navigation.getName(), navigation1.getName());
    }
}
