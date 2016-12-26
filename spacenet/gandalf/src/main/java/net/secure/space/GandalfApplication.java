package net.secure.space;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.cassandra.CassandraDataAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.XADataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class, CassandraDataAutoConfiguration.class, XADataSourceAutoConfiguration.class})
@ComponentScan(basePackages = "net.secure.space")
@EnableWebMvc
@EnableWebSecurity
@RestController
public class GandalfApplication {

	public static void main(String[] args) {
		SpringApplication.run(GandalfApplication.class, args);
	}

	@RequestMapping(value = "/status", method = RequestMethod.GET)
	public String checkGandalfHealth () {
		return "Gandalf is fine. Please continue or your task.";
	}
}

