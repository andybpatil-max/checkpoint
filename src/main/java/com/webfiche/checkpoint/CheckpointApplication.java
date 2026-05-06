package com.webfiche.checkpoint;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.ldap.LdapAutoConfiguration;
import org.springframework.boot.autoconfigure.ldap.embedded.EmbeddedLdapAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import com.webfiche.checkpoint.config.AppProperties;

@SpringBootApplication(exclude = {LdapAutoConfiguration.class, EmbeddedLdapAutoConfiguration.class})
@EnableConfigurationProperties(AppProperties.class)
public class CheckpointApplication {

	public static void main(String[] args) {
		SpringApplication.run(CheckpointApplication.class, args);
	}

}
