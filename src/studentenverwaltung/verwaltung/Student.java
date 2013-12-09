package studentenverwaltung.verwaltung;

import studentenverwaltung.util.*;

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

	public Name getName(){
		return name;
	}

	public Date getDate(){
		return geburtstag;
	}

	public int getAge(){
		return Date.getToday().getYearsSince(geburtstag);
	}

	public Studiengang getStudiengang(){
		return studiengang;
	}
}
