package studiverwaltung.verwaltung;

import studiverwaltung.util.*;
import studiverwaltung.util.structures.BinarySearchTree;

import java.io.*;
import java.util.ArrayList;

public final class Verwaltung {
	private static final String FIRST_NAME_FILE = "resources/first_names";
	private static final String LAST_NAME_FILE = "resources/last_names";

	private BinarySearchTree<Name, Student> studenten;
	private ArrayList<Student> cachedValues;
	private PrintStream out;

	public Verwaltung(PrintStream out) {
		this(out, 0);
	}

	public Verwaltung(PrintStream out, int initRandomStudents) {
		this.out = out;
		this.studenten = new BinarySearchTree<Name, Student>();

		NameGenerator nameGen = null;

		try {
			nameGen = new NameGenerator(new File(FIRST_NAME_FILE),
			    new File(LAST_NAME_FILE));
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}

		for (int i = 0; i < initRandomStudents; ++i) {
			addStudent(getRandomStudent(nameGen));
		}
	}

	public Student getStudent(int i) {
		if (cachedValues == null) cachedValues = studenten.getValues();

		return cachedValues.get(i);
	}

	public void addStudent(Student student){
		studenten.put(student.getName(), student);
		cachedValues = null;
	}

	public void removeStudent(Student student){
		/*
		 *  todo: implement delete
		 */
		cachedValues = null;
	}

	public float getAverageAge(){
		float avg = 0;
		for(int i = 0; i < studenten.size(); ++i){
			avg += getStudent(i).getAge();
		}
		return avg / (float)studenten.size();
	}

	public void printAll(){
		for(int i = 0; i < studenten.size(); ++i){
			out.println(getStudent(i));
		}
	}

	public Student deleteByMatrikel(int matrikel){
		Student delete = null;
		for(int i = 0; i < studenten.size(); ++i){
			if(getStudent(i).getMatrikelNummer() == matrikel){
				delete = getStudent(i);
				removeStudent(delete);
				return delete;
			}
		}
		return delete;
	}

	public void printStudent(int index){
		if(index >= 0 && index < studenten.size()){
			out.println(getStudent(index));
		}
	}

	private Student getRandomStudent(NameGenerator nameGen) {
		Name name = nameGen.getRandomName();
		Date geburtstag = Date.getRandomDate(1970, 1995);
		Studiengang studiengang = Studiengang.values()[
		    (int)(Math.random() * Studiengang.values().length)];

		Student newStudent = new Student(name, geburtstag, studiengang);

		for(int i = 0; i < 32; ++i){
			newStudent.setPruefungBestanden(i, Math.random() < 0.5);
		}

		return newStudent;
	}
}
