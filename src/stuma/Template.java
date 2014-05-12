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

package stuma;

import java.io.*;

public class Template {
	private String fileContents;
	private String templateFile;
	private String command;

	private static String PREFIX = "%%-";
	private static String SUFFIX = "-%%";

	Template(String file, String command) {
		this.templateFile = file;
		this.command = command;

		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
			String line;
			StringBuilder sb = new StringBuilder();

			while((line = reader.readLine()) != null) {
				sb.append(line);
				sb.append("\n");
			}

			fileContents = sb.toString();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				reader.close();
			} catch (Exception e) {}
		}
	}

	private String replace(String[] labels, String[] values, String string)
	{
		String res = new String(string);

		for (int i = 0; i < labels.length; ++i) {
			String regex = PREFIX + labels[i] + SUFFIX;
			String value = values[i];
			res = res.replaceAll(regex, value);
		}

		return res;
	}

	public void applyTemplate(String[] labels, String[] values, String file)
	    throws IOException
	{
		if (labels.length != values.length) {
			String e = "Wrong number of arguments\n";
			throw new IllegalArgumentException(e);
		}

		String appliedString = replace(labels, values, fileContents);

		File tmp = File.createTempFile("tmp",
		    (new File(templateFile)).getName());
		writeToFile(tmp, appliedString);

		// TODO: check portability (path separators?)
		callCommand(tmp.getAbsolutePath(), file);

		tmp.delete();
	}

	private void callCommand(String inputFile, String outputFile) {
		String[] labels = {"input", "output"};
		String[] values = {inputFile, outputFile};

		String command = replace(labels, values, this.command);

		try {
			ProcessBuilder pb = new ProcessBuilder("sh", "-c",
			    command);
			pb.inheritIO();
			Process p = pb.start();

			int exitValue = p.waitFor();

			if (exitValue != 0) {
				System.err.println("Command '" + command +
				    "' exited with status: " + exitValue);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void writeToFile(File file, String s) {
		Writer writer = null;
		try {
			writer = new BufferedWriter(new OutputStreamWriter(
			    new FileOutputStream(file), "utf-8"));
			writer.write(s);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				writer.close();
			} catch (Exception e) {}
		}
	}
}

/*

"$$-first_name-$$"

oder:

%%-first_name-%%

$$-first_name-$$ $$-last_name-$$
-> Manfred Müller


*/
