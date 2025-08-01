package LLD.ChainOf.Responsibilty;

//AbstractLogger class defining the chain and the method to log messages
abstract class AbstractLogger {
 public static int INFO = 1;
 public static int DEBUG = 2;
 public static int ERROR = 3;

 protected int level;
 protected AbstractLogger nextLogger;

 public void setNextLogger(AbstractLogger nextLogger){
     this.nextLogger = nextLogger;
 }

 public void logMessage(int level, String message) {
     if(this.level <= level){
         write(message);
     }
     if(nextLogger != null){
         nextLogger.logMessage(level, message);
     }
 }

 abstract protected void write(String message);
}

//Concrete logger for INFO level
class ConsoleLogger extends AbstractLogger {

 public ConsoleLogger(int level){
     this.level = level;
 }

 @Override
 protected void write(String message) {
     System.out.println("Console Logger: " + message);
 }
}

//Concrete logger for DEBUG level
class DebugLogger extends AbstractLogger {

 public DebugLogger(int level){
     this.level = level;
 }

 @Override
 protected void write(String message) {
     System.out.println("Debug Logger: " + message);
 }
}

//Concrete logger for ERROR level
class ErrorLogger extends AbstractLogger {

 public ErrorLogger(int level){
     this.level = level;
 }

 @Override
 protected void write(String message) {
     System.out.println("Error Logger: " + message);
 }
}

//Main class to set up the chain and test logging
public class LoggerChain {

 private static AbstractLogger getChainOfLoggers(){

     AbstractLogger errorLogger = new ErrorLogger(AbstractLogger.ERROR);
     AbstractLogger debugLogger = new DebugLogger(AbstractLogger.DEBUG);
     AbstractLogger consoleLogger = new ConsoleLogger(AbstractLogger.INFO);

     // Set chain: ERROR -> DEBUG -> INFO
     errorLogger.setNextLogger(debugLogger);
     debugLogger.setNextLogger(consoleLogger);

     return errorLogger;
 }

 public static void main(String[] args){
     AbstractLogger loggerChain = getChainOfLoggers();

     loggerChain.logMessage(AbstractLogger.INFO, "This is an informational message.");
     loggerChain.logMessage(AbstractLogger.DEBUG, "This is a debug level message.");
     loggerChain.logMessage(AbstractLogger.ERROR, "This is an error message.");
 }
}
