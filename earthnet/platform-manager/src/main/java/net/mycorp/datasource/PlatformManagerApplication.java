package net.mycorp.datasource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.cassandra.CassandraDataAutoConfiguration;
import org.springframework.boot.autoconfigure.data.elasticsearch.ElasticsearchDataAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.XADataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class,
        CassandraDataAutoConfiguration.class, XADataSourceAutoConfiguration.class, ElasticsearchDataAutoConfiguration.class})
@ComponentScan(basePackages = "net.mycorp.datasource")
//@EnableWebMvc
//@EnableWebSecurity
public class PlatformManagerApplication {
    public static void main(String[] args) {
        SpringApplication.run(PlatformManagerApplication.class, args);
    }
}
