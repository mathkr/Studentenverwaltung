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
	//@FunctionalInterface
	private interface Operation {
		public void operate(Stack<Double> s);
	}

	private Operation operation;

	public OperatorToken(String operator) {
		switch (operator) {
			case "+":
				operation = new Operation() {
					@Override
					public void operate(Stack<Double> s) {
						double a = s.pop();
						double b = s.pop();
						s.push(b + a);
					}
				};
				break;
			case "-":
				operation = new Operation() {
					@Override
					public void operate(Stack<Double> s) {
						double a = s.pop();
						double b = s.pop();
						s.push(b - a);
					}
				};
				break;
			case "*":
				operation = new Operation() {
					@Override
					public void operate(Stack<Double> s) {
						double a = s.pop();
						double b = s.pop();
						s.push(b * a);
					}
				};
				break;
			case "/":
				operation = new Operation() {
					@Override
					public void operate(Stack<Double> s) {
						double a = s.pop();
						double b = s.pop();
						s.push(b / a);
					}
				};
				break;
			case "sqrt":
				operation = new Operation() {
					@Override
					public void operate(Stack<Double> s) {
						double a = s.pop();
						s.push(Math.sqrt(a));
					}
				};
				break;
			case "sin":
				operation = new Operation() {
					@Override
					public void operate(Stack<Double> s) {
						double a = s.pop();
						s.push(Math.sin(a));
					}
				};
				break;
			case "cos":
				operation = new Operation() {
					@Override
					public void operate(Stack<Double> s) {
						double a = s.pop();
						s.push(Math.cos(a));
					}
				};
				break;
			case "log":
				operation = new Operation() {
					@Override
					public void operate(Stack<Double> s) {
						double a = s.pop();
						s.push(Math.log10(a));
					}
				};
				break;
			case "ln":
				operation = new Operation() {
					@Override
					public void operate(Stack<Double> s) {
						double a = s.pop();
						s.push(Math.log(a));
					}
				};
				break;
			default:
				System.err.println("Unknown operator: "
				    + operator);

				operation = new Operation() {
					@Override
					public void operate(Stack<Double> s) {
					}
				};
		}
	}

	public void operate(Stack<Double> stack) {
		operation.operate(stack);
	}
}
