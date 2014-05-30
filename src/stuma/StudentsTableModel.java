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

import javax.swing.table.*;
import javax.swing.event.*;
import java.util.List;

public class StudentsTableModel implements TableModel {
	private static String[] columnNames = {"ID", "Last Name", "First Name",
	    "Birthday"};

	private List<Student> students;

	StudentsTableModel(List<Student> students) {
		this.students = students;
	}

	@Override
	public void addTableModelListener(TableModelListener l) {
		// TODO: implement
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		return new Object().getClass();
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public String getColumnName(int columnIndex) {
		return columnNames[columnIndex];
	}

	@Override
	public int getRowCount() {
		return students.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO
		return null;
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

	@Override
	public void removeTableModelListener(TableModelListener l) {
		// TODO: implement
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		// TODO: implement
	}
}
