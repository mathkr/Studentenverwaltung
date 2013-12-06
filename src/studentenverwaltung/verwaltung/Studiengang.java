package studentenverwaltung.verwaltung;

public enum Studiengang {
	Anlagenbetriebstechnik(1, "Anlagenbetriebstechnik"),
	Gebaeudeenergietechnik(1, "Gebäudeenergietechnik"),
	Lebensmitteltechnologie(1, "Lebensmitteltechnologie/Lebensmittelwirtschaft"),
	MaritimeTechnologie(1, "Maritime Technologie"),
	Medizintechnik(1, "Medizintechnik"),
	NachhaltigeEnergie(1, "Nachhaltige Energie- und Umwelttechnologien"),
	Produktionstechnologie(1, "Produktionstechnologie"),
	Schiffsbetriebstechnik(1, "Schiffsbetriebstechnik"),
	Biotechnologie(1, "Biotechnologie"),
	EmbeddedSystems(1, "Embedded Systems Design"),
	Infrastrukturwirtschaft(1, "Infrastruktur und Public-Private-Partnership"),
	MedizintechnikMaster(1, "Master Medizintechnik"),
	ProcessEngineering(1, "Process Engineering and Energy Technology"),
	Windenergietechnik(1, "Windenergietechnik"),
	Betriebswirtschaftslehre(2, "Betriebswirtschaftslehre"),
	CruiseTourismManagement(2, "Cruise Tourism Management"),
	DigitaleMedienProduktion(2, "Digitale Medienproduktion"),
	Informatik(2, "Informatik"),
	Transportwesen(2, "Transportwesen/Logistik"),
	Wirtschaftsinformatik(2, "Wirtschaftsinformatik"),
	AnwendungsorientierteInfo(2, "Anwendungsorientierte Informatik"),
	IntegratedSafety(2, "Integrated Safety and Security Management"),
	LogisticsEngineering(2, "Logistics Engineering and Management"),
	ManagementImMittelstand(2, "Management im Mittelstand");

	public int fachbereich;
	public String name;

	private Studiengang(int fachbereich, String name) {
		this.fachbereich = fachbereich;
		this.name = name;
	}
}
