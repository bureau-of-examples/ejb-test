How to set up JBoss
====
1. Go to standalone.xml and remove the security-realm attribute in remoting config.
2. In order to deploy the ejb to JBoss in IntelliJ, you need to configure the EJB facet for the EJB jar.

Below is a good reference for setting up standalone JNDI based EJB client:
http://stackoverflow.com/questions/14336478/jboss-7-jndi-lookup