package temp;

public interface Map<T1 extends Tuple, T2 extends Tuple> {

	public T2 map(T1 t);

}
