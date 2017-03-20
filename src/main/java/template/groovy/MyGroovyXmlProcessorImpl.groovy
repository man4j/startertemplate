package template.groovy;

import groovy.util.slurpersupport.GPathResult
import groovy.util.slurpersupport.NodeChild
import groovy.xml.MarkupBuilder

public class MyGroovyXmlProcessorImpl implements MyGroovyXmlProcessor {
    private static final String NAMESPACE_URI = "http://spring.io/guides/gs-producing-web-service";
    
    @Override
    public String getCountry(GPathResult request) {
        StringWriter xmlWriter = new StringWriter()
        MarkupBuilder xmlMarkup = new MarkupBuilder(xmlWriter);
        
        xmlMarkup
            ."gs:getCountryResponse"("xmlns:gs": NAMESPACE_URI) {
                for (NodeChild n : request.name) {
                  "gs:country" {
                    "gs:name"(n)
                    "gs:population"(new Random().nextInt(100_000_000).toString())
                  }
                }
            }
            
        return xmlWriter.toString();
    }
}