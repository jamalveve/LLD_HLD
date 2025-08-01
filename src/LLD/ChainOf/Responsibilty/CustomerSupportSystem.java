package LLD.ChainOf.Responsibilty;

public class CustomerSupportSystem {
	public static void main(String[] args) {
	     SupportHandler level1 = new Level1SupportHandler();
	     SupportHandler level2 = new Level2SupportHandler();
	     SupportHandler level3 = new Level3SupportHandler();

	     level1.setNextHandler(level2);
	     level2.setNextHandler(level3);
	     

	     Request basicRequest = new Request(Priority.BASIC);
	     Request intermediateRequest = new Request(Priority.INTERMEDIATE);
	     Request criticalRequest = new Request(Priority.CRITICAL);

	     level1.handleRequest(basicRequest);          // Handled by Level 1
	     level1.handleRequest(intermediateRequest);    // Passed to Level 2
	     level1.handleRequest(criticalRequest);        // Passed to Level 3
	 }
}

//Priority Enum to classify requests
enum Priority {
 BASIC, INTERMEDIATE, CRITICAL
}

//Request class carrying a priority
class Request {
 private Priority priority;

 public Request(Priority priority) {
     this.priority = priority;
 }

 public Priority getPriority() {
     return priority;
 }
}

//Handler Interface
interface SupportHandler {
 void setNextHandler(SupportHandler nextHandler);
 void handleRequest(Request request);
}

//Concrete Handlers
class Level1SupportHandler implements SupportHandler {
 private SupportHandler nextHandler;

 public void setNextHandler(SupportHandler nextHandler) {
     this.nextHandler = nextHandler;
 }

 public void handleRequest(Request request) {
     if (request.getPriority() == Priority.BASIC) {
         System.out.println("Level 1 Support handled the request.");
     } else if (nextHandler != null) {
         nextHandler.handleRequest(request);
     }
 }
}

class Level2SupportHandler implements SupportHandler {
 private SupportHandler nextHandler;

 public void setNextHandler(SupportHandler nextHandler) {
     this.nextHandler = nextHandler;
 }

 public void handleRequest(Request request) {
     if (request.getPriority() == Priority.INTERMEDIATE) {
         System.out.println("Level 2 Support handled the request.");
     } else if (nextHandler != null) {
         nextHandler.handleRequest(request);
     }
 }
}

class Level3SupportHandler implements SupportHandler {
 public void setNextHandler(SupportHandler nextHandler) {
     // No next handler for Level 3
 }

 public void handleRequest(Request request) {
     if (request.getPriority() == Priority.CRITICAL) {
         System.out.println("Level 3 Support handled the request.");
     } else {
         System.out.println("Request cannot be handled.");
     }
 }
}

//Main class to demonstrate
