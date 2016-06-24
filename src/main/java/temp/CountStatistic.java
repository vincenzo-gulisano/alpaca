package temp;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map.Entry;
import java.util.TreeMap;

// TODO share interface for statistics class
// TODO check initialization of prevTS
// TODO make sure no one calls System
public class CountStatistic {

	private long count;
	private TreeMap<Long, Long> countStats;

	String id;

	PrintWriter out;
	boolean immediateWrite;

	long prevTs;

	public CountStatistic(String id, String outputFile, boolean immediateWrite) {
		this.id = id;
		this.count = 0;
		this.countStats = new TreeMap<Long, Long>();
		this.immediateWrite = immediateWrite;

		FileWriter outFile;
		try {
			outFile = new FileWriter(outputFile, true);
			out = new PrintWriter(outFile);
		} catch (IOException e) {
			e.printStackTrace();
		}

		prevTs = -1;

	}

	public void increase(long v, long ts) {

		while (prevTs < ts) {
			if (immediateWrite) {
				out.println(prevTs * 1000 + "," + count);
				out.flush();
			} else {
				this.countStats.put(prevTs * 1000, count);
			}
			count = 0;
			prevTs++;
		}
		count += v;
	}

	public void writeStats(long ts) {

		while (prevTs < ts) {
			if (immediateWrite) {
				out.println(prevTs * 1000 + "," + count);
				out.flush();
			} else {
				this.countStats.put(prevTs * 1000, count);
			}
			count = 0;
			prevTs++;
		}

		if (!immediateWrite) {
			try {
				for (Entry<Long, Long> stat : countStats.entrySet()) {

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
