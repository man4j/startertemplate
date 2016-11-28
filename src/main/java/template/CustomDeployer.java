package template;

import org.springframework.ws.transport.http.MessageDispatcherServlet;

import io.undertow.servlet.Servlets;
import starter.deployer.Deployer;

public class CustomDeployer extends Deployer {
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
        
        deploymentInfo.addServlet(Servlets.servlet("ws", MessageDispatcherServlet.class)
                      .addInitParam("transformWsdlLocations", "true")
                      .addMapping("/ws/*")
                      .setLoadOnStartup(2));
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
