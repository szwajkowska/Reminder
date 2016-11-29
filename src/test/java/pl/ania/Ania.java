package pl.ania;

public class Ania {

    private String name;
    private int age;

    public Ania(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Ania)) {
            return false;

        }
        Ania ania2 = (Ania) obj;

        return ania2.name.equals(this.name) && ania2.age == this.age;
    }
}
