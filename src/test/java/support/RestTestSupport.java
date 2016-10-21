package support;

import java.io.IOException;
import java.sql.SQLException;

import javax.script.ScriptException;

import org.apache.commons.io.IOUtils;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.testcontainers.containers.MySQLContainer;

import template.CustomDeployer;

public class RestTestSupport {
    private static CustomDeployer deployer;
    
    @SuppressWarnings("rawtypes")
    private static MySQLContainer mysql;
    
    @SuppressWarnings("rawtypes")
    @BeforeClass
    public static void before() throws SQLException, ScriptException, IOException {
        mysql = new MySQLContainer() {
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
        
        try (java.sql.Connection conn = mysql.createConnection("")) {
            org.testcontainers.jdbc.ext.ScriptUtils.executeSqlScript(conn, "", IOUtils.toString(SpringMvcTestContainersSupport.class.getResourceAsStream("/dump.sql")));
        }
        
        System.setProperty("db.url", mysql.getJdbcUrl());
        System.setProperty("db.user", mysql.getUsername());
        System.setProperty("db.password", mysql.getPassword());
        
        System.setProperty("spring.profiles.active", "integration");
        
        deployer = new CustomDeployer();
        deployer.deploy();
    }
    
    @AfterClass
    public static void stopContainer() {
        deployer.undeploy();
        mysql.stop();
    }
    
    public static String getWebURL() {
        return "http://localhost:8080";
    }
}
