package com.user.usermanagement.repositories;

import com.user.usermanagement.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.AbstractContextMapper;
import org.springframework.ldap.support.LdapNameBuilder;
import org.springframework.stereotype.Repository;

import javax.naming.Name;
import javax.naming.directory.Attributes;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.BasicAttributes;

@Repository
public class UserRepository {

    @Autowired
    private LdapTemplate ldapTemplate;

    public String create(User user) {
        Name dn = buildDn(user.getEmail());
        ldapTemplate.bind(dn, null, buildAttribute(user));
        return user.getEmail() + " created";
    }

    public User findByEmail(String email) {
        Name dn = buildDn(email);
        User user = null;

        user = ldapTemplate.lookup(dn, new UserContextMapper());
        return user;
    }

    private Name buildDn(String email) {
        return LdapNameBuilder
                .newInstance()
                .add("ou", "users")
                .add("uid", email)
                .build();
    }

    private Attributes buildAttribute(User user) {
        BasicAttribute ocAttributes = new BasicAttribute("objectClass");
        ocAttributes.add("top");
        ocAttributes.add("person");
        ocAttributes.add("organizationalPerson");
        ocAttributes.add("inetOrgPerson");
        ocAttributes.add("shadowAccount");
        ocAttributes.add("posixAccount");

        Attributes attributes = new BasicAttributes();
        attributes.put(ocAttributes);
        attributes.put("ou", "users");
        attributes.put("uid", user.getEmail());
        attributes.put("mail", user.getEmail());
        attributes.put("cn", user.getFirstName());
        attributes.put("sn", user.getLastName());
        attributes.put("telephoneNumber", user.getContactNo());
        attributes.put("userPassword", user.getPassword());

        return attributes;
    }

    private class UserContextMapper extends AbstractContextMapper<User> {
        public User doMapFromContext(DirContextOperations context) {
            User user = new User();
            user.setFirstName(context.getStringAttribute("cn"));
            user.setLastName(context.getStringAttribute("sn"));
            user.setEmail(context.getStringAttribute("mail"));
            user.setContactNo(context.getStringAttribute("telephoneNumber"));
            return user;
        }
    }
}
