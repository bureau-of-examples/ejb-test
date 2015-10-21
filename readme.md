How to set up JBoss
====
1. Go to standalone.xml and remove the security-realm attribute in remoting config.
2. In order to deploy the ejb to JBoss in IntelliJ, you need to configure the EJB facet for the EJB jar.

Below is a good reference for setting up standalone JNDI based EJB client:
http://stackoverflow.com/questions/14336478/jboss-7-jndi-lookup

Reference:
https://docs.jboss.org/author/display/AS71/Remote+EJB+invocations+via+JNDI+-+EJB+client+API+or+remote-naming+project

JBoss AS Developer Guide:
https://docs.jboss.org/author/display/AS71/Developer+Guide

EJB life cycle:
https://docs.oracle.com/javaee/7/tutorial/ejb-intro007.htm#GIPLJ