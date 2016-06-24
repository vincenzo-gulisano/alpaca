package temp;

public abstract class BaseSingleThreadedOperator implements
		SingleThreadedOperator {

	protected String id;
	protected ChannelPool channelPool;

	protected boolean limitOutputSize;
	protected long outputSizeLimit;

	protected boolean backOff;
	protected BackOffRegulator backOffRegulator;

	public void fedBy(String... ids) {
		// TODO Auto-generated method stub

	}

	public void run() {
		processTuple();
	}

}
