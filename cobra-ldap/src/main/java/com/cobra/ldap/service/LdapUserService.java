package com.cobra.ldap.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.stereotype.Service;

/**
 * @author cobra
 * @since 2021/1/22 9:38
 */
@Service
public class LdapUserService {

    private String baseDn = "dc=my-domain,dc=com";
    private String objectClass = "structuralObjectClass";
    private String person = "inetOrgPerson";
    private String prefix = "cn";

    @Autowired
    private LdapTemplate ldapTemplate;

}
