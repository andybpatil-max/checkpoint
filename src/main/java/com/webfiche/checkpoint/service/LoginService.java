package com.webfiche.checkpoint.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.webfiche.checkpoint.sysadmin.beans.UserDetail;
import com.webfiche.checkpoint.sysadmin.beans.UserSelector;
import com.webfiche.checkpoint.sysadmin.service.SysadUserUtil;

@Service
public class LoginService implements UserDetailsService {

    @Autowired private SysadUserUtil sysadUserUtil;
    @Autowired private DataSource dataSource;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try (Connection conn = dataSource.getConnection()) {
            boolean exists = sysadUserUtil.UserExists(conn, username);
            if (!exists) {
                throw new UsernameNotFoundException("User not found: " + username);
            }
            UserSelector userSelector = new UserSelector();
            sysadUserUtil.GetUserRows(conn, userSelector, username);
            UserDetail[] rows = userSelector.getUserrows();
            UserDetail ud = (rows != null && rows.length > 0) ? rows[0] : null;
            if (ud == null) {
                throw new UsernameNotFoundException("User not found: " + username);
            }
            // Return a Spring Security User; password stored as the DB-encoded value
            return org.springframework.security.core.userdetails.User.builder()
                    .username(ud.getUser_id())
                    .password(ud.getUser_pass_current() != null ? ud.getUser_pass_current() : "")
                    .authorities(Collections.emptyList())
                    .build();
        } catch (UsernameNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new UsernameNotFoundException("Error loading user: " + username, e);
        }
    }
}
