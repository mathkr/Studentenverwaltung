package studiverwaltung.verwaltung;

import studiverwaltung.util.Date;

public class EhemaligerStudent extends Student {
	private Date exmatrikuliert;

	public EhemaligerStudent(Student student, Date exmatrikuliert){
		super(student);
		this.exmatrikuliert = exmatrikuliert;
	}
	
	public Date getExmatrikelDate(){
		return exmatrikuliert;
	}
}
