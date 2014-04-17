package verwaltung;

import util.*;

public class Student {
	private static int nextMatrikelnummer = 10000;

	private Name name;
	private Date geburtstag;
	private Studiengang studiengang;
	private int bestandenePruefungen;
	private int matrikelNummer;

	public Student(Name name, Date geburtstag, Studiengang studiengang){
		this.name = name;
		this.geburtstag = geburtstag;
		this.studiengang = studiengang;
		this.bestandenePruefungen = 0;
		this.matrikelNummer = nextMatrikelnummer++;
	}

	public Student(Student student){
		this.name = student.getName();
		this.geburtstag = student.getBirthday();
		this.studiengang = student.getStudiengang();
		this.bestandenePruefungen = student.getPruefungen();
		this.matrikelNummer = student.getMatrikelNummer();
	}

	public void setPruefungBestanden(int index, boolean bestanden) throws IndexOutOfBoundsException {
		if(index < 0 || index > 31)
			throw new IndexOutOfBoundsException("Index: " + index + ", size: 31");

		if(bestanden)
			bestandenePruefungen |= 1 << index;
		else
			bestandenePruefungen &= ~(1 << index);
	}

	public boolean getPruefungBestanden(int index) throws IndexOutOfBoundsException {
		if(index < 0 || index > 31)
			throw new IndexOutOfBoundsException("Index: " + index + ", size: 31");
		
		return ((bestandenePruefungen >> index) & 1) == 1;
	}

	public int getAnzahlPruefungenBestanden(){
		int res = 0;
		for(int i = 0; i < 32; ++i){
			if(getPruefungBestanden(i))
				++res;
		}

		return res;
	}

	public int getPruefungen(){
		return bestandenePruefungen;
	}

	public boolean hasPassedPruefungen(int pruefungen){
		return (bestandenePruefungen & pruefungen) == pruefungen;
	}

	public Name getName(){
		return name;
	}

	public Date getBirthday(){
		return geburtstag;
	}

	public int getAge(){
		return Date.getToday().getYearsSince(geburtstag);
	}

	public int getMatrikelNummer(){
		return matrikelNummer;
	}

	public Studiengang getStudiengang(){
		return studiengang;
	}

	@Override
	public String toString(){
		return getMatrikelNummer() + " : " + getName() + " : " + getBirthday() + " : " + getStudiengang().name + " : " + getAge() +
			" years : " + getAnzahlPruefungenBestanden() + "/32";
	}
}
