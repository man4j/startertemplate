package template;

import java.util.Collections;
import java.util.Set;

import starter.deployer.Deployer;
import template.config.WsServletInitializer;

public class CustomDeployer extends Deployer {
    @Override
    protected Set<Class<?>> configureSpringInitializers() {
        return Collections.singleton(WsServletInitializer.class);
    }
    
    @Override
    protected void configureContextParams() {
        super.configureContextParams();

        deploymentInfo.addInitParameter("contextConfigLocation", "template.config.CustomDbConfig,"
                + "template.config.CustomMvcConfig,"
                + "template.config.CustomSecurityConfig,"
                + "template.config.CustomSocialConfig");
    }
    
    @Override
    protected void configureFilters() {
        super.configureFilters();
    }

    @Override
    protected void configureListeners() {
        super.configureListeners();
    }

    @Override
    protected void configureServlets() {
        super.configureServlets();        
    }

    @Override
    protected void configureContextPath() {
        super.configureContextPath();
    }

    @Override
    protected void configureDeploymentName() {
        super.configureDeploymentName();
    }

    @Override
    protected void configureServer() {
        super.configureServer();
    }
}
