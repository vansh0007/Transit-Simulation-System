package sqa;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class EastRead {
	
	 
		// TODO Auto-generated method stub
		public static ArrayList<String> getEast() throws IOException {
			 ArrayList<String> timeline2 = new ArrayList();
			// TODO Auto-generated method stub
			FileInputStream fis = new FileInputStream("SQA/ScheduleData/SQ2.txt");
			Scanner sc = new Scanner(fis);
			int count = 0;
			sc.nextLine();
			sc.nextLine();
		

			while (sc.hasNextLine()) {
				String[] abc = sc.nextLine().split("-");
				String minutes = abc[1].replaceAll("\\s", "");

				String[] min = minutes.split(",");
				for (String k : min) {
					timeline2.add("B"+" "+(int) ((Double.parseDouble(abc[0]) * 60) + Integer.parseInt(k)));

				}
			}
			sc.close();
			return (timeline2);
	}

}
