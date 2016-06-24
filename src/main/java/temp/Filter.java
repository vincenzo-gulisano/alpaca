package temp;

import java.util.List;

public interface Filter<T extends Tuple> {

	public List<String> filter(T t);

}
