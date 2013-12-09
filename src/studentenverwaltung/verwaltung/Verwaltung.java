package studentenverwaltung.verwaltung;

import studentenverwaltung.util.*;
import java.io.*;

public final class Verwaltung {
	private static final String FIRST_NAME_FILE = "res/first_names";
	private static final String LAST_NAME_FILE = "res/last_names";

	private ArrayList<Student> studenten;
	private OutputStream out;

	private NameGenerator nameGen;

	public Verwaltung(OutputStream out){
		this(out, 0);
	}

	public Verwaltung(OutputStream out, int initRandomStudents){
		this.out = out;
		this.studenten = new ArrayList<Student>();

		for(int i = 0; i < initRandomStudents; ++i)
			addStudent(getRandomStudent());
	}

	public void addStudent(Student student){
		studenten.add(student);
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

		return new Student(name, geburtstag, studiengang);
	}
}
