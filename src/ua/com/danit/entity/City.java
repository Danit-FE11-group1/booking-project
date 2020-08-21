package ua.com.danit.entity;

public enum City {
    TIRANA("Tirana", "ALB"),
    ANDORRA_LA_VELLA("Andorra la Vella", "AND"),
    VIENNA("Vienna", "AUT"),
    MINSK("Minsk", "BLR"),
    BRUSSELS("Brussels", "BEL"),
    SARAJEVO("Sarajevo", "BIH"),
    SOFIA("Sofia", "BGR"),
    ZAGREB("Zagreb", "HRV"),
    PRAGUE("Prague", "CZE"),
    COPENHAGEN("Copenhagen", "DNK"),
    TALLINN("Tallinn", "EST"),
    HELSINKI("Helsinki", "FIN"),
    PARIS("Paris", "FRA"),
    BERLIN("Berlin", "DEU"),
    ATHENS("Athens", "GRC"),
    BUDAPEST("Budapest", "HUN"),
    REYKJAVIK("Reykjavik", "ISL"),
    DUBLIN("Dublin", "IRL"),
    ROME("Rome", "ITA"),
    RIGA("Riga", "LVA"),
    VADUZ("Vaduz", "LIE"),
    VILNIUS("Vilnius", "LTU"),
    LUXEMBOURG("Luxembourg", "LUX"),
    VALLETTA("Valletta", "MLT"),
    CHISINAU("Chisinau", "MDA"),
    MONACO("Monaco", "MCO"),
    AMSTERDAM("Amsterdam", "NLD"),
    SKOPJE("Skopje", "MKD"),
    OSLO("Oslo", "NOR"),
    WARSAW("Warsaw", "POL"),
    LISBON("Lisbon", "PRT"),
    BUCHAREST("Bucharest", "ROU"),
    SAN_MARINO("San Marino", "SMR"),
    BELGRADE("Belgrade", "SRB"),
    BRATISLAVA("Bratislava", "SVK"),
    LJUBLJANA("Ljubljana", "SVN"),
    MADRID("Madrid", "ESP"),
    STOCKHOLM("Stockholm", "SWE"),
    BERN("Bern", "CHE"),
    LONDON("London", "GBR"),
    KYIV("Kyiv", "UKR"),
    VATICAN("Vatican", "VAT");

    private final String cityName;
    private final String country;

    private City(String cityName, String country) {
        this.cityName = cityName;
        this.country = country;
    }

    public String getCityName() {
        return this.cityName;
    }

    public String getCountry() {
        return this.country;
    }
}
