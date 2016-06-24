package temp;

public interface Query {

	public Operator registerSource(String name, Source<? extends Tuple> source,
			int parallelismDegree);

	public Operator registerSource(String name, Source<? extends Tuple> source);

	public Operator registerSink(String name, Sink<? extends Tuple> sink,
			int parallelismDegree);

	public Operator registerSink(String name, Sink<? extends Tuple> sink);

	public Operator registerFilter(String name, Filter<? extends Tuple> filter,
			int parallelismDegree);

	public Operator registerFilter(String name, Filter<? extends Tuple> filter);

	public Operator registerMap(String name,
			Map<? extends Tuple, ? extends Tuple> map, int parallelismDegree);

	public Operator registerMap(String name,
			Map<? extends Tuple, ? extends Tuple> map);

	public void useSharedThreadPool();

	// TODO Will be called by deploy method.
	public void staticValidation();

	public void deploy();

	public void terminate();

}
