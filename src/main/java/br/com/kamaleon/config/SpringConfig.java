package br.com.kamaleon.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.beans.PropertyVetoException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by @author Renan Oliveira on 08/05/14
 */
@Configuration
@ComponentScan(basePackages = {"br.com.kamaleon.service", "br.com.kamaleon.security", "br.com.kamaleon.dao"})
@EnableJpaRepositories(basePackages = "br.com.kamaleon.dao")
@EnableTransactionManagement
public class SpringConfig {

    //åˆ�å§‹åŒ–æ•°æ�®æº�
    @Bean
    public ComboPooledDataSource dataSource() throws PropertyVetoException {

        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setDriverClass("oracle.jdbc.OracleDriver");
        dataSource.setJdbcUrl("jdbc:oracle:thin:@201.28.44.202:1525:OSCARFULL");
        dataSource.setUser("mstore");
        dataSource.setPassword("mstore_123_orcl");
        dataSource.setInitialPoolSize(10);
        dataSource.setMaxPoolSize(100);
        dataSource.setMaxIdleTime(60);
        dataSource.setIdleConnectionTestPeriod(60);
        dataSource.setAutoCommitOnClose(true);
		
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
        map.put("hibernate.dialect", "org.hibernate.dialect.OracleDialect");
        map.put("hibernate.show_sql", "true");
        map.put("hibernate.format_sql", "false");
        map.put("hibernate.hbm2ddl.auto", "none");
        return map;
    }

    @Bean
    public JpaTransactionManager transactionManager() throws PropertyVetoException {
        JpaTransactionManager manager = new JpaTransactionManager();
        manager.setEntityManagerFactory(entityManagerFactory().getObject());
        return manager;
    }

}
