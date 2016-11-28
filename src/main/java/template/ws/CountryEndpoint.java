package template.ws;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.ls.DOMImplementationLS;

import groovy.lang.Binding;
import groovy.lang.GroovyCodeSource;
import groovy.lang.GroovyShell;

@Endpoint
public class CountryEndpoint {
    private static final String NAMESPACE_URI = "http://spring.io/guides/gs-producing-web-service";
    
    public static final String ECHO_RESPONSE_LOCAL_NAME = "getCountryResponse";
    
    private final DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCountryRequest")
    @ResponsePayload
    public Element getCountry(@RequestPayload Element e) throws ParserConfigurationException {
        String str = element2String(e);
        
        System.out.println(str);
        
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document doc = documentBuilder.newDocument();

        Element responseElement = doc.createElementNS(NAMESPACE_URI, ECHO_RESPONSE_LOCAL_NAME);
        Element countryElement = doc.createElementNS(NAMESPACE_URI, "country");
        
        Element nameElement = doc.createElementNS(NAMESPACE_URI, "name");
        nameElement.appendChild(doc.createTextNode("RUSSIA"));
        
        Element populationElement = doc.createElementNS(NAMESPACE_URI, "population");
        populationElement.appendChild(doc.createTextNode("140000000"));
        
        countryElement.appendChild(nameElement);
        countryElement.appendChild(populationElement);
        responseElement.appendChild(countryElement);

        return responseElement;
    }

    private String element2String(Element e) {
        DOMImplementationLS domImplLS = (DOMImplementationLS) e.getOwnerDocument().getImplementation();
        
        return domImplLS.createLSSerializer().writeToString(e);
    }
    
    public static void main(String[] args) throws IOException {
        String xml = "<list>" +
                "       <technology>" +
                "         <name>Groovyasdasdasd</name>" +
                "       </technology>" +
                "     </list>";
        
        Binding b = new Binding();
        
        GroovyShell shell = new GroovyShell(b);
        
        b.setProperty("xml", xml);

        GroovyCodeSource groovyCodeSource = new GroovyCodeSource(new File("src/main/java/template/ws/Test.groovy"));
        
        System.out.println(shell.evaluate(groovyCodeSource));
    }
}
