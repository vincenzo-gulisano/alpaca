package temp;

public class SingleThreadedFilterOperator<T extends Tuple> extends
		BaseSingleThreadedOperator {

	private Filter<T> filter;
	private String inputChannel;

	@SuppressWarnings("unchecked")
	public void processTuple() {

		if (limitOutputSize && channelPool.size(inputChannel) > outputSizeLimit)
			return;

		T t = (T) channelPool.getTuple(id, inputChannel);
		if (t != null)
			for (String destination : filter.filter(t))
				channelPool.addTuple(id, destination, t);

		if (backOff)
			if (t != null)
				backOffRegulator.reset();
			else
				backOffRegulator.backOff();

	}

}
