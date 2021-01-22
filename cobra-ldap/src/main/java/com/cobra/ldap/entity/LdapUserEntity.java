package com.cobra.ldap.entity;

import lombok.Data;
import lombok.ToString;
import org.springframework.ldap.odm.annotations.Entry;

/**
 * @author cobra
 * @since 2021/1/22 9:38
 */
@Data
@ToString
@Entry(objectClasses = {"inetOrgPerson"},base = "dc=my-domain,dc=com")
public class LdapUserEntity {

}
