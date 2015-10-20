package zhy2002.ejbtest;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Properties;

/**
 * EJB client.
 * Reference: https://docs.jboss.org/author/display/AS71/Remote+EJB+invocations+via+JNDI+-+EJB+client+API+or+remote-naming+project
 */
public class EjbClient {

     public static void main(String[] args) throws NamingException {

         System.out.println("Connect to the server...");

         Properties properties = new Properties();
         properties.put("jboss.naming.client.ejb.context", true); //Must set this to true without jboss-ejb-client.properties file.
         InitialContext initialContext = new InitialContext(properties);
         Context serverContext = (Context)initialContext.lookup("ejb_test_server_EJB_exploded");
         SampleServiceRemote sampleService = (SampleServiceRemote) serverContext.lookup("SampleServiceStatelessBean!zhy2002.ejbtest.SampleServiceRemote");
         String serverMessage = sampleService.getMessage("EjbClient");
         System.out.println(serverMessage);

     }
}
