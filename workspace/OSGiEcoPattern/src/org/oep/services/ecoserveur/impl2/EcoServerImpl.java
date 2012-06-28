package org.oep.services.ecoserveur.impl2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.oep.services.ecoserveur.api.EcoServeur;

public class EcoServerImpl implements EcoServeur {
	protected double baseConsumption = 0;
	protected double lastConsumption = 0;
	
	private BufferedReader br;
	
	public EcoServerImpl() {
		try {
			br = new BufferedReader(new FileReader("conso_mesuree.csv"));
			
			lastConsumption = getLastValue();
			baseConsumption = lastConsumption;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	private Double getLastValue() throws IOException{
		String oldLine = Double.toString(lastConsumption), line = br.readLine();
		while(line!=null){
			oldLine=line;
			line = br.readLine();
		}
		
		String val = oldLine.split(",")[0];

		return Double.valueOf(val);
	}
	@Override
	public double getConsumption() {
		try {
			lastConsumption = getLastValue();
			return getLastValue() - baseConsumption;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return lastConsumption - baseConsumption;
	}

}
