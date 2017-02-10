package net.mycorp.vo.platform;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.data.cassandra.core.CassandraTemplate;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Connection;

/**
 * Created by AjayMenon on 1/21/2017.
 */
public class Platform {

    //these 3 defines the platform.
    private String platformUrl;
    private String platformPort;
    private Credentials platformCredentials; // to be on a platform you need ure credentials. Can be as complicated as you like

    //What type do you want to return.
    private Connection connection;
    private Session session;
    private SessionFactory sessionFactory;
    private CassandraTemplate cassandraTemplate;
    private JdbcTemplate jdbcTemplate;

    public Platform(final String platformUrl,final String platformPort,final Credentials platformCredentials){
        this.platformUrl = platformUrl;
        this.platformPort = platformPort;
        this.platformCredentials = platformCredentials;
    }

    //getters and setter.
    public String getPlatformUrl() {
        return platformUrl;
    }

    public void setPlatformUrl(String platformUrl) {
        this.platformUrl = platformUrl;
    }

    public String getPlatformPort() {
        return platformPort;
    }

    public void setPlatformPort(String platformPort) {
        this.platformPort = platformPort;
    }

    public Credentials getPlatformCredentials() {
        return platformCredentials;
    }

    public void setPlatformCredentials(Credentials platformCredentials) {
        this.platformCredentials = platformCredentials;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public CassandraTemplate getCassandraTemplate() {
        return cassandraTemplate;
    }

    public void setCassandraTemplate(CassandraTemplate cassandraTemplate) {
        this.cassandraTemplate = cassandraTemplate;
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
