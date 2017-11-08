package devSimulator;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class DevopsSimulatorConfig {
	private Date start;
	private Date end;
	private long HostCount;
	
	public DevopsSimulatorConfig(Date start, Date end, long HostCount){
		this.start = start;
		this.end = end;
		this.HostCount = HostCount;
	}
	
	public DevopsSimulator ToSimulator(Random rand)  {
		Host[] hostInfos = new Host[(int) HostCount];
		for(int i = 0; i < HostCount; i++){
			hostInfos[i] = new Host(i, start, rand);
		}

		long epochs = (end.getTime() - start.getTime()) / Host.getEpochDuration();
		long maxPoints = epochs * (HostCount * Host.getNHostSims());
		DevopsSimulator dg = new DevopsSimulator(maxPoints, hostInfos,start, end);
		return dg;
	}
}
