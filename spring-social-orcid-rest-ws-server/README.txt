This is an example RESTful web service that demonstrates how to use Spring Social ORCID to enable users 
to sign into a web application via ORCID.

Hopefully, with this example, you'll understand how to integrate Spring Social into your web service. 
And users can sign into their web application via your web service through ORCID.

We have also provided a client written in JavaScript (https://github.com/EuropePMC/spring-social-orcid-rest-ws-example/tree/master/spring-social-orcid-rest-ws-client)
that uses this web service. 

Have fun, and all the best!
Europe PMC development team

------------------------------------
Prerequisite:

1. Spring Social ORCID has not been hosted in the Central Maven Repository. 
You need to download the Spring Social ORCID project from https://github.com/yucigou/spring-social-orcid
And then generate the jar file and install it in your local Maven repository: mvn clean install

2. You need to register an ORCID Application. If not yet, apply for one at:
https://orcid.org/content/register-client-application-production-trusted-party, or
https://orcid.org/content/register-client-application-sandbox

Then you'll be given an ORCID application ID for your application together with its secret. 

3. You need to put the application with its secret in the following Spring properties file:
/src/main/webapp/WEB-INF/spring/social.properties

Accordingly, you need to configure the ORCID API properties in the same file. Please find below the example
social.properties file for ORCID Production API:

orcid.clientId=xxxxxxxxxxxxxxxxxxx
orcid.clientSecret=xxxxxxxxxxxxxxxxxxx

orcid.frontendUrl=https://orcid.org/
orcid.messageSchemaVersion=2.0
orcid.apiUrl=https://api.orcid.org/v${orcid.messageSchemaVersion}/
orcid.pubApiUrl=https://pub.orcid.org/v${orcid.messageSchemaVersion}/
orcid.authorizeUrl=https://orcid.org/oauth/authorize
orcid.accessTokenUrl=https://api.orcid.org/oauth/token

Another example social.properties file for ORCID Sandbox API:

orcid.clientId=xxxxxxxxxxxxxxxxxxx
orcid.clientSecret=xxxxxxxxxxxxxxxxxxx

orcid.frontendUrl=https://sandbox.orcid.org/
orcid.messageSchemaVersion=2.0
orcid.apiUrl=https://api.sandbox.orcid.org/v${orcid.messageSchemaVersion}/
orcid.pubApiUrl=https://pub.sandbox.orcid.org/v${orcid.messageSchemaVersion}/
orcid.authorizeUrl=https://sandbox.orcid.org/oauth/authorize
orcid.accessTokenUrl=https://api.sandbox.orcid.org/oauth/token

------------------------------------
4. Set up your project in your IDE, such as Eclipse. (Optional)

mvn eclipse:eclipse

------------------------------------
5. Generate the war file, i.e., spring-social-orcid-client-1.0.0.war for example

mvn clean install

------------------------------------
6. Run the web application in Tomcat

You can copy the war file to diretory <TOMCAT_HOME>/webapps/, and start Tomcat:
cd <TOMCAT_HOME>/bin
startup.bat

------------------------------------
7. Try the web application

Open your browser, and navigate to http://localhost:8088, and follow the instructions.