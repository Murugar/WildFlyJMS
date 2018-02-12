package com.iqmsoft.jms;

import java.util.Properties;

import javax.jms.ConnectionFactory;
import javax.jms.JMSContext;
import javax.naming.Context;
import javax.naming.InitialContext;

public class JMSContextBuilder{
	    private static final String CONNECTION_FACTORY = "jms/RemoteConnectionFactory";
	    private static final String USERNAME = "guest";
	    private static final String PASSWORD = "guest";
	    private static final String INITIAL_CONTEXT_FACTORY = "org.jboss.naming.remote.client.InitialContextFactory";
	    private static final String PROVIDER = "http-remoting://localhost:8080";
	    public static JMSContext getJMSContext() throws Exception {
	        Context namingContext = null;
	        JMSContext context = null;
	        try {
	            Properties env = new Properties();
	            env.put(Context.INITIAL_CONTEXT_FACTORY, INITIAL_CONTEXT_FACTORY);
	            env.put(Context.PROVIDER_URL, PROVIDER);
	            env.put(Context.SECURITY_PRINCIPAL, USERNAME);
	            env.put(Context.SECURITY_CREDENTIALS, PASSWORD);
	            namingContext = new InitialContext(env);
	            ConnectionFactory connectionFactory = (ConnectionFactory) namingContext.lookup(CONNECTION_FACTORY);
	            context = connectionFactory.createContext(USERNAME, PASSWORD);
	        } catch (Exception e) {
	            e.printStackTrace();
	            throw e;
	        } 
	        return context;
	    }
}
