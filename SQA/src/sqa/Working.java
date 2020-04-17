package sqa;

import java.io.IOException;
import java.util.*;
import sqa.Charger;

public class Working {

	static Stack<String> porta = new Stack<>();
	static Stack<String> portb = new Stack<>();
	static int bus_battery_capacity;
    
	static ArrayList<String> intransit = new ArrayList<>();
	static ArrayList<String> Schedule1 = new ArrayList<>();
    static HashMap<Integer, HashMap<String, Integer>> Bus_config = new HashMap<Integer, HashMap<String, Integer>>();
    static ArrayList<String> charger_schedule = new ArrayList<>();
	static HashMap<String, Integer> battery_config = new HashMap<>();
	static HashMap<Integer, Integer> charger_config = new HashMap<>();
    static ArrayList<Integer> ret = new ArrayList<>();
    static ArrayList<String> west = new ArrayList<>();
    static ArrayList<String> east = new ArrayList<>();
    static int Bus_averge = 1;
	static	int distance_cover = 40;
        
    static    int a = 10;
	static	int busCount = 0;
	static	int portA = 0;
	static	int A = 1;
	static	int B = 1;
	static	int Time = 0;
	static	int limit = 1620;
	static	int portB = 0;
	static	int total_charger = 0;
	static	int total_bus = 0;
    

	public static void main(String[] args) throws IOException {
        
		Scanner scan = new Scanner(System.in);
        
//		HashMap<Integer, Integer> charger_schedule = new HashMap<>();
		
		battery_config.put("battery1", 294);
		battery_config.put("battery2", 394);
		Bus_config.put(1, battery_config);

		battery_config = new HashMap<>();
		battery_config.put("battery1", 494);
		battery_config.put("battery2", 594);
		Bus_config.put(2, battery_config);
		
		
		charger_schedule.add("60,80");
		charger_schedule.add("180,220");
		charger_schedule.add("500,560");
        
        charger_config.put(1, 350);
		charger_config.put(2, 450);
        
         west = FileRead.getWest();
		 east = EastRead.getEast();
		for (int i = 0; i < west.size(); i++) {
			Schedule1.add(west.get(i));

		}

	for (int i = 0; i < east.size(); i++) {
		Schedule1.add(east.get(i));
	}
    
	 
		
		
			 
		 
		
		Charger.userInterface();
         
	}

public static ArrayList<Integer> Work(int bus_battery_capacity){
		
		Stack<String> buses = new Stack<>();
		for (int i = 3; i < 1000; i++) {
			buses.add("bus" + i + " " + bus_battery_capacity);

		}

		porta.add("bus1" + " " + bus_battery_capacity);
		portb.add("bus2" + " " + bus_battery_capacity);
		
		
		
		
		
		

		 

		 
		 
		 
		 
		 
		

		 
		 

		
   
		 
		 
		 
	 

	 
		for (int i = 0; i < 1700; i++) {
			Time = i;
			
			   
			
			 for (int it = 0; it < charger_schedule.size(); it++) {
				 String [] charge= (charger_schedule.get(it).split(","));
                 int start= Integer.parseInt(charge[0]);
                 int finish = Integer.parseInt(charge[1]);
                 if (Time>=start && Time<finish) {
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
							
							String[] peek= porta.peek().split(" ");
							if((Integer.parseInt(peek[1]) - 40)>=40) {
								
								String valu1 = porta.pop();
								String[] ab = valu1.split(" ");
								String busid = ab[0];
								int b_left = Integer.parseInt(ab[1]) - 40;
								int t = Time + 40;
								String	port3 = "B";
								 
										intransit.add(port3 + " " + t + " " + busid + " " + b_left);
								
							}
							
							else {
								busCount++;
							porta.add(buses.pop());
							String v = porta.pop();
							String[] ab1 = v.split(" ");
							String busid = ab1[0];
							int b_left1 = Integer.parseInt(ab1[1]) - 40;

							int t = (int) Time + 40;
						String	port1 = "B";

							intransit.add(port1 + " " + t + " " + busid + " " + b_left1);
					
							
							
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
						String	port1 = "B";

							intransit.add(port1 + " " + t + " " + busid + " " + b_left1);
						}
					}
						
					
 
					  if (port.equalsIgnoreCase("B")) {
						if (B != 0 && !portb.isEmpty()) {
							B--;
							
							
							
							String[] peek= portb.peek().split(" ");
							if((Integer.parseInt(peek[1]) - 40)>=40) {
								
								String valu1 = portb.pop();
								String[] ab = valu1.split(" ");
								String busid = ab[0];
								int b_left = Integer.parseInt(ab[1]) - 40;
								int t = Time + 40;
								String	port3 = "A";
								 
										intransit.add(port3 + " " + t + " " + busid + " " + b_left);
								
							}
							
							else {
								busCount++;
							portb.add(buses.pop());
							String v = portb.pop();
							String[] ab1 = v.split(" ");
							String busid = ab1[0];
							int b_left1 = Integer.parseInt(ab1[1]) - 40;

							int t = (int) Time + 40;
						String	port1 = "A";

							intransit.add(port1 + " " + t + " " + busid + " " + b_left1);
					
							
							
							
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
					String 	port4 = "A";

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
		total_charger = ((int) portA + (int) portB + 2);
		 
 
		System.out.println("The buses at port A: " + porta);
		System.out.println("The buses at port b:" + portb);
		System.out.println("The buses in transit: " + intransit);
		System.out.println("The number of buses :" + total_bus);
		System.out.println("The number of chargers: " + total_charger);
		System.out.println("The price of chargers: $ " + (total_charger * 10));
		 ret.add(porta.size());
         ret.add(portb.size());
         ret.add(intransit.size());
         ret.add( total_bus);
         ret.add(total_charger);
         
         
		return ret ;	
		}



	
	

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
			 
		}

		if (!portb.empty()) {

			for (int i = 0; i < portb.size(); i++) {
				String a = portb.get(i);
				String[] ab1 = a.split(" ");
				String busid = ab1[0];
				if (Integer.parseInt(ab1[1]) < bus_battery_capacity) {
					int b_left1 = Integer.parseInt(ab1[1])+1;
					portb.set(i, busid + " " + b_left1);
					

				}
				 	}
			 
		}
		 

		// TODO Auto-generated method stub

	}

}
