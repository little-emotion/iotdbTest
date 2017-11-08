package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.util.Date;
import java.util.Random;

import devSimulator.DevopsSimulator;
import devSimulator.DevopsSimulatorConfig;
import measurement.Point;

public class StatementGen {
	private static FileWriter out; 

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		//��ʼ����ʱ���Լ�host��Ŀ
		Date start = new Date();
		Date end = new Date(start.getTime() + 3546*1000);
		long HostCount = 4;
		
		//�ļ�
		out = new FileWriter(HostCount+"Host.txt");
		
		//��ʼ������
		Random rand;
		if(args.length != 0){
			long seed = Long.parseLong(args[0]);
			rand = new Random(seed);
		}
		else{
			rand = new Random();
		}
		
		//���ɲ���������
		DevopsSimulatorConfig cfg = new DevopsSimulatorConfig(start, end, HostCount);
		DevopsSimulator sim = cfg.ToSimulator(rand);
		Point point = new Point();
		
		while(!sim.Finished()){
			sim.Next(point);
			out.write(point.creatInsertStatement()+"\n");
			point.Reset();	
		}

		out.close();
		System.out.println("End��");
	}

}

