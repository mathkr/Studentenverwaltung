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

package plotter;

public class Main {
	public static void main (String[] args) {
		if (args.length >= 3) {
			RPNExpression[] rpns =
			    new RPNExpression[args.length - 2];
			for (int i = 2; i < args.length; ++i) {
				rpns[i - 2] = new RPNExpression(args[i]);
			}

			new PlotterFrame(Double.parseDouble(args[0]),
			    Double.parseDouble(args[1]), rpns);
		} else {
			String usage = "USAGE: plot min max rpn-expressions...";
			System.out.println(usage);
		}
	}
}
