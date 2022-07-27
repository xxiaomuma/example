package pers.xiaomuma.auth.example.core.model;

import org.springframework.security.core.userdetails.UserDetails;
import pers.xiaomuma.auth.framework.auth.server.authentication.UserConnection;
import pers.xiaomuma.auth.framework.user.AdditionalUser;
import pers.xiaomuma.auth.framework.user.DefaultUserDetails;
import java.util.Collections;


public class PasswordUserConnection implements UserConnection {

    private String username;
    private UserDetails userDetails;

    public PasswordUserConnection(String username, UserDetails userDetails) {
        this.username = username;
        this.userDetails = userDetails;
    }

    public PasswordUserConnection(Integer userId, String username, String password, boolean enabled) {
        AdditionalUser user = new AdditionalUser();
        user.setUserId(userId);
        DefaultUserDetails userDetails = new DefaultUserDetails(username, password,
                Collections.emptySet(), enabled, user);
        this.username = username;
        this.userDetails = userDetails;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public UserDetails getUserDetails() {
        return this.userDetails;
    }
}
