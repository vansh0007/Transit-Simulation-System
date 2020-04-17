package sqa;

import java.util.Stack;

public class Stacking {

	public static void main(String[] args) {
		
		
		Stack<Integer> abc = new Stack<>();
		abc.add(1);
		abc.add(2);
 
		if(!abc.empty()) {
			
//			Iterator iterator = stack.iterator();
//			while(iterator.hasNext()){
			   
			for(int i=0;i<abc.size();i++) {
				
				
				int a = abc.get(i);
				a++;
				abc.set(i, a);
				
			}
			
		}
		
		System.out.println(abc);
		
		// TODO Auto-generated method stub

	}

}
