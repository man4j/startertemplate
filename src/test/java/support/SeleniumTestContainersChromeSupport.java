package support;

import java.io.IOException;
import java.sql.SQLException;

import javax.script.ScriptException;

import org.apache.commons.io.IOUtils;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testcontainers.containers.BrowserWebDriverContainer;
import org.testcontainers.containers.BrowserWebDriverContainer.VncRecordingMode;
import org.testcontainers.containers.MySQLContainer;

import template.CustomDeployer;

public class SeleniumTestContainersChromeSupport {
    @SuppressWarnings("rawtypes")
    private static BrowserWebDriverContainer chrome;
    
    @SuppressWarnings("rawtypes")
    private static MySQLContainer mysql;
    
    private static String WEB_URL;
    
    private static CustomDeployer deployer;
    
    @SuppressWarnings("rawtypes")
    @BeforeClass
    public static void startContainer() throws SQLException, ScriptException, IOException, UnsupportedOperationException, InterruptedException {
        chrome = new BrowserWebDriverContainer().withDesiredCapabilities(DesiredCapabilities.chrome())
                                                .withRecordingMode(VncRecordingMode.SKIP, null);
        chrome.start();
        
        String host;
        
        if (System.getenv("WEB_HOST") != null) {
            host = System.getenv("WEB_HOST");
        } else {
            host = chrome.execInContainer("ip", "route", "show").getStdout().split(" ")[2];
        }
        
        WEB_URL = "http://" + host + ":8080/";
        
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
        chrome.stop();
        deployer.undeploy();
        mysql.stop();
    }
    
    public RemoteWebDriver getDriver() {
        return chrome.getWebDriver();
    }
    
    public static String getWebURL() {
        return WEB_URL;
    }
}
