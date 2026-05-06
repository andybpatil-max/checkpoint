package com.webfiche.checkpoint.config;

import java.sql.Connection;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;
import com.webfiche.checkpoint.sysadmin.beans.ProductSelector;
import com.webfiche.checkpoint.sysadmin.service.SysadProdUtil;

@Service
public class AppStartupService implements ApplicationRunner {

    @Autowired private DataSource dataSource;
    @Autowired private AppProperties appProperties;
    @Autowired private SysadProdUtil sysadProdUtil;

    // Application-scoped product catalog (replaces ServletContext.getAttribute("PRODUCTS"))
    private final ProductSelector allProducts = new ProductSelector();

    @Override
    public void run(ApplicationArguments args) {
        try (Connection conn = dataSource.getConnection()) {
            allProducts.clearRows();
            sysadProdUtil.GetProductRows(conn, allProducts);
            System.out.println("AppStartupService: loaded " + allProducts.getProductrows().length + " products");
        } catch (Exception e) {
            System.out.println("AppStartupService: DB unavailable at startup, products not loaded: " + e.getMessage());
        }
    }

    public ProductSelector getAllProducts() {
        return allProducts;
    }
}
