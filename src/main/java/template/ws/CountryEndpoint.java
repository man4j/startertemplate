package template.ws;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import groovy.util.slurpersupport.GPathResult;
import template.groovy.MyGroovyXmlProcessor;

@Endpoint
public class CountryEndpoint {
    private static final String NAMESPACE_URI = "http://spring.io/guides/gs-producing-web-service";
    
    @Autowired
    private MyGroovyXmlProcessor xmlProcessor;
    
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCountryRequest")
    @ResponsePayload
    public String getCountry(@RequestPayload GPathResult request) {
        return xmlProcessor.getCountry(request);
    }
}
