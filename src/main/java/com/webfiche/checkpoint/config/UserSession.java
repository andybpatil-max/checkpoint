package com.webfiche.checkpoint.config;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;
import com.webfiche.checkpoint.beans.User;
import com.webfiche.checkpoint.sysadmin.beans.ProductSelector;

@Component
@SessionScope
public class UserSession {

    private User user;
    private ProductSelector myProds;

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public ProductSelector getMyProds() { return myProds; }
    public void setMyProds(ProductSelector myProds) { this.myProds = myProds; }

    public boolean isLoggedIn() { return user != null; }

    public void clear() {
        this.user = null;
        this.myProds = null;
    }
}
