package studentenverwaltung.verwaltung;

import studentenverwaltung.util.*;
import java.io.*;

public final class Verwaltung {
	private static final String FIRST_NAME_FILE = "resources/first_names";
	private static final String LAST_NAME_FILE = "resources/last_names";

	private ArrayList<Student> studenten;
	private PrintStream out;

	private NameGenerator nameGen;

	public Verwaltung(PrintStream out){
		this(out, 0);
	}

	public Verwaltung(PrintStream out, int initRandomStudents){
		this.out = out;
		this.studenten = new ArrayList<Student>();

		for(int i = 0; i < initRandomStudents; ++i){
			Student newStudent = getRandomStudent();
			if(newStudent != null)
				addStudent(newStudent);
			else
				return;
		}
	}

	public void addStudent(Student student){
		studenten.add(student);
	}

	public void removeStudent(int index){
		if(index >= 0 && index < studenten.getSize()){
			studenten.remove(index);
		}
	}

	public float getAverageAge(){
		float avg = 0;
		for(int i = 0; i < studenten.getSize(); ++i){
			avg += studenten.get(i).getAge();
		}
		return avg / (float)studenten.getSize();
	}

	public void printAll(){
		for(int i = 0; i < studenten.getSize(); ++i){
			out.println(studenten.get(i));
		}
	}

	public Student deleteByMatrikel(int matrikel){
		Student delete = null;
		for(int i = 0; i < studenten.getSize(); ++i){
			if(studenten.get(i).getMatrikelNummer() == matrikel){
				delete = studenten.get(i);
				studenten.remove(i);
				return delete;
			}
		}
		return delete;
	}

	public void printStudent(int index){
		if(index >= 0 && index < studenten.getSize()){
			out.println(studenten.get(index));
		}
	}

	public void printOldestStudent(){
		out.println(getOldestStudent(0, 0));
	}

	public void printYoungestStudent(){
		out.println(getYoungestStudent(0, 0));
	}

	public void printPruefungenHisto(){
		int[] werte = new int[33];
		int max = 0;
		for(int i = 0; i < studenten.getSize(); ++i){
			werte[studenten.get(i).getAnzahlPruefungenBestanden()]++;
		}
		for(int i = 0; i < werte.length; ++i){
			max = werte[i] > max ? werte[i] : max;
		}

		for(int i = 0; i < werte.length; ++i){
			out.format("%3d | ", i);
			for(int j = 0; j < werte[i]; ++j){
				out.print("#");
			}
			out.println();
		}

		out.print("     ");
		for(int i = 0; i < max + 3; ++i)
			out.print("-");
			
		out.println();
	}

	/***********************/

	// rekursiv den aeltesten studenten bestimmen
	private Student getOldestStudent(int max, int index){
		if(index == studenten.getSize()) return studenten.get(max);

		if(studenten.get(index).getBirthday().compareTo(studenten.get(max).getBirthday()) < 0){
			return getOldestStudent(index, index + 1);
		} else {
			return getOldestStudent(max, index + 1);
		}
	}

	// rekursiv den juengsten studenten bestimmen
	private Student getYoungestStudent(int min, int index){
		if(index == studenten.getSize()) return studenten.get(min);

		if(studenten.get(index).getBirthday().compareTo(studenten.get(min).getBirthday()) > 0){
			return getYoungestStudent(index, index + 1);
		} else {
			return getYoungestStudent(min, index + 1);
		}
	}

	private Student getRandomStudent(){
		if(nameGen == null)
			try {
				nameGen = new NameGenerator(new File(FIRST_NAME_FILE), new File(LAST_NAME_FILE));
			} catch(IOException e){
				e.printStackTrace();
				return null;
			}

		Name name = nameGen.getRandomName();
		Date geburtstag = Date.getRandomDate(1970, 1995);
		Studiengang studiengang = Studiengang.values()[(int)(Math.random() * Studiengang.values().length)];

		Student newStudent = new Student(name, geburtstag, studiengang);

		for(int i = 0; i < 32; ++i){
			newStudent.setPruefungBestanden(i, Math.random() < 0.5);
		}

		return newStudent;
	}
}
