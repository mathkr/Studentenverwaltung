package studiverwaltung.verwaltung;

public class Name implements Comparable<Name> {
	private String firstName;
	private String lastName;

	public Name(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public String getFullName() {
		StringBuilder sb = new StringBuilder();
		sb.append(firstName);
		sb.append(" ");
		sb.append(lastName);
		return sb.toString();
	}

	public String getFirstName() {
		return new String(firstName);
	}

	public String getLastName() {
		return new String(lastName);
	}

	@Override
	public int compareTo(Name other) {
		return lastName.compareTo(other.lastName);
	}

	@Override
	public String toString(){
		return getFullName();
	}
}
