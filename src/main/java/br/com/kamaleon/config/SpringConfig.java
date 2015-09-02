package br.com.kamaleon.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
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
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setPackagesToScan("br.com.kamaleon.model");
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(hibernateProperties());
        return em;
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(emf);
        return transactionManager;
    }

    private Properties hibernateProperties() {
        Properties properties = new Properties();
        // hibernate-hikaricp connection pool
        properties.setProperty("hibernate.connection.provider_class", com.zaxxer.hikari.hibernate.HikariConnectionProvider.class.getName());
        properties.setProperty("hibernate.dialect", org.hibernate.dialect.PostgreSQL9Dialect.class.getName());
        properties.setProperty("hibernate.hbm2ddl.auto", "create");
        properties.setProperty("hibernate.hikari.connectionTestQuery", "SELECT 1");
        properties.setProperty("hibernate.hikari.dataSourceClassName",  org.postgresql.ds.PGSimpleDataSource.class.getName());
        properties.setProperty("hibernate.hikari.dataSource.url", "jdbc:postgresql://localhost:5432/teste");
        properties.setProperty("hibernate.hikari.dataSource.user", "postgres");
        properties.setProperty("hibernate.hikari.dataSource.password", "postgres");
        return properties;
    }
}	
   /* @Bean
    public DataSource configureDataSource() {
        HikariConfig config = new HikariConfig();
        config.setDataSourceClassName("");
        config.setDriverClassName("org.postgresql.Driver");
        config.setJdbcUrl("jdbc:postgresql://localhost:5432/teste");
        config.setUsername("postgres");
        config.setPassword("postgres");
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        config.addDataSourceProperty("useServerPrepStmts", "true");

        return new HikariDataSource(config);
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean configureEntityManagerFactory() {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(configureDataSource());
        entityManagerFactoryBean.setPackagesToScan("br.com.kamaleon.model");
        entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

        Properties jpaProperties = new Properties();
        jpaProperties.put(org.hibernate.cfg.Environment.DIALECT, "org.hibernate.dialect.PostgreSQLDialect");
        jpaProperties.put(org.hibernate.cfg.Environment.HBM2DDL_AUTO, "auto");
        entityManagerFactoryBean.setJpaProperties(jpaProperties);

        return entityManagerFactoryBean;
    }
	
	@Bean
	public PlatformTransactionManager annotationDrivenTransactionManager() {
        return new JpaTransactionManager();
	}
	public ComboPooledDataSource dataSource() throws PropertyVetoException {

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
*/
