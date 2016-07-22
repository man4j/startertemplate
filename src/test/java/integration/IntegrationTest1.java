package integration;

import java.util.concurrent.ExecutionException;

import org.junit.Assert;
import org.junit.Test;

import com.ning.http.client.AsyncHttpClient;

public class IntegrationTest1 {
    @Test
    public void shouldWork() throws InterruptedException, ExecutionException {
        String baseUrl = System.getProperty("undertow.url");
        
        System.out.println(baseUrl);
        
        try(AsyncHttpClient client = new AsyncHttpClient()) {
            Assert.assertEquals(200, client.prepareGet(baseUrl + "/auth/signin").execute().get().getStatusCode());
        }
    }
}
