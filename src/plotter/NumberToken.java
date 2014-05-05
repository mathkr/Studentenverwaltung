package plotter;

public class NumberToken implements Token {
	private double d;
	public NumberToken(double d) {
		this.d = d;
	}

	public double doubleValue() {
		return d;
	}
}
