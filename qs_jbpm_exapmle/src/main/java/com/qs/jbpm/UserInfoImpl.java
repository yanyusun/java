package com.qs.jbpm;

import org.kie.api.task.model.Group;
import org.kie.api.task.model.OrganizationalEntity;
import org.kie.internal.task.api.UserInfo;

import java.util.Iterator;

/**
 * @author by pan on 16-3-28.
 */
public class UserInfoImpl implements UserInfo {
    @Override
    public String getDisplayName(OrganizationalEntity organizationalEntity) {
        return null;
    }

    @Override
    public Iterator<OrganizationalEntity> getMembersForGroup(Group group) {
        return null;
    }

    @Override
    public boolean hasEmail(Group group) {
        return false;
    }

    @Override
    public String getEmailForEntity(OrganizationalEntity organizationalEntity) {
        return null;
    }

    @Override
    public String getLanguageForEntity(OrganizationalEntity organizationalEntity) {
        return null;
    }
}
