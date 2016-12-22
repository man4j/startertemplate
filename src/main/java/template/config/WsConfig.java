package template.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;

@EnableWs
@Configuration
@ComponentScan(basePackages = {"template.ws"})
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
}
