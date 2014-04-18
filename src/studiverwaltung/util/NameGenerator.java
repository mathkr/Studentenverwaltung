package studiverwaltung.util;

import studiverwaltung.verwaltung.*;

import java.util.ArrayList;
import java.io.*;

public class NameGenerator {
	private ArrayList<String> firstNames;
	private ArrayList<String> lastNames;

	public NameGenerator (File firstNamesFile, File lastNamesFile) throws IOException {
		this.firstNames = new ArrayList<String>();
		this.lastNames = new ArrayList<String>();

		readFiles(firstNamesFile, lastNamesFile);
	}

	public Name getRandomName(){
		int firstNameIndex = (int)(Math.random() * firstNames.size());
		int lastNameIndex = (int)(Math.random() * lastNames.size());
		return new Name(firstNames.get(firstNameIndex), lastNames.get(lastNameIndex));
	}

	private void readFiles(File firstNamesFile, File lastNamesFile) throws IOException {
		BufferedReader in_firstNames = new BufferedReader(new FileReader(firstNamesFile));
		BufferedReader in_lastNames = new BufferedReader(new FileReader(lastNamesFile));

		String res = null;

		while((res = in_firstNames.readLine()) != null){
			firstNames.add(res);
		}

		while((res = in_lastNames.readLine()) != null){
			lastNames.add(res);
		}

		in_firstNames.close();
		in_lastNames.close();
	}
}
