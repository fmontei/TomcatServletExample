TomcatServletExample
====================
To launch application,
1) Deploy .class files to E:\CPSC\apache-tomcat-7.0.54\webapps\WEB-INF\classes\mypkg 
                       or [path-to-tomcat]\webapps\WEB-INF\classes\mypkg
2) In the E:\CPSC\apache-tomcat-7.0.54\webapps\WEB-INF\ subdirectory, create web.xml with following:

    .........

    <servlet>
      <servlet-name>TomcatServletExample</servlet-name>
      <servlet-class>mypkg.ServletExample</servlet-class>
    </servlet>
    
    .........
  
    <servlet-mapping>
      <servlet-name>TomcatServletExample</servlet-name>
      <url-pattern></url-pattern>
    </servlet-mapping>
    
3) Navigate to http://localhost:8080/TomcatServletExample

NOTE: %CATALINA_HOME% environment variable must be set up. 
Go to %CATALINA_HOME%\bin and type startup in command prompt to initiate server.
Do not exit out of window that opens.
