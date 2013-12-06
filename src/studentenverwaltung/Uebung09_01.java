import studentenverwaltung.util.*;
import studentenverwaltung.verwaltung.*;

public class Uebung09_01 {
	public static final int MONAT = 11;
	public static final int TAG = 4;
	public static final int YEAR = 2013;


	public static char[][] FIRST_NAMES = {
		{ 'M', 'a', 'n', 'n', 'i', 'e'},
		{ 'O', 't', 't', 'o'},
		{ 'F', 'r', 'e', 'd'},
		{ 'A', 'n', 'n', 'a'},
		{ 'A', 'm', 'e', 'l', 'i', 'e'},
		{ 'F', 'r', 'e', 'd', 'e', 'r', 'i', 'k', 'e'},
		{ 'H', 'a', 'n', 's'},
		{ 'K', 'a', 'r', 'l'},
		{ 'M', 'i', 'c', 'h', 'a', 'e', 'l'},
		{ 'O', 'b', 'i', ' ', 'W', 'a', 'n'}
	};

	public static char[][] LAST_NAMES = {
		{ 'M', 'ü', 'l', 'l', 'e', 'r'},
		{ 'F', 'a', 's', 's'},
		{ 'G', 'r', 'a', 'u'},
		{ 'A', 'm', 'i', 'r', 'y'},
		{ 'E', 'u', 'l', 'e', 'r'},
		{ 'W', 'o', 'l', 'l', 'n', 'y'},
		{ 'P', 'o', 't', 't', 'e', 'r'},
		{ 'B', 'r', 'o', 'e', 's', 'e', 'l'},
		{ 'V', 'i', 'e', 'l', 'h', 'a', 'b', 'e', 'r'},
		{ 'T', 'u', 't', 'o', 'r', 'i'}
	};

	public static char[][] PREFIXES = {
		{ 'V', 'o', 'n'},
		{ 'Z', 'u'},
		{ 'V', 'a', 'n', ' ','D','e'}
	};

	public static int initSize = 50;
	public static int nextMatrikel = 10000;
	public static int[][] studiGeburtstag = new int[initSize][]; // studiGeburtstag[i] = {Jahr, Monat, Tag}
	public static Integer[] studiMatrikelnummern = new Integer[initSize];
	public static Integer[] studiPruefungen = new Integer[initSize];
	public static char[][] studiName = new char[initSize][];
	public static Studiengang[] studiStudiengang = new Studiengang[initSize];

	public static void main(String[] args) {
		for (int i = 0; i < studiMatrikelnummern.length + 1; i++){
			createRandomStudent();
		}

		float avgAge = 0;
		int maxExamCount = 0;
		for (int i = 0; i < studiMatrikelnummern.length; i++){
			avgAge += getAge(i);
			maxExamCount = (getPruefungenCount(i) > maxExamCount) ? getPruefungenCount(i) : maxExamCount;
		}

		System.out.println("+++++++++++++++++++++++++++++++++++++++\nDie meisten Pruefungen haben bestanden:\n+++++++++++++++++++++++++++++++++++++++");
		for (int i = 0; i < studiMatrikelnummern.length; i++){
			if(getPruefungenCount(i) == maxExamCount)
				printStudent(i);
		}
	}

	public static int getPruefungenCount(int index){
		int res = 0;
		for(int i = 0; i < 32; i++)
			res += (studiPruefungen[index]>>i) & 1;

		return res;
	}

	public static void createRandomStudent(){
		addItem(studiStudiengang, Studiengang.values()[(int)(Math.random() * Studiengang.values().length)]);

		addItem(studiMatrikelnummern, nextMatrikel++);

		int[] bday = new int[3];
		bday[0] = 1964 + (30 - (int)(Math.pow(Math.random(), 3) * 30));
		bday[1] = 1 + (int)(Math.random() * 12);
		bday[2] = 1 + (int)(Math.random() * 28);

		addItem(studiGeburtstag, bday);

		char[][] names = new char[2 + (int)(Math.random() * 2)][];

		if(names.length == 3){
			names[names.length - 2] = PREFIXES[(int)(Math.random() * PREFIXES.length)];
		}

		names[names.length - 1] = LAST_NAMES[(int)(Math.random() * LAST_NAMES.length)];
		names[0] = FIRST_NAMES[(int)(Math.random() * FIRST_NAMES.length)];

		addItem(studiName, concat(names));

		int pruefungen = 0;
		for (int j = 0; j < 32; j++) {
			pruefungen |= ((int)(Math.random() * 2))<<j;
		}
		addItem(studiPruefungen, pruefungen);
	}

	public static <T> void addItem(T[] array, T item){
		for (int i = 0; i < array.length; i++) {
			if(array[i] == null){
				array[i] = item;
				return;
			}
		}

		System.err.println("Sorry, Speicher voll!");
	}

	public static char[] concat(char[][] strings){
		int len = (strings.length - 1);

		for (int i = 0; i < strings.length; i++){
			len += strings[i].length;
		}
		
		char[] retString = new char[len];
		int copyIndex = 0;

		for (int i = 0; i < strings.length; i++){
			for (int j = 0; j < strings[i].length; j++){
				retString[copyIndex++] = strings[i][j];
			}
			if (i < strings.length - 1) {
				retString[copyIndex++] = ' ';
			}
		}

		return retString;
	}

	public static void printStudent(int index) {
		for(int i = 0; i < studiName[index].length; i++)
			System.out.print(studiName[index][i]);

		System.out.println("\n | " + getPruefungenCount(index));
	}

	public static int getAge(int index) {
		return (YEAR - studiGeburtstag[index][0]) - (studiGeburtstag[index][1] >= MONAT && studiGeburtstag[index][2] >= TAG ? 0 : 1);
	}
}
