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

import java.awt.event.*;
import java.awt.*;

public class StumaController implements Subsystem {
	private static StumaController singleton;

	private StumaModel model;
	private StumaView view;

	private StumaController(StumaModel model, StumaView view) {
		this.model = model;
		this.view = view;

		view.frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				Main.exit(0);
			}
		});

	}

	public static StumaController getStumaController(StumaModel model,
	    StumaView view)
	{
		if (singleton == null) {
			singleton = new StumaController(model, view);
		}

		return singleton;
	}



	@Override
	public void close() {}
}
