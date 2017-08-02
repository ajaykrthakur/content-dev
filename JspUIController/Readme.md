# steps
1. create maven webapp project
2. add jquery to the index.jsp
3. write jquery get to fetch response from xmlparser service.
4. add webserver (tomcat) plugin to the POM. use a different port as 8080 being used by xmlparser app tomcat.
5. maven install.
6. maven run with tomcat7:run
7. add the new tomcat port to @CrossOrigin in xml parser content processor class. 