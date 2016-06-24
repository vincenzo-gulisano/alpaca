package temp;

import java.util.List;

public interface ChannelPool {

	public void registerChannel(List<String> sourceIDs,
			List<String> destinationIDs, String channelID);

	public void addTuple(String sourceID, String channelID, Tuple t);

	public long size(String channelID);
	
	public Tuple getTuple(String destinationID, String channelID);

}