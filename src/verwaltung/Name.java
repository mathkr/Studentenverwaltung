package verwaltung;

public class Name {
	private char[] firstName;
	private char[] lastName;

	public Name(char[] firstName, char[] lastName){
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public Name(String firstName, String lastName){
		this(firstName.toCharArray(), lastName.toCharArray());
	}

	public char[] getFullName(){
		char[] res = new char[firstName.length + 1 + lastName.length];

		for(int i = 0; i < firstName.length; ++i)
			res[i] = firstName[i];

		res[firstName.length] = ' ';

		for(int i = 0; i < lastName.length; ++i)
			res[firstName.length + 1 + i] = lastName[i];

		return res;
	}

	public char[] getFirstName(){
		return firstName.clone();
	}

	public char[] getLastName(){
		return lastName.clone();
	}

	@Override
	public String toString(){
		return new String(getFullName());
	}
}
