package com.dqys.business.orm.pojo;

import com.dqys.core.base.BaseModel;
import com.dqys.core.model.Address;
import com.dqys.core.model.Contact;

import java.util.List;

/**
 * Created by pan on 16-5-18.
 *
 * 联系人信息
 */
public class ContactsInfo extends BaseModel {

    private Integer type;           //类型:借款人 共同借款人 担保方 银行客户经理
    private String realName;
    private String identity;
    private String company;
    private List<Contact> contacts;
    private List<Address> addresses;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }
}
