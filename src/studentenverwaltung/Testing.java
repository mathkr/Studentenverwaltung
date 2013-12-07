package studentenverwaltung;

import java.io.*;
import studentenverwaltung.util.*;
import studentenverwaltung.verwaltung.*;

public class Testing {
	public static void main(String[] args){
		//System.out.println("HashTableTest:" + hashTableTest());
		System.out.println("Datetest:" + dateTest());
	}

	public static boolean dateTest(){
		Date myBday = new Date(1991, 6, 2);
		System.out.println(myBday);
		System.out.println(Date.getToday());

		System.out.println(Date.getToday().getYearsSince(myBday));

		System.out.println(Date.getToday() + " compareTo " + myBday + " = " + Date.getToday().compareTo(myBday));
		
		return true;
	}

	public static boolean hashTableTest(){
		HashTable<Name, Name> hashTable = new HashTable<Name, Name>();
		try {
			NameGenerator nameGen = new NameGenerator(
					new File("resources/first_names"), 
					new File("resources/last_names"));

			Name myName = nameGen.getRandomName();
			hashTable.add(myName, myName);

			for(int i = 0; i < 4000; ++i){
				Name randomName = nameGen.getRandomName(); 
				hashTable.add(randomName, randomName);
			}

			Name maybeMyName = hashTable.get(myName);

			if(!maybeMyName.equals(myName)) return false;

			ArrayList<Name> names = hashTable.getValues();
			for(int i = 0; i < names.getSize(); ++i){
				System.out.println(names.get(i));
			}

			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}

		return false;
	}
}
