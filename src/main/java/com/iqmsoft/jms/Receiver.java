package com.iqmsoft.jms;

import java.util.Properties;

import javax.jms.Destination;
import javax.jms.JMSConsumer;
import javax.jms.Message;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;

public class Receiver {
    private static final String USERNAME = "guest";
    private static final String PASSWORD = "guest";
    private static final String INITIAL_CONTEXT_FACTORY = "org.jboss.naming.remote.client.InitialContextFactory";
    private static final String PROVIDER = "http-remoting://localhost:8080";
    public static JMSConsumer prepare() throws Exception{
        final Properties env = new Properties();
        env.put(Context.INITIAL_CONTEXT_FACTORY, INITIAL_CONTEXT_FACTORY);
        env.put(Context.PROVIDER_URL, PROVIDER);
        env.put(Context.SECURITY_PRINCIPAL, USERNAME);
        env.put(Context.SECURITY_CREDENTIALS, PASSWORD);
        Context namingContext = new InitialContext(env);
		Destination destination = (Destination) namingContext.lookup("jms/topic/testTopic");//"jms/queue/test"
		return JMSContextBuilder.getJMSContext().createConsumer(destination);
    }
	public static void main(String[] args) throws Exception {
        JMSConsumer consumer = prepare();
		Message m = null;
		while(true){
			m = consumer.receiveNoWait();
			if(m instanceof TextMessage){
				System.out.println(((TextMessage)m).getText());
			}
		}
	}
}
