import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import org.apache.log4j.PropertyConfigurator;
import java.util.Properties;

Properties props = new Properties();
props.setProperty("property.log.name", "iam.demo");
PropertyConfigurator.configure(props);

Logger logger = LogManager.getLogger("iam.demo");

System.out.println(logger);
System.out.println(logger.getClass());
System.out.println(logger.getName());
Enumeration appenders = logger.getAllAppenders();
while(appenders.hasMoreElements()) {
  Appender appender = (Appender) appenders.nextElement();
  System.out.println("appender = " + appender);
}

import bsh.Interpreter;
Interpreter interpreter = this.interpreter;

for(Object variable: this.variables) {
  logger.info("---");
  Object value = interpreter.get(variable);
  logger.info(variable + ": " + value);
  if (value != null)
  logger.info("-> " + value.getClass());
}

return requestEndPoint;