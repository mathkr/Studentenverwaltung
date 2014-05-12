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

public class TemplateTest {
	public static void main(String[] args) {
		StumaModel model = StumaModel.getStumaModel();
		Student student = model.getStudentById(1);

		Template t = new Template("resources/test.md",
				// "pandoc -f markdown -t pdf -o %%-output-%% %%-input-%%");
				"echo %%-input-%% > %%-output-%%");

		String[] labels = {"last_name", "first_name", "dob"};
		String[] values = new String[labels.length];
		for (int i = 0; i < values.length; ++i) {
			values[i] = student.get(labels[i]).toString();
		}

		try {
			t.applyTemplate(labels, values, "resource/test.pdf");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
