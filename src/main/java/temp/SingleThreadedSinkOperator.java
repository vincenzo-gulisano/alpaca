package temp;

public class SingleThreadedSinkOperator<T extends Tuple> extends
		BaseSingleThreadedOperator {

	private Sink<T> sink;
	private String inputChannel;

	@SuppressWarnings("unchecked")
	public void processTuple() {

		T t = (T) channelPool.getTuple(id, inputChannel);
		if (t != null)
			sink.processNextTuple(t);

		if (backOff)
			if (t != null)
				backOffRegulator.reset();
			else
				backOffRegulator.backOff();

	}

}
