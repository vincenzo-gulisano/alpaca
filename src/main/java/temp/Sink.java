package temp;

public interface Sink<T extends Tuple> {

	public void processNextTuple(T t);

}
