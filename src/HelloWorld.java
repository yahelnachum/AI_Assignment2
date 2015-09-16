
public class HelloWorld {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Clock c = new Clock(2000);
		try {
			Thread.sleep(10);                 //1000 milliseconds is one second.
			System.out.println("Over Target Time? " + c.overTargetTime());
			
			Thread.sleep(2000);                 //1000 milliseconds is one second.
			System.out.println("Over Target Time? " + c.overTargetTime());
			
		} catch(InterruptedException ex) {
		    Thread.currentThread().interrupt();
		}
	}

}
