
public class Clock {

	private long prev;
	
	/**
	 * Constructs a clock with previous set to the current time.
	 */
	Clock(){
		prev = System.currentTimeMillis();
	}
	
	/**
	 * Brings back the elapsed time and resets the clock.
	 * @return The elapsed time.
	 */
	public long delta(){
		long current = System.currentTimeMillis();
		long previous = prev;
	
		prev = current;
		return (current - previous);
	}
	
	/**
	 * Brings back the elapsed time but does not reset the clock.
	 * @return The elapsed time.
	 */
	public long split(){
		long current = System.currentTimeMillis();
		return (current - prev);
	}
}
