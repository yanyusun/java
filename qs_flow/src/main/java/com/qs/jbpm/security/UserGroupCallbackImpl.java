package com.qs.jbpm.security;

import org.kie.internal.task.api.UserGroupCallback;

import java.util.List;

/**
 * @author by pan on 16-3-31.
 */
public class UserGroupCallbackImpl implements UserGroupCallback {
    @Override
    public boolean existsUser(String s) {
        return false;
    }

    @Override
    public boolean existsGroup(String s) {
        return false;
    }

    @Override
    public List<String> getGroupsForUser(String s, List<String> list, List<String> list1) {
        return null;
    }
}
