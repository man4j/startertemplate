package support;

import java.io.IOException;
import java.util.Random;

import org.flywaydb.core.Flyway;
import org.testcontainers.containers.MySQLContainer;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

public class TestContainersSupport {
    private static final String DOCKER_HOST_SSH_USER = "root";
    private static final String DOCKER_HOST_SSH_KEY_PATH = System.getProperty("user.home") + "/docker_host.ppk";
    
    @SuppressWarnings("rawtypes")
    public static MySQLContainer createMySQLContainer() {
        MySQLContainer mysql = new MySQLContainer() {
            @Override
            public String getJdbcUrl() {
                return "jdbc:mysql://" + getContainerIpAddress() + ":" + getMappedPort(3306) + "/test_db";
            }
            
            @Override
            protected void configure() {
                optionallyMapResourceParameterAsVolume("TC_MY_CNF", "/etc/mysql/conf.d");

                addExposedPort(3306);
                addEnv("MYSQL_DATABASE", "test_db");
                addEnv("MYSQL_USER", "test");
                addEnv("MYSQL_PASSWORD", "test");
                addEnv("MYSQL_ROOT_PASSWORD", "test");
                setCommand("mysqld");
                setStartupAttempts(3);        
            }
        };
        
        mysql.start();
        migrate(mysql);
        setSystemVars(mysql);
        
        return mysql;
    }
    
    public static Object[] createTunnel() throws IOException {
        try {
            String fullDockerHostPath = System.getenv("DOCKER_HOST");
            String dockerHost = fullDockerHostPath.substring(fullDockerHostPath.indexOf("//") + 2, fullDockerHostPath.lastIndexOf(':'));
    
            JSch jsch = new JSch();
            JSch.setConfig("StrictHostKeyChecking", "no");
            
            jsch.addIdentity(DOCKER_HOST_SSH_KEY_PATH);
            
            Session session = jsch.getSession(DOCKER_HOST_SSH_USER, dockerHost);

            session.connect();
            session.setServerAliveInterval(30000);
            
            int port = new Random().nextInt(10_000) + 20_000;
            
            for (int i = 0; i < 10; i++) {
                try {
                    session.setPortForwardingR("0.0.0.0", port, "localhost", 8080);
                    break;
                } catch (@SuppressWarnings("unused") JSchException e) {
                    port = new Random().nextInt(10_000) + 20_000;
                }
            }
            
            return new Object[] {session, port};
        } catch (Exception e) {
            throw new IOException(e);
        }
    }

    @SuppressWarnings("rawtypes")
    private static void setSystemVars(MySQLContainer mysql) {
        System.setProperty("db.url", mysql.getJdbcUrl());
        System.setProperty("db.user", mysql.getUsername());
        System.setProperty("db.password", mysql.getPassword());
    }

    @SuppressWarnings("rawtypes")
    private static void migrate(MySQLContainer mysql) {
        Flyway fw = new Flyway();
        fw.setDataSource(mysql.getJdbcUrl(), mysql.getUsername(), mysql.getPassword());
        fw.setLocations("classpath:migrations");
        fw.migrate();
    }
}
