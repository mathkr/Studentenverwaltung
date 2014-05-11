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

import java.util.Stack;

public class OperatorToken implements Token {
	@FunctionalInterface
	private interface Operation {
		public void operate(Stack<Double> s);
	}

	private Operation operation;

	public OperatorToken(String operator) {
		switch (operator) {
			case "+":
				operation = (Stack<Double> s) -> {
					double a = s.pop();
					double b = s.pop();
					s.push(b + a);
				};
				break;

			case "-":
				operation = (Stack<Double> s) -> {
					double a = s.pop();
					double b = s.pop();
					s.push(b - a);
				};
				break;

			case "*":
				operation = (Stack<Double> s) -> {
					double a = s.pop();
					double b = s.pop();
					s.push(b * a);
				};
				break;

			case "/":
				operation = (Stack<Double> s) -> {
					double a = s.pop();
					double b = s.pop();
					s.push(b / a);
				};
				break;

			case "sqrt":
				operation = (Stack<Double> s) ->
				    s.push(Math.sqrt(s.pop()));
				break;

			case "sin":
				operation = (Stack<Double> s) ->
				    s.push(Math.sin(s.pop()));
				break;

			case "cos":
				operation = (Stack<Double> s) ->
				    s.push(Math.cos(s.pop()));
				break;

			case "log":
				operation = (Stack<Double> s) ->
				    s.push(Math.log10(s.pop()));
				break;

			case "ln":
				operation = (Stack<Double> s) ->
				    s.push(Math.log(s.pop()));
				break;
			default:
				System.err.println("Unknown operator: "
				    + operator);

				operation = (Stack<Double> s) -> {};
		}
	}

	public void operate(Stack<Double> stack) {
		operation.operate(stack);
	}
}
