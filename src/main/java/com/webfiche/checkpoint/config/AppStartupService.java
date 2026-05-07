package com.webfiche.checkpoint.config;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
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

    private final ProductSelector allProducts = new ProductSelector();

    @Override
    public void run(ApplicationArguments args) {
        try (Connection conn = dataSource.getConnection()) {
            allProducts.clearRows();
            sysadProdUtil.GetProductRows(conn, allProducts);
            System.out.println("AppStartupService: loaded " + allProducts.getProductrows().length + " products");

            // Load applDate from system_control
            try (Statement st = conn.createStatement();
                 ResultSet rs = st.executeQuery(
                     "SELECT APPLDATE FROM system_control ORDER BY PRODUCTID FETCH FIRST 1 ROWS ONLY")) {
                if (rs.next()) {
                    String raw = rs.getString("APPLDATE");
                    if (raw != null && !raw.isBlank()) {
                        appProperties.setApplDate(raw.trim());
                        System.out.println("AppStartupService: applDate=" + raw.trim());
                    }
                }
            } catch (Exception e) {
                System.out.println("AppStartupService: could not load applDate: " + e.getMessage());
            }
        } catch (Exception e) {
            System.out.println("AppStartupService: DB unavailable at startup: " + e.getMessage());
        }
    }

    public ProductSelector getAllProducts() {
        return allProducts;
    }
}
