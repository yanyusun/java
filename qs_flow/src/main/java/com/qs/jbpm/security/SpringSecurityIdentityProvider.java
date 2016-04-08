package com.qs.jbpm.security;

import org.kie.internal.identity.IdentityProvider;

import java.util.List;

/**
 * @author by pan on 16-3-31.
 */
public class SpringSecurityIdentityProvider implements IdentityProvider {

    @Override
    public String getName() {
        return null;
    }

    @Override
    public List<String> getRoles() {
        return null;
    }

    @Override
    public boolean hasRole(String s) {
        return false;
    }
}
