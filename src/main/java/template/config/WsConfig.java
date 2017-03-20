package template.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.server.endpoint.adapter.method.MethodArgumentResolver;
import org.springframework.ws.server.endpoint.adapter.method.MethodReturnValueHandler;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;

import template.groovy.GPathResultMethodArgumentResolver;
import template.groovy.StringMethodReturnValueHandler;

@EnableWs
@Configuration
@ComponentScan(basePackages = {"template.ws"})
@ImportResource("classpath:/groovy-config.xml")
public class WsConfig extends WsConfigurerAdapter {
    @Bean(name = "countries")
    public DefaultWsdl11Definition countriesByNameWsdl11Definition() throws Exception {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        
        SimpleXsdSchema schema = new SimpleXsdSchema(new ClassPathResource("countries.xsd"));
        schema.afterPropertiesSet();
        
        wsdl11Definition.setPortTypeName("CountriesPort");
        wsdl11Definition.setLocationUri("/ws");
        wsdl11Definition.setSchema(schema);
        
        return wsdl11Definition;
    }
    
    @Override
    public void addArgumentResolvers(List<MethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(new GPathResultMethodArgumentResolver());
    }
    
    @Override
    public void addReturnValueHandlers(List<MethodReturnValueHandler> returnValueHandlers) {
        returnValueHandlers.add(new StringMethodReturnValueHandler());
    }
}
