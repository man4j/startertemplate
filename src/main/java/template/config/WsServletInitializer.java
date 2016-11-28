package template.config;

import org.springframework.ws.transport.http.support.AbstractAnnotationConfigMessageDispatcherServletInitializer;

public class WsServletInitializer extends AbstractAnnotationConfigMessageDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return null;
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] { WsConfig.class };
    }

    @Override
    public boolean isTransformWsdlLocations() {
        return true;
    }
    
    @Override
    protected String[] getServletMappings() {
        return new String[] {"/ws/*"};
    }
}
