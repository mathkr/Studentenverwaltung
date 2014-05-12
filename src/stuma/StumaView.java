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

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class StumaView implements Subsystem {
	private static StumaView singleton;

	private JFrame frame;
	private StumaModel model;

	private StumaView(StumaModel model) {
		this.model = model;
		initialize();
	}

	public static StumaView getStumaView(StumaModel model) {
		if (singleton == null) {
			singleton = new StumaView(model);
			singleton.frame.setVisible(true);
		}

		return singleton;
	}

	@Override
	public void close() {
		frame.dispose();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));

		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				Main.exit(0);
			}
		});

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);

		StudentsPanel studentsPanel = new StudentsPanel(
		    new StudentsTableModel(model.getStudents()));
		tabbedPane.addTab("Students", null, studentsPanel, null);
	}
}
