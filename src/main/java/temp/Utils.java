package temp;

public class Utils {

	public static void sleepMilliseconds(long ms) {
		try {
			Thread.sleep(ms);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void sleepSeconds(long ms) {
		try {
			Thread.sleep(ms * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
