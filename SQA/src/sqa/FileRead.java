package sqa;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileRead {
	
	public static ArrayList<String> getWest() throws IOException {
		 ArrayList<String> timeline1 = new ArrayList();
		// TODO Auto-generated method stub
 
		FileInputStream fis = new FileInputStream("C:/Users/vansh/Desktop/New folder (11)/SQ1.txt");
 
		 		Scanner sc = new Scanner(fis);
		int count = 0;
		sc.nextLine();
		sc.nextLine();
		 

		while (sc.hasNextLine()) {
			String[] abc = sc.nextLine().split("-");
			String minutes = abc[1].replaceAll("\\s", "");

			String[] min = minutes.split(",");
			for (String k : min) {
				timeline1.add("A"+" "+(int) ((Double.parseDouble(abc[0]) * 60) + Integer.parseInt(k)));

			}
		}
		sc.close();
		
		return timeline1;
	}
}
