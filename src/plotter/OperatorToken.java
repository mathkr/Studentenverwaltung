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
