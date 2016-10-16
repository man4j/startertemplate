package template;

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
