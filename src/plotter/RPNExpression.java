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
import java.util.List;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class RPNExpression {
	private class VariableToken implements Token {}

	private List<Token> expression;

	public RPNExpression(String expression) {
		this.expression = parseExpression(expression);
	}

	private List<Token> parseExpression(String exp) {
		List<Token> res = new ArrayList<Token>();
		StringTokenizer tokenizer = new StringTokenizer(exp, " ");

		while (tokenizer.hasMoreTokens()) {
			String token = tokenizer.nextToken();
			if (isNumeric(token)) {
				Double d = Double.parseDouble(token);
				NumberToken nt = new NumberToken(d);
				res.add(nt);
			} else if (isOperator(token)) {
				OperatorToken ot = new OperatorToken(token);
				res.add(ot);
			} else {
				VariableToken vt = new VariableToken();
				res.add(vt);
			}
		}
		return res;
	}

	public double solve(double x) {
		Stack<Double> stack = new Stack<Double>();

		for (int i = 0; i < expression.size(); ++i) {
			Token t = expression.get(i);
			if (t instanceof NumberToken) {
				stack.push(((NumberToken)t).doubleValue());
			} else if (t instanceof OperatorToken) {
				((OperatorToken)t).operate(stack);
			} else if (t instanceof VariableToken) {
				stack.push(x);
			}
		}

		return stack.pop();
	}

	private boolean isNumeric(String s) {
		try {
			Double.parseDouble(s);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}

	private boolean isOperator(String s) {
		switch (s) {
			case "+":
			case "-":
			case "*":
			case "/":
			case "sqrt":
			case "sin":
			case "cos":
			case "log":
			case "ln":
				return true;
			default:
				return false;
		}
	}
}
