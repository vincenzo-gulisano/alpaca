package temp;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class FilterExample {

	class InputTuple extends BaseTuple {
		public long a;
		public double b;
		public int c;

		public InputTuple(long a, double b, int c) {
			super();
			this.a = a;
			this.b = b;
			this.c = c;
		}

		@Override
		public String toString() {
			return "[" + a + "," + b + "," + c + "]";
		}

	};

	public FilterExample() {

		Query q = new SimpleQuery();

		q.registerSource("source", new Source<InputTuple>() {
			Random r = new Random();

			public InputTuple getNextTuple() {
				return new InputTuple(r.nextLong(), r.nextDouble(), r.nextInt());
			}
		});

		q.registerFilter("filter", new Filter<InputTuple>() {
			public List<String> filter(InputTuple t) {
				List<String> result = new LinkedList<String>();
				if (t.a > 10 && t.b < 2.5)
					result.add("sink");
				return result;
			}
		}).fedBy("source");

		q.registerSink("sink", new Sink<InputTuple>() {
			public void processNextTuple(InputTuple t) {
				System.out.println(t);
			}
		}).fedBy("filter");

		q.deploy();

		Utils.sleepSeconds(60);

		q.terminate();

	}

	public static void main(String[] args) {

	}

}
