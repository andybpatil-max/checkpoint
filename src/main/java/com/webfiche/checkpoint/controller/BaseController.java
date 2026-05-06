package com.webfiche.checkpoint.controller;

import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import com.webfiche.checkpoint.config.AppProperties;
import com.webfiche.checkpoint.config.UserSession;

public abstract class BaseController {

    @Autowired protected UserSession userSession;
    @Autowired protected AppProperties appProperties;
    @Autowired protected DataSource dataSource;

    protected Connection openConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
