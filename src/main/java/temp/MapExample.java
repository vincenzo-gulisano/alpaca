package temp;

import java.util.Random;

public class MapExample {

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

	class OutputTuple extends BaseTuple {
		public long d;

		public OutputTuple(long d) {
			super();
			this.d = d;
		}

		@Override
		public String toString() {
			return "[" + d + "]";
		}

	};

	public MapExample() {

		Query q = new SimpleQuery();

		q.registerSource("source", new Source<InputTuple>() {
			Random r = new Random();

			public InputTuple getNextTuple() {
				return new InputTuple(r.nextLong(), r.nextDouble(), r.nextInt());
			}
		});

		q.registerMap("map", new Map<InputTuple, OutputTuple>() {

			public OutputTuple map(InputTuple t) {
				return new OutputTuple(t.a * t.c);
			}
		}).fedBy("source");

		q.registerSink("sink", new Sink<InputTuple>() {
			public void processNextTuple(InputTuple t) {
				System.out.println(t);
			}
		}).fedBy("sink");

		q.deploy();

		Utils.sleepSeconds(60);

		q.terminate();

	}

	public static void main(String[] args) {

	}

}
