package com.weread.web;

import org.apache.catalina.connector.Connector;
import org.apache.coyote.http11.Http11AprProtocol;
import org.springframework.boot.web.embedded.tomcat.TomcatConnectorCustomizer;

public class MyTomcatConnectorCustomizer implements TomcatConnectorCustomizer {
	
	private TomcatConfig config;
	
	public MyTomcatConnectorCustomizer(TomcatConfig config){
		this.config = config;
	}
	
    public void customize(Connector connector) {  
        Http11AprProtocol protocol = (Http11AprProtocol) connector.getProtocolHandler();
        //设置最大连接数  
        if(config.getMaxConn()>0)
        	protocol.setMaxConnections(config.getMaxConn()); 
        //设置最大线程数  
        if(config.getMaxThreads()>0)
        	protocol.setMaxThreads(config.getMaxThreads());
        if(config.getConnTimeout()>0)
        	protocol.setConnectionTimeout(config.getConnTimeout());
    }  
}
