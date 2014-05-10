/* Programming assignments for 'Programmieren I + II' at the
 * Hochschule Bremerhaven, GERMANY.
 *
 * Copyright (C) 2014 Matthis Krause
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, see <http://www.gnu.org/licenses/>.
 */

package util;

import java.util.ArrayList;
import java.io.*;

public class NameGenerator {
	private ArrayList<String> firstNames;
	private ArrayList<String> lastNames;

	public NameGenerator (File firstNamesFile, File lastNamesFile)
	    throws IOException
	{
		this.firstNames = new ArrayList<String>();
		this.lastNames = new ArrayList<String>();

		readFiles(firstNamesFile, lastNamesFile);
	}

	public String getRandomName(){
		int firstNameIndex = (int)(Math.random() * firstNames.size());
		int lastNameIndex = (int)(Math.random() * lastNames.size());
		return new String(firstNames.get(firstNameIndex) +
		    lastNames.get(lastNameIndex));
	}

	private void readFiles(File firstNamesFile, File lastNamesFile)
	    throws IOException
	{
		BufferedReader in_firstNames = new BufferedReader(
		    new FileReader(firstNamesFile));

		BufferedReader in_lastNames = new BufferedReader(
		    new FileReader(lastNamesFile));

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
