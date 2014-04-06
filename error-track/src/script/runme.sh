#!/bin/bash          

echo Running Error Tracking...
java -verbose -classpath activation-1.1.jar:slf4j-api-1.6.6.jar:aopalliance-1.0.jar:slf4j-log4j12-1.6.6.jar:commons-lang3-3.1.jar:spring-aop-3.2.7.RELEASE.jar:commons-logging-1.1.3.jar:spring-beans-3.2.7.RELEASE.jar:spring-context-3.2.7.RELEASE.jar:guava-13.0.1.jar:spring-context-support-3.2.7.RELEASE.jar:log4j-1.2.16.jar:spring-core-3.2.7.RELEASE.jar:mail-1.4.jar:spring-expression-3.2.7.RELEASE.jar:quartz-1.8.6.jar:spring-tx-3.2.7.RELEASE.jar -jar error-track-1.0-SNAPSHOT.jar com.captaindebug.errortrack.Main
