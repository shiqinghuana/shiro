package com.springboot1.shiro

import org.apache.shiro.authc.AuthenticationException
import org.apache.shiro.authc.AuthenticationInfo
import org.apache.shiro.authc.AuthenticationToken
import org.apache.shiro.authc.SimpleAuthenticationInfo
import org.apache.shiro.authz.AuthorizationInfo
import org.apache.shiro.authz.SimpleAuthorizationInfo
import org.apache.shiro.realm.AuthorizingRealm
import org.apache.shiro.realm.jdbc.JdbcRealm
import org.apache.shiro.subject.PrincipalCollection
import org.thymeleaf.util.StringUtils

/**
 * Created by QingHuan on 2020/7/31 22:56
 */
class CustomRelam  extends AuthorizingRealm{

    private  Map<String,String> user = new HashMap<>() ;
    {
        user.put("admin","123456")
        user.put("test","123456")
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String username =  (String) principalCollection.getPrimaryPrincipal()

        List<String> roles = getRolesByUserNamem(username)

        List<String> permission = getPermissionByUserNamem(username)

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo()
        info.setRoles(roles)
        info.setStringPermissions(permission)
        return info
    }

    /**
     * @TODO 用户认证  SimpleAuthenticationInfo （账号， 密码 ，realmname）
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String principal = (String)authenticationToken.getPrincipal()
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(principal,getPasswordByUserName(principal),getName())
        return info
    }




    private String getPasswordByUserName(String username){
        return user.get(username)
    }

    private List<String> getRolesByUserNamem(String username) {
        ArrayList<String> roles = new ArrayList<>()
        if (username == "admin") {
            roles.add("*")
        }
        roles.add("test")
        return roles
    }

    private List<String> getPermissionByUserNamem(String username){
        ArrayList<String>  permission= new ArrayList<>()
        if (username == "admin"){
            permission.add("*")
        }
        permission.add("user:test")
        permission.add("user:edit")
        return permission
    }
}
