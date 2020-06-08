package com.weread.web;

import java.io.File;

import org.apache.catalina.core.AprLifecycleListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.autoconfigure.web.embedded.TomcatWebServerFactoryCustomizer;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.StandardEnvironment;

@Configuration
public class WebServerConfiguration {
	
	AprLifecycleListener arpLifecycle = null;
	@Autowired
	private TomcatConfig config;
	@Autowired
	private ServerProperties ServerProperties;
	
    @Bean  
    public TomcatWebServerFactoryCustomizer createWebServerFactoryCustomizer() {  
    	TomcatWebServerFactoryCustomizer customizer = new TomcatWebServerFactoryCustomizer(new StandardEnvironment(),ServerProperties);
    	TomcatServletWebServerFactory tomcatFactory = new TomcatServletWebServerFactory();
        arpLifecycle = new AprLifecycleListener();
        tomcatFactory.setProtocol("org.apache.coyote.http11.Http11AprProtocol");
        tomcatFactory.setPort(config.getPort());
        tomcatFactory.setBaseDirectory(new File(System.getProperty("user.dir")));
        tomcatFactory.addConnectorCustomizers(new MyTomcatConnectorCustomizer(config));
        customizer.customize(tomcatFactory);
        return customizer;
    }
}
