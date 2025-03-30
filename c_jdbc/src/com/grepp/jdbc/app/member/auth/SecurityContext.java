package com.grepp.jdbc.app.member.auth;

public class SecurityContext {
    
    private static SecurityContext instance;
    private Principal principal = Principal.ANONYMOUS;
    
    private SecurityContext() {
    }
    
    public static SecurityContext getInstance() {
        if (instance == null) {
            instance = new SecurityContext();
        }
        
        return instance;
    }
    
    public Principal getPrincipal() {
        return principal;
    }
    
    public void setPrincipal(Principal principal) {
        this.principal = principal;
    }
}
