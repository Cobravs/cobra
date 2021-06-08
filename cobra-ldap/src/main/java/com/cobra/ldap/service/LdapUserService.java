package com.cobra.ldap.service;

import com.cobra.ldap.entity.LdapUserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.NameNotFoundException;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.AbstractContextMapper;
import org.springframework.ldap.query.LdapQuery;
import org.springframework.stereotype.Service;

import javax.naming.Name;
import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import java.util.List;
import java.util.UUID;

import static org.springframework.ldap.query.LdapQueryBuilder.*;

/**
 * @author cobra
 * @since 2021/1/22 9:38
 */
@Service
public class LdapUserService {

    private String baseDn = "dc=my-domain,dc=com";
    private String objectclass = "structuralObjectClass";
    private String person = "inetOrgPerson";
    private String prefix = "cn";

    @Autowired
    private LdapTemplate ldapTemplate;

    public List<String> findCnListsByUserName(String userName) {
        LdapQuery query = query()
                .base(baseDn)
                .attributes(prefix)
                .where(prefix).is(userName);
        return ldapTemplate.search(query,
                (AttributesMapper<String>) attrs -> (String) attrs.get(prefix).get());
    }

    public List<LdapUserEntity> findAllUser() {

        return ldapTemplate.search(query()
                .where(objectclass).is(person), new UserMapperAttribute());
    }

    public boolean isUserDnExist(String dn) {
        try {
            ldapTemplate.lookup(dn);
        } catch (NameNotFoundException e) {
            return false;
        }
        return true;
    }

    public List<Name> findAllDn() {
        // 查询所有dn
        List<Name> search = ldapTemplate.search(query()
                        .where(objectclass).is(person),
                new AbstractContextMapper() {
                    @Override
                    protected Name doMapFromContext(DirContextOperations adapter) {
                        return adapter.getDn();
                    }

                });
        return search;
    }

    public LdapUserEntity loginLdap(String userName, String password) {
        LdapUserEntity user = new LdapUserEntity();
        List<Name> allDn = findAllDn();
        String dn = "";
        for (Name name : allDn) {
            String s = name.toString();
            if (s.startsWith(prefix + "=" + userName)) {
                dn = s;
                user.setUserName(userName);
                break;
            }
        }
        boolean userDnExist = isUserDnExist(dn);
        if (userDnExist) {
            user.setUserName(userName);
            return user;
        }
        return null;
    }

    public static String getUUID() {
        String uuid = UUID.randomUUID().toString();
        return uuid.replaceAll("-", "");
    }

    private static class UserMapperAttribute implements AttributesMapper<LdapUserEntity> {

        private String prefix = "cn";

        @Override
        public LdapUserEntity mapFromAttributes(Attributes attributes) throws NamingException {
            LdapUserEntity user = new LdapUserEntity();
            Object o = attributes.get(prefix).get();
            user.setUserName((String) o);
            return user;
        }
    }

}
