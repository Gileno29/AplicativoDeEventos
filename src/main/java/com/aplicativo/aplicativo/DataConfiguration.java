package com.aplicativo.aplicativo;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@EnableJpaRepositories(basePackageClasses = AplicativoDeEventosApplication.class)
@EnableTransactionManagement


@Configuration
public class DataConfiguration {
	
	@Bean
	public JpaTransactionManager transactionManager(EntityManagerFactory emf) {
		return new JpaTransactionManager(emf);
	}
	
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource, JpaVendorAdapter jpaVendorAdapter) {
		Properties properties = new Properties();		
		properties.put("hibernate.show_sql", true);
		properties.put("hibernate.format_sql", true);
		properties.put("hibernate.use_sql_comments", true);
		properties.put("javax.persistence.schema-generation.database.action", "update");//create-drop
		
		LocalContainerEntityManagerFactoryBean bean = new LocalContainerEntityManagerFactoryBean();
		bean.setDataSource(dataSource);
		bean.setJpaVendorAdapter(jpaVendorAdapter);		
		bean.setPackagesToScan(AplicativoDeEventosApplication.class.getPackage().getName());		
		bean.setPersistenceUnitName("eventos");
		bean.setJpaProperties(properties);
		
		return bean;
	}
	
	@Bean
	public JpaVendorAdapter jpaVendorAdapter() {
		HibernateJpaVendorAdapter bean = new HibernateJpaVendorAdapter();
		
		bean.setDatabase(Database.POSTGRESQL);
//		bean.setGenerateDdl(true);
//		bean.setShowSql(false);
		
		return bean;
	}
	
	//Link: https://www.baeldung.com/spring-boot-configure-data-source-programmatic
	@Bean
	public DataSource getDataSource() {
		DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create(); 
        dataSourceBuilder.driverClassName("org.postgresql.Driver");
        dataSourceBuilder.url("jdbc:postgresql://localhost:5432/eventos?charSet=LATIN1");
	    dataSourceBuilder.username("postgres"); 
	    dataSourceBuilder.password("postgres"); 
	    return dataSourceBuilder.build(); 
	}
}
	

