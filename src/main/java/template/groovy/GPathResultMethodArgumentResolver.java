package template.groovy;

import java.util.Properties;

import javax.xml.transform.Source;
import javax.xml.transform.sax.SAXResult;

import org.springframework.core.MethodParameter;
import org.springframework.ws.context.MessageContext;
import org.springframework.ws.server.endpoint.adapter.method.MethodArgumentResolver;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.xml.transform.TransformerObjectSupport;

import groovy.util.XmlSlurper;
import groovy.util.slurpersupport.GPathResult;

public class GPathResultMethodArgumentResolver extends TransformerObjectSupport implements MethodArgumentResolver {
    private Properties namespaces = new Properties();

    public GPathResultMethodArgumentResolver() {
        this.namespaces.put("soapenv", "http://schemas.xmlsoap.org/soap/envelope/");
    }

    public GPathResultMethodArgumentResolver(Properties namespaces) {
        this();
        this.namespaces.putAll(namespaces);
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterAnnotation(RequestPayload.class) != null &&
               (parameter.getParameterType().equals(GPathResult.class) ||
                parameter.getParameterType().equals(Object.class)); // Object means 'def' support for Groovy
    }

    @Override
    public Object resolveArgument(MessageContext messageContext, MethodParameter parameter) throws Exception {
        Source source = messageContext.getRequest().getPayloadSource();
        if (source == null) return null;
        XmlSlurper slurper = new XmlSlurper();
        transform(source, new SAXResult(slurper));
        GPathResult doc = slurper.getDocument();
        doc.declareNamespace(this.namespaces);
        return doc;
    }
}
