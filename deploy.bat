gradle build
del C:\kamaleon\projetos\admoscar\apache-tomcat-7.0.53\webapps\adm-oscar.war
rd /s /q C:\kamaleon\projetos\admoscar\apache-tomcat-7.0.53\webapps\adm-oscar
copy build\libs\adm-oscar.war C:\kamaleon\projetos\admoscar\apache-tomcat-7.0.53\webapps