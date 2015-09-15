
public class HelloWorld {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		long before = System.currentTimeMillis();
		System.out.println("Hellow World");
		try {
		    Thread.sleep(100000);                 //1000 milliseconds is one second.
		} catch(InterruptedException ex) {
		    Thread.currentThread().interrupt();
		}
		long after = System.currentTimeMillis();
		System.out.println("Time to print \"Hellow World\": " + (after - before));
	}

}
