package sqa;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import sqa.Charger;

public class Working {
	static ArrayList<Integer> time_value;
	static Stack<String> porta;
	static Stack<String> portb;
	static int bus_battery_capacity;
	static ArrayList<Integer> fast_charger_schedule;
	static ArrayList<String> fast_charging;
	static ArrayList<String> intransit;
	static ArrayList<String> Schedule1;
	static HashMap<Integer, HashMap<String, Integer>> Bus_config;
	static ArrayList<String> slow_charger_schedule;
	static HashMap<String, Integer> battery_config;
	static HashMap<Integer, Integer> charger_config;
	static ArrayList<String> west;
	static ArrayList<String> east;
	static int Bus_averge;
	static int distance_cover;
	static int a;
	static int busCount;
	static int portA;
	static int A;
	static int B;
	static int Time;
	static int limit;
	static int portB;
	static int total_charger;
	static int total_bus;
	static int fast_charger;
	static int slow_charger;
	static Stack<String> buses;
	static ArrayList<String> logfile = new ArrayList<>();

	static int fast_battery;

	Working() throws IOException {
		porta = new Stack<>();
		portb = new Stack<>();
		bus_battery_capacity = 0;
		fast_charger_schedule = new ArrayList<>();
		fast_charging = new ArrayList<>();
		intransit = new ArrayList<>();
		Schedule1 = new ArrayList<>();
		Bus_config = new HashMap<Integer, HashMap<String, Integer>>();
		slow_charger_schedule = new ArrayList<>();
		battery_config = new HashMap<>();
		charger_config = new HashMap<>();

		west = new ArrayList<>();
		east = new ArrayList<>();
		Bus_averge = 1;
		distance_cover = 40;
		buses = new Stack<>();
		time_value = new ArrayList<>();

		a = 10;
		busCount = 0;
		portA = 0;
		A = 1;
		B = 1;
		Time = 0;
		limit = 1620;
		portB = 0;
		total_charger = 0;
		total_bus = 0;
		fast_charger = 0;
		slow_charger = 0;

		battery_config.put("battery1", 294);
		battery_config.put("battery2", 394);
		Bus_config.put(1, battery_config);

		battery_config = new HashMap<>();
		battery_config.put("battery1", 494);
		battery_config.put("battery2", 594);
		Bus_config.put(2, battery_config);

		slow_charger_schedule.add("1000,1060");
		slow_charger_schedule.add("1120,1180");
		slow_charger_schedule.add("1240,1300");
		slow_charger_schedule.add("1360,1420");

		charger_config.put(1, 350);
		charger_config.put(2, 450);

		west = FileRead.getWest();
		east = EastRead.getEast();
//		Schedule1.add("A 0");
//		Schedule1.add("A 45");
//		Schedule1.add("B 0");
//		Schedule1.add("B 60");

		for (int i = 0; i < west.size(); i++) {
			Schedule1.add(west.get(i));

		}

		for (int i = 0; i < east.size(); i++) {
			Schedule1.add(east.get(i));
		}

	}

	public ArrayList<Integer> Work(int bus_battery_capacity) throws IOException {
		int pass1 = Integer.parseInt(Charger.charger1_capacity.getSelectedItem().toString());
		int pass2 = Integer.parseInt(Charger.charger2_capacity.getSelectedItem().toString());

		if (pass1 < pass2) {
			fast_battery = pass2;

		}

		else if (pass1 > pass2) {

			fast_battery = pass1;

		}

		else {

			fast_battery = pass2;
		}
		total_bus = 0;
		Time = 0;

		for (int i = 997; i >=3; i--) {
			buses.add("Bus-" + i + " " + bus_battery_capacity);

		}

		porta.add("bus-1" + " " + bus_battery_capacity);

		portb.add("bus-2" + " " + bus_battery_capacity);

		for (int i = 0; i < 3000; i++) {
			Time = i;

			for (int it = 0; it < slow_charger_schedule.size(); it++) {
				String[] charge = (slow_charger_schedule.get(it).split(","));
				int start = Integer.parseInt(charge[0]);
				int finish = Integer.parseInt(charge[1]);
				if (Time >= start && Time < finish) {
					chargeingBuses(Time);

				}

			}

			for (int j = 0; j < Schedule1.size(); j++) {

				String[] abc = Schedule1.get(j).split(" ");
				String port = abc[0];
				int val = Integer.parseInt(abc[1]);

				if (Time == val) {

					if (port.equalsIgnoreCase("A")) {

						PA: if (A != 0 && !porta.isEmpty()) {
							A--;

							String[] peek = porta.peek().split(" ");
							if ((Integer.parseInt(peek[1]) - 40) >= 40) {

								String valu1 = porta.pop();
								String[] ab = valu1.split(" ");
								String busid = ab[0];
								int b_left = Integer.parseInt(ab[1]) - 40;
								int t = (int) (Time + 40);

								String port3 = "B";
								 

								String arrival = fastText(Time);

								String destination = fastText(t);
								// System.out.println("destination---"+destination);
								logfile.add("Arrival port" + " : " +"  Lionel-Groulx " + " | " + busid + " | " + "  "+ arrival
										+"      " + " - " + destination + " | " + "Destination Port" + " "
										+ "   MacDonald    "+ " | "+ "  battery Remaining  " + b_left+ " | ");

								intransit.add(port3 + " " + t + " " + busid + " " + b_left);

							}

							else {

								fastCharging(port, Time,peek[0]);

							}

						}

						else {
							busCount++;
							porta.add(buses.pop());
							String v = porta.pop();
							String[] ab1 = v.split(" ");
							String busid = ab1[0];
							int b_left1 = Integer.parseInt(ab1[1]) - 40;

							int t = (int) Time + 40;
							String port1 = "B";
							String arrival = fastText(Time);
							String destination = fastText(t);
							logfile.add("Arrival port" + " : " + "  Lionel-Groulx " + " | " + busid + " | " + "  "+ arrival
									+"      " + " - " + destination + " | " + "Destination Port" + " "
									+"   MacDonald    "+ " | " + "  battery Remaining  " + b_left1+ " | ");
							intransit.add(port1 + " " + t + " " + busid + " " + b_left1);
						}
					}

					if (port.equalsIgnoreCase("B")) {
						if (B != 0 && !portb.isEmpty()) {
							B--;

							String[] peek = portb.peek().split(" ");
							if ((Integer.parseInt(peek[1]) - 40) >= 40) {

								String valu1 = portb.pop();
								String[] ab = valu1.split(" ");
								String busid = ab[0];
								int b_left = Integer.parseInt(ab[1]) - 40;
								int t = Time + 40;
								String port3 = "A";
								String arrival = fastText(Time);
								String destination = fastText(t);
								logfile.add("Arrival port" + " : " + "   MacDonald    " + " | " + busid + " | " + "  "+ arrival
										+"      " + " - " + destination + " | " + "Destination Port" + " "
										+ "  Lionel-Groulx " + " | "+ "  battery Remaining  " + b_left+ " | ");

								intransit.add(port3 + " " + t + " " + busid + " " + b_left);

							}

							else {
								fastCharging(port, Time,peek[0]);

							}

						}

						else {
							busCount++;
							portb.add(buses.pop());
							String vy = portb.pop();
							String[] ax = vy.split(" ");
							String id = ax[0];
							int left1 = Integer.parseInt(ax[1]) - 40;
							int t = Time + 40;
							String port4 = "A";
							String arrival= fastText(Time);
							String destination= fastText(Time+40);
							logfile.add("Arrival port" + " : " + "   MacDonald    " + " | " + id + " | " + "  "+ arrival
									+"      " + " - " + destination + " | " + "Destination Port" + " "
									+ "  Lionel-Groulx "+ " | "+ "  battery Remaining  " + left1+ " | ");

							intransit.add(port4 + " " + t + " " + id + " " + left1);
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
							if (B > portB) {

								portB = B;
							}

						}

						if (port1.equalsIgnoreCase("A")) {

							A++;
							porta.add(id + " " + bleft);

							intransit.remove(j1);
							if (A > portA) {

								portA = A;
							}

						}

					}

					// add to A and b
				}

			}

		}

		total_bus = busCount + 2;
		System.out.println(portA);
		System.out.println(portB);

		total_charger = ((int) portA + (int) portB);
		fast_charger= maxA+maxB;
		System.out.println("total bus" + total_bus);
		slow_charger = total_bus - fast_charger;
		int total = (fast_charger * Integer.parseInt(Charger.charger2_price.getText().toString())
				+ slow_charger * Integer.parseInt(Charger.charger1_price.getText().toString()));
		System.out.println("The buses at port A: " + porta);
		System.out.println("The buses at port b:" + portb);
		System.out.println("The buses in transit: " + intransit);
		System.out.println("The number of buses :" + total_bus);

		System.out.println("The number of  Fast chargers: " + fast_charger);
		System.out.println("The number of  Slow chargers: " + slow_charger);
		System.out.println("The total price of chargers: ");
		System.out.println("The price of chargers: $ " + total);
		System.out.println("port A chargers"+maxA);
		System.out.println("port B chargers"+maxB);
		
		PrintWriter outputfile = new PrintWriter(
				"C:/Users/vansh/Desktop/Poject big data/sqa/Transit-Simulation-System/SQA/ScheduleData/output.txt");
//		 BufferedWriter out = new BufferedWriter(new FileWriter("C:/Users/vansh/Desktop/Poject big data/sqa/Transit-Simulation-System/SQA/ScheduleData/output.txt"));
		for (String st : logfile) {

			outputfile.println(st);
			outputfile.println("");

		}

		outputfile.close();

		ArrayList<Integer> ret = new ArrayList<>();
		ret.add(porta.size());
		ret.add(portb.size());
		ret.add(intransit.size());
		ret.add(total_bus);
		ret.add(fast_charger + slow_charger);
		ret.add(fast_charger);
		ret.add(slow_charger);
		ret.add(total);
		ret.add(total_bus);

		return ret;

	}

	public static String fastText(Integer a) {

		int minute = a;
		 

		int hour = minute / 60;
		minute %= 60;
		String p = "AM";
		if (hour >= 12) {
			hour %= 12;
			p = "PM";
		}
		if (hour == 0) {
			hour = 12;
		}
		String abc = (hour < 10 ? "0" + hour : hour) + ":" + (minute < 10 ? "0" + minute : minute) + " " + p;
		System.out.println(abc);
		return abc;

	}
	static int maxA=0;
	static int maxB=0;

	private static void fastCharging(String p, int time,String id) {

		time_value.add(time);
		 
		if (p.equalsIgnoreCase("A")) {

			fast_charging.add(porta.pop() + " A");
			  if(fast_charging.size()>maxA) {
				  maxA=fast_charging.size();
			  }
			if (!porta.isEmpty()) {

				String v = porta.pop();
				String[] ab1 = v.split(" ");
				String busid = ab1[0];
				int b_left1 = Integer.parseInt(ab1[1]) - 40;

				int t = (int) time + 40;
				String port1 = "B";
				String arrival =fastText(time);
				String destination =fastText(t);
				logfile.add("Arrival port" + " : " + "  Lionel-Groulx " + " | " + busid + " | " + "  "+ arrival
						+"      " + " - " + destination + " | " + "Destination Port" + " "
						+ "   MacDonald    "+ " | "+ "  battery Remaining  " + b_left1+ " | ");
				intransit.add(port1 + " " + t + " " + busid + " " + b_left1);
			}
		}

		if (p.equalsIgnoreCase("B")) {
			

			fast_charging.add(portb.pop() + " B");
			if(fast_charging.size()>maxB) {
				  maxB=fast_charging.size();
			  }
			if (!portb.isEmpty()) {

				String v = portb.pop();
				String[] ab1 = v.split(" ");
				String busid = ab1[0];
				int b_left1 = Integer.parseInt(ab1[1]) - 40;

				int t = (int) Time + 40;
				String port1 = "A";
				String arrival =fastText(time);
				String destination =fastText(t);
				logfile.add("Arrival port" + " : " + "   MacDonald    " + " | " + busid + " | " + "  "+ arrival
						+"      " + " - " + destination + " | " + "Destination Port" + " "
						+  "  Lionel-Groulx "+ " | "+ "  battery Remaining  " + b_left1+ " | ");

				intransit.add(port1 + " " + t + " " + busid + " " + b_left1);
			}

		}

		if (!fast_charging.isEmpty()) {

			for (int i = 0; i < fast_charging.size(); i++) {
				String[] right = fast_charging.get(i).split(" ");
				fast_charging.remove(i);
                String arrival =fastText(time);
                String destination= fastText(time+15);
				if (right[2].equalsIgnoreCase("A")) {
					

					porta.add(right[0] + " "
							+ (Integer.parseInt(right[1])  + ( fast_battery*15)/60));
					logfile.add("Arrival port" + " : " +  "  Lionel-Groulx " + " | " + id + " | " + "  "+ arrival
							+"      " + " - " + destination + " | " + "Destination Port" + " "
							+  "  Lionel-Groulx "+ " | "+ "  battery Remaining  " + (Integer.parseInt(right[1])   + " | "+ " ChargingTime  " +   arrival  + "    " + destination  +" | "+ Charger.charger1_manufacturer.getSelectedItem()+"-"+fast_battery+"KW"+"  |  "+" After Charging "+ (Integer.parseInt(right[1])+(fast_battery *15)/ 60  ) + " | " ));
				}

				else if (right[2].equalsIgnoreCase("B")) {

					portb.add(right[0] + " "
							+ (Integer.parseInt(right[1])  + (fast_battery*15) / 60));
					logfile.add("Arrival port" + " : " + "   MacDonald    " + " | " + id + " | " + "  "+ arrival
							+"      " + " - " + destination + " | " + "Destination Port" + " "
							+ "   MacDonald    "+ " | "+ "  battery Remaining  " + (Integer.parseInt(right[1])  + " | "+ " ChargingTime  " +   arrival  + "    " + destination  +" | "+ Charger.charger1_manufacturer.getSelectedItem()+"-"+fast_battery+"KW"+"  |  "+" After Charging "+ (Integer.parseInt(right[1])+(fast_battery *15)/ 60  ) + " | " ));
							
			}
			}
			}

		}

	

	// TODO Auto-generated method stub

	 public void calculate_Maximum() {
		 
		 
	 }
	

	private static void chargeingBuses(int time) {

		if (!porta.empty()) {

			for (int i = 0; i < porta.size(); i++) {
				String a = porta.get(i);
				String[] ab1 = a.split(" ");
				String busid = ab1[0];
				if (Integer.parseInt(ab1[1]) < bus_battery_capacity) {
					int b_left1 = Integer.parseInt(ab1[1]) + 1;
					porta.set(i, busid + " " + b_left1);

				}

			}

		}

		if (!portb.empty()) {

			for (int i = 0; i < portb.size(); i++) {
				String a = portb.get(i);
				String[] ab1 = a.split(" ");
				String busid = ab1[0];
				if (Integer.parseInt(ab1[1]) < bus_battery_capacity) {
					int b_left1 = Integer.parseInt(ab1[1]) + 1;
					portb.set(i, busid + " " + b_left1);

				}
			}

		}

		// TODO Auto-generated method stub

	}

}
