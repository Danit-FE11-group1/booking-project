package ua.com.danit.entity;

public enum Boat {
    A319_132_100("A319-132/100", 132),
    B777_300_ER("B777-300 ER", 402),
    A330_300("A330-300", 440),
    A330_200("A330-200", 300),
    B737_MAX_9("B737 MAX 9", 178);


    private final String name;
    private final int seats;

    private Boat(String name, int seats) {
        this.name = name;
        this.seats = seats;
    }

    public String getName() {
        return name;
    }

    public int getSeats() {
        return seats;
    }
}
