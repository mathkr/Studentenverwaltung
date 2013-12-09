package studentenverwaltung;

import studentenverwaltung.util.*;
import studentenverwaltung.verwaltung.*;
import java.util.Scanner;
import java.io.PrintStream;

public final class Main {
	public static void main(String[] args){
		PrintStream out = System.out;
		Verwaltung verwaltung = new Verwaltung(out, 250);
		boolean exit = false;
		Scanner c = new Scanner(System.in);

		while(!exit){
			out.println("0: print all students");
			out.println("1: print the oldest student");
			out.println("2: print the youngest student");
			out.println("3: print histogram of how many students passed how many exams");
			out.println("4: print average age");
			out.println("5: delete student by matrikelnummer");
			out.println("6: reinitialize verwaltung");
			out.println("7: exit");

			out.print("Enter the number you'd like to choose: ");
			out.flush();
			String s = c.nextLine();
			out.println();

			switch(s){
				case "0":
					verwaltung.printAll();
					break;
				case "1":
					verwaltung.printOldestStudent();
					break;
				case "2":
					verwaltung.printYoungestStudent();
					break;
				case "3":
					verwaltung.printPruefungenHisto();
					break;
				case "4":
					out.println("Average age: " + verwaltung.getAverageAge());
					break;
				case "5":
					out.print("Enter matrikelnummer: ");
					out.flush();
					s = c.nextLine();
					Student delete = verwaltung.deleteByMatrikel(Integer.parseInt(s));
					if(delete == null){
						out.println("No student found with matrikel " + s);
					}
					break;
				case "6":
					verwaltung = new Verwaltung(out, 250);
					break;
				case "7":
					exit = true;
					out.println("Goodbye!");
					break;
				default:
					out.println("I don't know that command!");
					break;
			}
			
			out.println();
		}
	}
}
