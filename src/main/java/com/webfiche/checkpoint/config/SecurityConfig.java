package com.webfiche.checkpoint.config;

import java.security.MessageDigest;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import com.webfiche.checkpoint.service.LoginService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired private LoginService loginService;
    @Autowired private LoginSuccessHandler loginSuccessHandler;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(
                    "/login", "/Logon.action",
                    "/css/**", "/js/**", "/images/**", "/webjars/**"
                ).permitAll()
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .successHandler(loginSuccessHandler)
                .failureUrl("/login?error")
                .permitAll()
            )
            .logout(logout -> logout
                .logoutUrl("/Logoff.action")
                .logoutSuccessUrl("/login?logout")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .permitAll()
            )
            .sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .maximumSessions(1)
            );
        return http.build();
    }

    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder builder = http.getSharedObject(AuthenticationManagerBuilder.class);
        builder.userDetailsService(loginService).passwordEncoder(passwordEncoder());
        return builder.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new PasswordEncoder() {
            private static final String AES_KEY = "wFEncKeywFEncKey";
            @Override
            public String encode(CharSequence raw) {
                try {
                    byte[] digest = MessageDigest.getInstance("SHA-1").digest(raw.toString().getBytes("UTF-8"));
                    return Base64.getEncoder().encodeToString(digest);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
            @Override
            public boolean matches(CharSequence raw, String encoded) {
                if (encoded == null) return false;
                String trimmed = encoded.trim();
                // SHA-1 path: 28-char Base64 (20 bytes)
                if (encode(raw).equals(trimmed)) return true;
                // AES path: 24-char Base64 (16 bytes) — decrypt stored value and compare
                try {
                    byte[] key = AES_KEY.getBytes("UTF-8");
                    Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
                    cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(key, "AES"), new IvParameterSpec(key));
                    byte[] ct = Base64.getDecoder().decode(trimmed.getBytes("UTF-8"));
                    String decrypted = new String(cipher.doFinal(ct), "UTF-8");
                    return decrypted.equals(raw.toString());
                } catch (Exception ignored) {}
                return false;
            }
        };
    }
}
