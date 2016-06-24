package temp;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map.Entry;
import java.util.TreeMap;

// TODO share interface for statistics class
// TODO check initialization of prevTS
// TODO make sure no one calls System
public class AverageStatistic {

	private long sum;
	private long count;
	private TreeMap<Long, Long> stats;

	String id;

	PrintWriter out;
	boolean immediateWrite;

	long prevTs;

	public AverageStatistic(String id, String outputFile, boolean immediateWrite) {
		this.id = id;
		this.sum = 0;
		this.count = 0;
		this.stats = new TreeMap<Long, Long>();
		this.immediateWrite = immediateWrite;

		FileWriter outFile;
		try {
			outFile = new FileWriter(outputFile, true);
			out = new PrintWriter(outFile);
		} catch (IOException e) {
			e.printStackTrace();
		}

		prevTs = System.currentTimeMillis() / 1000;

	}

	public void add(long v, long ts) {

		while (prevTs < ts) {
			if (immediateWrite) {
				out.println(prevTs * 1000 + ","
						+ (count != 0 ? sum / count : -1));
				out.flush();
			} else {
				this.stats.put(prevTs * 1000, (count != 0 ? sum / count : -1));
			}
			sum = 0;
			count = 0;
			prevTs++;
		}

		sum += v;
		count++;

	}

	public void writeStats(long ts) {

		while (prevTs < ts) {
			if (immediateWrite) {
				out.println(prevTs * 1000 + ","
						+ (count != 0 ? sum / count : -1));
				out.flush();
			} else {
				this.stats.put(prevTs * 1000, (count != 0 ? sum / count : -1));
			}
			sum = 0;
			count = 0;
			prevTs++;
		}

		if (!immediateWrite) {
			try {
				for (Entry<Long, Long> stat : stats.entrySet()) {

					long time = stat.getKey();
					long counter = stat.getValue();

					out.println(time + "," + counter);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		out.flush();
		out.close();

	}

}
