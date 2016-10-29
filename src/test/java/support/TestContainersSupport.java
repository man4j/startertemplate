package support;

import org.flywaydb.core.Flyway;
import org.testcontainers.containers.MySQLContainer;

public class TestContainersSupport {
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
        
//        importDump(mysql);
        migrate(mysql);
        setSystemVars(mysql);
        
        return mysql;
    }

//    @SuppressWarnings("rawtypes")
//    private static void importDump(MySQLContainer mysql) throws ScriptException, IOException, SQLException {
//        try (java.sql.Connection conn = mysql.createConnection("")) {
//            org.testcontainers.jdbc.ext.ScriptUtils.executeSqlScript(conn, "", IOUtils.toString(SpringMvcTestContainersSupport.class.getResourceAsStream("/dump.sql")));
//        }
//    }

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
