package support;

import org.junit.BeforeClass;

public class RestTestSupport {
    private static String WEB_URL = System.getProperty("web.url");
    
    @BeforeClass
    public static void before() {
        if (WEB_URL == null) {
            WEB_URL = "http://127.0.0.1:8080";
        }
    }
    
    public static String getWebURL() {
        return WEB_URL;
    }
}
