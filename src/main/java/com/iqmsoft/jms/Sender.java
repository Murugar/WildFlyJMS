package com.iqmsoft.jms;

import java.util.Properties;
import java.util.Scanner;

import javax.jms.Destination;
import javax.jms.JMSProducer;
import javax.naming.Context;
import javax.naming.InitialContext;

public class Sender {
    private static final String USERNAME = "guest";
    private static final String PASSWORD = "guest";
    private static final String INITIAL_CONTEXT_FACTORY = "org.jboss.naming.remote.client.InitialContextFactory";
    private static final String PROVIDER = "http-remoting://localhost:8080";
    public static Destination prepare() throws Exception{
        final Properties env = new Properties();
        env.put(Context.INITIAL_CONTEXT_FACTORY, INITIAL_CONTEXT_FACTORY);
        env.put(Context.PROVIDER_URL, PROVIDER);
        env.put(Context.SECURITY_PRINCIPAL, USERNAME);
        env.put(Context.SECURITY_CREDENTIALS, PASSWORD);
        Context namingContext = new InitialContext(env);
		return (Destination) namingContext.lookup("jms/topic/testTopic");
    }
	public static void main(String[] args) throws Exception {
		JMSProducer prod = JMSContextBuilder.getJMSContext().createProducer();
		Destination d = prepare();
		Scanner sc = new Scanner(System.in);
		while(true){
			prod.send(d, sc.nextLine());
		}
	}
}
