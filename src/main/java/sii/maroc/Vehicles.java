package sii.maroc;

import java.util.Arrays;
import java.util.HashMap;

public class Vehicles {
	private HashMap<String, Integer> consommations;
	private HashMap<String, Integer> vehicleDoorsByType;
	
	public Vehicles(String consommations) {
		String[] firstSplit = consommations.split(",");
		this.consommations = new HashMap<>();
		for (String unCase : firstSplit) {
			String[] secondSplit = unCase.split(":");
			this.consommations.put(secondSplit[0], Integer.valueOf(secondSplit[1].substring(0,secondSplit[1].length()-1)));
		}
		vehicleDoorsByType = new HashMap<>();
		vehicleDoorsByType.put("CAR", 4);
		vehicleDoorsByType.put("TRUCK", 2);
		vehicleDoorsByType.put("MOTORCYCLE", 0);
	}
	
	private String calculConsommation(String motorType, String distance) {
		String[] givenDistance = distance.split(" ");
		int intDistance = Integer.valueOf(givenDistance[0]);
		float consommation = (intDistance * consommations.get(motorType)) / 100f;
		return String.format("%.2f L", consommation);
	}
	
	private String printDoors(int numberOfDoors, String[] closedDoors) {
		String report = "  _\n";
		for (int i = 1; i <= numberOfDoors; i++) {
			if (i%2 != 0) report += " ";
			
			if (Arrays.binarySearch(closedDoors, String.valueOf(i)) >= 0) report += "|";
			else if (i%2 != 0) report += "/";
			else report += "\\";
			
			if (i == 1) report += " ";
			else if (i == 2) report += "\n";
			else if (i == numberOfDoors - 1) report += "_";
		}
		return report;
	}
	
	public String move(String vehicleType, String motorType, String closedDoors, String distance) {
		String report = "DOORS ";
		String[] doorsTable = closedDoors.split(" ");
		int vehicleDoors = vehicleDoorsByType.get(vehicleType);
		if (doorsTable.length >= vehicleDoors) {
			report +=  "OK, MOVING. The "+ vehicleType +" will consume " + calculConsommation(motorType,distance); 
		}
		else {
			report +=  "KO, BLOCKED \n" + printDoors(vehicleDoors, doorsTable);
		}		
		return report;
	}
}
