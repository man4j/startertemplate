package template.groovy;

import org.springframework.core.MethodParameter;
import org.springframework.ws.context.MessageContext;
import org.springframework.ws.server.endpoint.adapter.method.MethodReturnValueHandler;
import org.springframework.xml.transform.StringSource;
import org.springframework.xml.transform.TransformerObjectSupport;

public class StringMethodReturnValueHandler extends TransformerObjectSupport implements MethodReturnValueHandler {
    @Override
    public boolean supportsReturnType(MethodParameter returnType) {
        return returnType.getParameterType().equals(String.class);
    }

    @Override
    public void handleReturnValue(MessageContext messageContext, MethodParameter returnType, Object returnValue) throws Exception {
        transform(new StringSource((String) returnValue), messageContext.getResponse().getPayloadResult());
    }
}
