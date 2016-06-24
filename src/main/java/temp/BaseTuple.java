package temp;

public class BaseTuple implements Tuple {

	protected long ts;

	public BaseTuple() {
		ts = System.currentTimeMillis();
	}

	public BaseTuple(long ts) {
		this.ts = ts;
	}

	public long getTimestamp() {
		return ts;
	}

}
