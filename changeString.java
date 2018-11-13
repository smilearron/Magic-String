import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

//created by Huiyi Cao

public class changeString {
	public static void main(String args[]) {
		ArrayList<String> myList = new ArrayList<String>();//Create a new array list
		Scanner reader = new Scanner(System.in);// Reading from System.in
		String n = "";
		System.out.println("Enter strings: ");
		n = reader.nextLine() ;// Scans the next token of the input as a string
		if(!n.equals("finish")) {
			myList.add(n);
		}
		//once finished 
		reader.close();
		System.out.println("Input: "+myList);
		for(int i=0;i<myList.size();i++) {
			String res = myList.get(i);
			if(res.length()==0) {
				continue;
			}
			//Reverse the string if its length is a multiple of 4
			if(res.length()%4==0) {
				res=reverse(res);
			}
			//Truncate the string to 5 characters if its length is a multiple of 5
			if(res.length()%5==0) {
				res=truncate(res);
			}
			//Count the number of uppercase
			int count = 0;
			//Convert the string to all uppercase if it contains at least 3 uppercase characters in the first 5 characters
			if(res.length()>=5) {
				for(int j=0;j<5;j++) {
					if(Character.isUpperCase(res.charAt(j))) {
						count++;
					}
				}
				if(count>=3) {
					res=uppercase(res);
				}
			}
			//Get the index of the end of string
			int end = res.length()-1;
			myList.set(i, res);
			//If the string ends with a hyphen, remove it, and append the next string in the list to the current one.
			if(res.charAt(end)=='-') {
				res=res.substring(0,end);
				if(i!=myList.size()-1) {
					res = res + myList.get(i+1);
					myList.remove(i);
				}
				myList.set(i, res);
				i--;
			}
		}
		System.out.println("Output: "+myList);
		//Get median length of all strings
		Collections.sort(myList, new Comparator<String>() {
		    @Override
		    public int compare(String s1, String s2)
		    {
		        return  s1.length() - s2.length();
		    }
		});
		double middle=0;
		if (myList.size() % 2 == 1) {
		    middle = myList.get(myList.size()/2).length();
		} else {
		    middle = (myList.get(myList.size()/2).length()+ myList.get(myList.size()/2-1).length()) / 2.0;
		}
		System.out.println("median length of all strings: "+middle);
	}
	private static String reverse(String s) {
		char[] charArray = s.toCharArray();
		int i = 0;//Left pointer
		int j = charArray.length-1;//Right pointer
		while(i<j) {
			char temp = charArray[i];//Reverse the char array
			charArray[i] = charArray[j];
			charArray[j] = temp;
			i++;
			j--;
		}
		String res = String.valueOf(charArray);
		if(res.length()%5==0) {//The length of string is both a multiple of 4 and a multiple of 5;
			res = truncate(res);
		}
		return res;
	}
	private static String truncate(String s) {
		String res = s.substring(0, 5);
		return res;
	}
	private static String uppercase(String s) {
		String res = s.toUpperCase();
		return res;
	}
}
