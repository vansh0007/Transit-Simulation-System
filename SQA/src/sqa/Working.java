package sqa;

import java.io.IOException;
import java.util.*;

public class Working {

	static Stack<String> porta = new Stack<>();
	static Stack<String> portb = new Stack<>();
	static int bus_battery_capacity;

	public static void main(String[] args) throws IOException {
		Scanner scan = new Scanner(System.in);

		HashMap<Integer, HashMap<String, Integer>> Bus_config = new HashMap<Integer, HashMap<String, Integer>>();
		HashMap<String, Integer> battery_config = new HashMap<>();
		HashMap<Integer, Integer> charger_config = new HashMap<>();
		battery_config.put("battery1", 294);
		battery_config.put("battery2", 394);
		Bus_config.put(1, battery_config);

		battery_config = new HashMap<>();
		battery_config.put("battery1", 494);
		battery_config.put("battery2", 594);
		Bus_config.put(2, battery_config);

		charger_config.put(1, 350);
		charger_config.put(2, 450);

		int Bus_averge = 1;
		int distance_cover = 40;

		System.out.println("Enter the bustype 1 or 2");
		int bus_type = Integer.parseInt(scan.nextLine());

		System.out.println("Enter the no battery type battery1 or battery2");
		String battery_type = scan.nextLine();

		System.out.println("Enter the charger with 350 as 1 and 450 as 2");
		int charger_type = Integer.parseInt(scan.nextLine());
		System.out.println("Enter the price of chargers:");
		int charger_price = Integer.parseInt(scan.nextLine());

		float charging_time = (float) Bus_config.get(bus_type).get(battery_type) / charger_config.get(charger_type);
		bus_battery_capacity = Bus_config.get(bus_type).get(battery_type);
		System.out.println("The selected battery capacity is : " + bus_battery_capacity);
		int a = 10;
		int busCount = 0;
		int portA = 0;
		int A = 1;
		int B = 1;
		int Time = 0;
		int limit = 10000;
		boolean timetable = false;
		boolean transit = false;
		int portB = 0;
		int total_charger = 0;
		int total_bus = 0;

		ArrayList<String> Schedule1 = new ArrayList<>();
		ArrayList<String> west = FileRead.getWest();
		ArrayList<String> east = EastRead.getEast();
		for (int i = 0; i < west.size(); i++) {
			Schedule1.add(west.get(i));

		}

		for (int i = 0; i < east.size(); i++) {
			Schedule1.add(east.get(i));

		}

		Schedule1.add("A 0");
		Schedule1.add("A 0");
		Schedule1.add("A 60");
		Schedule1.add("A 70");
		Schedule1.add("A 60");
		Schedule1.add("B 80");
		Schedule1.add("A 110");
		Schedule1.add("A 110");
//		Schedule1.add("A 120");
//		Schedule1.add("A 120");
//		Schedule1.add("A 125");
//		Schedule1.add("A 130");
//		Schedule1.add("B 135");

		Stack<String> portb = new Stack<>();
		Stack<String> buses = new Stack<>();
		for (int i = 3; i < 1000; i++) {
			buses.add("bus" + i + " " + bus_battery_capacity);

		}

		porta.add("bus1" + " " + bus_battery_capacity);
		portb.add("bus2" + " " + bus_battery_capacity);

		ArrayList<String> intransit = new ArrayList<>();
		for (int i = 0; i < limit; i++) {
			Time = i;

			chargeingBuses(Time);

			for (int j = 0; j < Schedule1.size(); j++) {

				String[] abc = Schedule1.get(j).split(" ");
				String port = abc[0];
				int val = Integer.parseInt(abc[1]);

				if (Time == val) {

					if (port.equalsIgnoreCase("A")) {
						PA: if (A != 0 && !porta.isEmpty()) {
							A--;
							String valu1 = porta.pop();
							String[] ab = valu1.split(" ");
							String busid = ab[0];
							int b_left = Integer.parseInt(ab[1]) - 40;

							int t = Time + 40;
							port = "B";
						 
								intransit.add(port + " " + t + " " + busid + " " + b_left);
							

						}

						else {
							busCount++;
							porta.add(buses.pop());
							String v = porta.pop();
							String[] ab1 = v.split(" ");
							String busid = ab1[0];
							int b_left1 = Integer.parseInt(ab1[1]) - 40;

							int t = (int) Time + 40;
							port = "B";

							intransit.add(port + " " + t + " " + busid + " " + b_left1);
						}
					}

					if (port.equalsIgnoreCase("B")) {
						if (B != 0 && !portb.isEmpty()) {
							B--;

							String valu1 = portb.pop();
							String[] ab = valu1.split(" ");
							String id = ab[0];
							int b_left = Integer.parseInt(ab[1]) - 40;
							int t = Time + 40;
							port = "A";
							 
								intransit.add(port + " " + t + " " + id + " " + b_left);
							
						}

						else {
							busCount++;
							portb.add(buses.pop());
							String vy = portb.pop();
							String[] ax = vy.split(" ");
							String id = ax[0];
							int left1 = Integer.parseInt(ax[1]) - 40;
							int t = Time + 40;
							port = "A";

							intransit.add(port + " " + t + " " + id + " " + left1);
						}

					}

				}
			}

			if (!intransit.isEmpty()) {

				for (int j1 = 0; j1 < intransit.size(); j1++) {
					String[] abc1 = intransit.get(j1).split(" ");
					String port1 = abc1[0];
					int val1 = Integer.parseInt(abc1[1]);
					String id = abc1[2];
					int bleft = Integer.parseInt(abc1[3]);

					if (Time == val1) {

						if (port1.equalsIgnoreCase("B")) {

							B++;

							portb.add(id + " " + bleft);
							intransit.remove(j1);

						}

						if (B > portB) {

							portB = B;
						}
					}

					if (port1.equalsIgnoreCase("A")) {

						A++;
						porta.add(id + " " + bleft);

						intransit.remove(j1);

					}
					if (A > portA) {

						portA = A;
					}
				}

				// add to A and b
			}

			total_bus = busCount + 2;
			total_charger = ((int) portA + (int) portB + 2);

		}

		System.out.println("The buses at port A: " + porta);
		System.out.println("The buses at port b:" + portb);
		System.out.println("The buses in transit: " + intransit);
		System.out.println("The number of buses :" + total_bus);
		System.out.println("The number of chargers: " + total_charger);
		System.out.println("The price of chargers: $ " + (total_charger * charger_price));

	}

	static int k = 0;

	private static void chargeingBuses(int time) {

		if (!porta.empty()) {

			for (int i = 0; i < porta.size(); i++) {
				String a = porta.get(i);
				String[] ab1 = a.split(" ");
				String busid = ab1[0];
				if (Integer.parseInt(ab1[1]) < bus_battery_capacity) {
					int b_left1 = Integer.parseInt(ab1[1])+1;
					porta.set(i, busid + " " + b_left1);
				

				}
				 
			}
			System.out.println(porta);
		}

		if (!portb.empty()) {

			for (int i = 0; i < portb.size(); i++) {
				String a = portb.get(i);
				String[] ab1 = a.split(" ");
				String busid = ab1[0];
				if (Integer.parseInt(ab1[1]) < bus_battery_capacity) {
					int b_left1 = Integer.parseInt(ab1[1])+1;
					porta.set(i, busid + " " + b_left1);
					

				}
				 	}
			System.out.println(portb);	 
		}
		 

		// TODO Auto-generated method stub

	}

}
