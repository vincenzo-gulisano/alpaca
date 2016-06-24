package temp;

public class SingleThreadedSourceOperator<T extends Tuple> extends
		BaseSingleThreadedOperator {

	private Source<T> source;
	private String destinationID;

	public void processTuple() {

		if (limitOutputSize
				&& channelPool.size(destinationID) > outputSizeLimit) {
			if (backOff)
				backOffRegulator.backOff();
			return;
		} else {
			if (backOff)
				backOffRegulator.reset();
		}

		// TODO probably good to check if tuple is null?
		channelPool.addTuple(id, destinationID, source.getNextTuple());

	}

}
