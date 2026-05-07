package com.webfiche.checkpoint.config;

import javax.sql.DataSource;
import jakarta.servlet.ServletContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import com.webfiche.checkpoint.util.EncryptDecryptString;

@Configuration
public class DataSourceConfig {

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String username;

    @Autowired(required = false)
    private ServletContext servletContext;

    @Bean
    public DataSource dataSource() {
        String webInf = resolveWebInfPath();
        String password = EncryptDecryptString.GetAccess(webInf);

        HikariConfig cfg = new HikariConfig();
        cfg.setJdbcUrl(url);
        cfg.setUsername(username);
        cfg.setPassword(password);
        cfg.setDriverClassName("oracle.jdbc.OracleDriver");
        cfg.setInitializationFailTimeout(-1);
        return new HikariDataSource(cfg);
    }

    private String resolveWebInfPath() {
        if (servletContext != null) {
            String real = servletContext.getRealPath("/WEB-INF/");
            if (real != null) return real + "/";
        }
        // fallback when running embedded (dev mode)
        String home = System.getProperty("catalina.home", "");
        return home + "/webapps/checkpoint/WEB-INF/";
    }
}
