package br.com.kamaleon.config;

import java.beans.PropertyVetoException;
import java.util.HashMap;
import java.util.Map;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.postgresql.ds.PGSimpleDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by @author Renan Oliveira on 08/05/14
 */
@Configuration
@ComponentScan(basePackages = {"br.com.kamaleon.service", "br.com.kamaleon.security", "br.com.kamaleon.dao"})
@EnableJpaRepositories(basePackages = "br.com.kamaleon.dao")
@EnableTransactionManagement
public class SpringConfig {
	
    
  @Bean
	public PGSimpleDataSource dataSource() throws PropertyVetoException {

	  	PGSimpleDataSource dataSource = new PGSimpleDataSource();
				
		dataSource.setUrl("jdbc:postgresql://localhost:5432/teste");
		dataSource.setUser("postgres");
		dataSource.setPassword("postgres");
		
		return dataSource;
	}

	@Bean
	public PersistenceExceptionTranslationPostProcessor persistenceExceptionTranslationPostProcessor()
	{
		return new PersistenceExceptionTranslationPostProcessor();
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() throws PropertyVetoException {
		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();

		HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
		adapter.setGenerateDdl(true);
		adapter.setShowSql(true);
		factory.setJpaVendorAdapter(adapter);

		factory.setDataSource(dataSource());
		factory.setPackagesToScan("br.com.kamaleon.model");
		factory.setPersistenceProvider(new HibernatePersistenceProvider());
		factory.setJpaPropertyMap(jpaPropertyMap());
		return factory;
	}

	public Map<String, String> jpaPropertyMap()
	{
		Map<String, String> map = new HashMap<String, String>();
		map.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
		map.put("hibernate.show_sql", "false");
		map.put("hibernate.format_sql", "false");
		map.put("hibernate.hbm2ddl.auto", "update");
		return map;
	}

	@Bean
	public JpaTransactionManager transactionManager() throws PropertyVetoException {
		JpaTransactionManager manager = new JpaTransactionManager();
		manager.setEntityManagerFactory(entityManagerFactory().getObject());
		return manager;
	}
}
