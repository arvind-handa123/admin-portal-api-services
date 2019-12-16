package co.yabx.admin.portal.app.enums;

public enum Countries {
	Afghanistan, Albania, Algeria, Andorra, Angola, Antigua_and_Barbuda, Argentina, Armenia, Australia, Austria,
	Azerbaijan, The_Bahamas, Bahrain, Bangladesh, Barbados, Belarus, Belgium, Belize, Benin, Bhutan, Bolivia,
	Bosnia_and_Herzegovina, Botswana, Brazil, Brunei, Bulgaria, Burkina_Faso, Burundi, Cabo_Verde, Cambodia, Cameroon,
	Canada, Central_African_Republic, Chad, Chile, China, Colombia, Comoros, Democratic_Republic_of_the_Congo,
	Republic_of_the_Costa_Rica, Côte_d_Ivoire, Croatia, Cuba, Cyprus, Czech_Republic, Denmark, Djibouti, Dominica,
	Dominican_Republic, East_Timor_Timor_Leste, Ecuador, Egypt, El_Salvador, Equatorial_Guinea, Eritrea, Estonia,
	Eswatini, Ethiopia, Fiji, Finland, France, Gabon, The_Gambia, Georgia, Germany, Ghana, Greece, Grenada, Guatemala,
	Guinea, Guinea_Bissau, Guyana, Haiti, Honduras, Hungary, Iceland, India, Indonesia, Iran, Iraq, Ireland, Israel,
	Italy, Jamaica, Japan, Jordan, Kazakhstan, Kenya, Kiribati, North_Korea, South_Korea, Kosovo, Kuwait, Kyrgyzstan,
	Laos, Latvia, Lebanon, Lesotho, Liberia, Libya, Liechtenstein, Lithuania, Luxembourg, Madagascar, Malawi, Malaysia,
	Maldives, Mali, Malta, Marshall_Islands, Mauritania, Mauritius, Mexico, Micronesia, Moldova, Monaco, Mongolia,
	Montenegro, Morocco, Mozambique, Myanmar_Burma, Namibia, Nauru, Nepal, Netherlands, New_Zealand, Nicaragua, Niger,
	Nigeria, North_Macedonia, Norway, Oman, Pakistan, Palau, Panama, Papua_New_Guinea, Paraguay, Peru, Philippines,
	Poland, Portugal, Qatar, Romania, Russia, Rwanda, Saint_Kitts_and_Nevis, Saint_Lucia,
	Saint_Vincent_and_the_Grenadines, Samoa, San_Marino, Sao_Tome_and_Principe, Saudi_Arabia, Senegal, Serbia,
	Seychelles, Sierra_Leone, Singapore, Slovakia, Slovenia, Solomon_Islands, Somalia, South_Africa, Spain, Sri_Lanka,
	Sudan, South_Sudan, Suriname, Sweden, Switzerland, Syria, Taiwan, Tajikistan, Tanzania, Thailand, Togo, Tonga,
	Trinidad_and_Tobago, Tunisia, Turkey, Turkmenistan, Tuvalu, Uganda, Ukraine, United_Arab_Emirates, United_Kingdom,
	United_States, Uruguay, Uzbekistan, Vanuatu, Vatican_City, Venezuela, Vietnam, Yemen, Zambia, Zimbabwe;
	public static Countries getCountriies(String value) {
		if (value.equalsIgnoreCase("Antigua and Barbuda")) {
			return Countries.Antigua_and_Barbuda;
		} else if (value.equalsIgnoreCase("San Marino")) {
			return Countries.San_Marino;
		} else if (value.equalsIgnoreCase("Sao Tome and Principe")) {
			return Countries.Sao_Tome_and_Principe;
		} else if (value.equalsIgnoreCase("Saudi Arabia")) {
			return Countries.Saudi_Arabia;
		} else if (value.equalsIgnoreCase("Sierra Leone")) {
			return Countries.Sierra_Leone;
		} else if (value.equalsIgnoreCase("Solomon Islands")) {
			return Countries.Solomon_Islands;
		} else if (value.equalsIgnoreCase("South Africa")) {
			return Countries.South_Africa;
		} else if (value.equalsIgnoreCase("Sri Lanka")) {
			return Countries.Sri_Lanka;
		} else if (value.equalsIgnoreCase("South Sudan")) {
			return Countries.South_Sudan;
		} else if (value.equalsIgnoreCase("Trinidad and Tobago")) {
			return Countries.Trinidad_and_Tobago;
		} else if (value.equalsIgnoreCase("United Arab Emirates")) {
			return Countries.United_Arab_Emirates;
		} else if (value.equalsIgnoreCase("United Kingdom")) {
			return Countries.United_Kingdom;
		} else if (value.equalsIgnoreCase("United States")) {
			return Countries.United_States;
		} else if (value.equalsIgnoreCase("Vatican City")) {
			return Countries.Vatican_City;
		} else if (value.equalsIgnoreCase("The Bahamas")) {
			return Countries.The_Bahamas;
		} else if (value.equalsIgnoreCase("Bosnia and Herzegovina")) {
			return Countries.Bosnia_and_Herzegovina;
		} else if (value.equalsIgnoreCase("Burkina Faso")) {
			return Countries.Burkina_Faso;
		} else if (value.equalsIgnoreCase("Cabo Verde")) {
			return Countries.Cabo_Verde;
		} else if (value.equalsIgnoreCase("Central African Republic")) {
			return Countries.Central_African_Republic;
		} else if (value.equalsIgnoreCase("Democratic Republic of the Congo")) {
			return Countries.Democratic_Republic_of_the_Congo;
		} else if (value.equalsIgnoreCase("Republic of the Costa Rica")) {
			return Countries.Republic_of_the_Costa_Rica;
		} else if (value.equalsIgnoreCase("Côte d Ivoire")) {
			return Countries.Côte_d_Ivoire;
		} else if (value.equalsIgnoreCase("Dominican Republic")) {
			return Countries.Dominican_Republic;
		} else if (value.equalsIgnoreCase("East Timor Timor Leste")) {
			return Countries.East_Timor_Timor_Leste;
		} else if (value.equalsIgnoreCase("El Salvador")) {
			return Countries.El_Salvador;
		} else if (value.equalsIgnoreCase("Equatorial Guinea")) {
			return Countries.Equatorial_Guinea;
		} else if (value.equalsIgnoreCase("The Gambia")) {
			return Countries.The_Gambia;
		} else if (value.equalsIgnoreCase("Guinea Bissau")) {
			return Countries.Guinea_Bissau;
		} else if (value.equalsIgnoreCase("North Korea")) {
			return Countries.North_Korea;
		} else if (value.equalsIgnoreCase("South Korea")) {
			return Countries.South_Korea;
		} else if (value.equalsIgnoreCase("Marshall Islands")) {
			return Countries.Marshall_Islands;
		} else if (value.equalsIgnoreCase("Myanmar Burma")) {
			return Countries.Myanmar_Burma;
		} else if (value.equalsIgnoreCase("New Zealand")) {
			return Countries.New_Zealand;
		} else if (value.equalsIgnoreCase("North Macedonia")) {
			return Countries.North_Macedonia;
		} else if (value.equalsIgnoreCase("Papua New Guinea")) {
			return Countries.Papua_New_Guinea;
		} else if (value.equalsIgnoreCase("Saint Kitts and Nevis")) {
			return Countries.Saint_Kitts_and_Nevis;
		} else if (value.equalsIgnoreCase("Saint Lucia")) {
			return Countries.Saint_Lucia;
		} else if (value.equalsIgnoreCase("Saint Vincent and the Grenadines")) {
			return Countries.Saint_Vincent_and_the_Grenadines;
		} else {
			return Countries.valueOf(value);
		}
	}

	@Override
	public String toString() {
		if (super.toString().equals("Saint_Vincent_and_the_Grenadines")) {
			return "Saint Vincent and the Grenadines";
		} else if (super.toString().equals("United_States")) {
			return "United States";
		} else if (super.toString().equals("United_Kingdom")) {
			return "United Kingdom";
		} else if (super.toString().equals("United_Arab_Emirates")) {
			return "United Arab Emirates";
		} else if (super.toString().equals("Trinidad_and_Tobago")) {
			return "Trinidad and Tobago";
		} else if (super.toString().equals("South_Sudan")) {
			return "South Sudan";
		} else if (super.toString().equals("Sri_Lanka")) {
			return "Sri Lanka";
		} else if (super.toString().equals("South_Africa")) {
			return "South Africa";
		} else if (super.toString().equals("Solomon_Islands")) {
			return "Solomon Islands";
		} else if (super.toString().equals("Sierra_Leone")) {
			return "Sierra Leone";
		} else if (super.toString().equals("Saudi_Arabia")) {
			return "Saudi Arabia";
		} else if (super.toString().equals("Sao_Tome_and_Principe")) {
			return "Sao Tome and Principe";
		} else if (super.toString().equals("San_Marino")) {
			return "San Marino";
		} else if (super.toString().equals("Antigua_and_Barbuda")) {
			return "Antigua and Barbuda";
		} else if (super.toString().equals("El_Salvador")) {
			return "El Salvador";
		} else if (super.toString().equals("East_Timor_Timor_Leste")) {
			return "East Timor Timor Leste";
		} else if (super.toString().equals("Dominican_Republic")) {
			return "Dominican Republic";
		} else if (super.toString().equals("Côte_d_Ivoire")) {
			return "Côte d Ivoire";
		} else if (super.toString().equals("Republic_of_the_Costa_Rica")) {
			return "Republic of the Costa Rica";
		} else if (super.toString().equals("Democratic_Republic_of_the_Congo")) {
			return "Democratic Republic of the Congo";
		} else if (super.toString().equals("Central_African_Republic")) {
			return "Central African Republic";
		} else if (super.toString().equals("Cabo_Verde")) {
			return "Cabo Verde";
		} else if (super.toString().equals("Burkina_Faso")) {
			return "Burkina Faso";
		} else if (super.toString().equals("Bosnia_and_Herzegovina")) {
			return "Bosnia and Herzegovina";
		} else if (super.toString().equals("The_Bahamas")) {
			return "The Bahamas";
		} else if (super.toString().equals("Vatican_City")) {
			return "Vatican City";
		} else if (super.toString().equals("Marshall_Islands")) {
			return "Marshall Islands";
		} else if (super.toString().equals("South_Korea")) {
			return "South Korea";
		} else if (super.toString().equals("North_Korea")) {
			return "North Korea";
		} else if (super.toString().equals("Guinea_Bissau")) {
			return "Guinea Bissau";
		} else if (super.toString().equals("The_Gambia")) {
			return "The Gambia";
		} else if (super.toString().equals("Equatorial_Guinea")) {
			return "Equatorial Guinea";
		} else if (super.toString().equals("North Macedonia")) {
			return "North_Macedonia";
		} else if (super.toString().equals("New_Zealand")) {
			return "New Zealand";
		} else if (super.toString().equals("Myanmar_Burma")) {
			return "Myanmar Burma";
		} else if (super.toString().equals("Saint_Lucia")) {
			return "Saint Lucia";
		} else if (super.toString().equals("Saint_Kitts_and_Nevis")) {
			return "Saint Kitts and Nevis";
		} else if (super.toString().equals("Papua_New_Guinea")) {
			return "Papua New Guinea";
		} else {
			return super.toString();
		}
	}
}
