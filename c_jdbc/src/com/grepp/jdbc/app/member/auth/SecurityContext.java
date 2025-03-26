package com.grepp.jdbc.app.member.auth;

public class SecurityContext {
    
    private Principal principal = Principal.ANONYMOUS;
    private static SecurityContext instance;
    
    public static SecurityContext getInstance() {
        if (instance == null) {
            instance = new SecurityContext();
        }
        
        return instance;
    }

    private SecurityContext() {
    }
    
    public void setPrincipal(Principal principal) {
        this.principal = principal;
    }
    
    public Principal getPrincipal() {
        return principal;
    }
}
