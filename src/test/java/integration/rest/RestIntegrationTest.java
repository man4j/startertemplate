package integration.rest;

import java.util.concurrent.ExecutionException;

import org.junit.Assert;
import org.junit.Test;

import com.ning.http.client.AsyncHttpClient;

import support.RestTestSupport;

public class RestIntegrationTest extends RestTestSupport {
    @Test
    public void shouldWork() throws InterruptedException, ExecutionException {
        try(AsyncHttpClient client = new AsyncHttpClient()) {
            Assert.assertEquals(200, client.prepareGet(getWebURL() + "/auth/signin").execute().get().getStatusCode());
        }
    }
}
