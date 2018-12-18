package collection;

import java.util.HashSet;
import java.util.Objects;

public class Spec {
    String size;
    String color;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Spec spec = (Spec) o;
        return Objects.equals(size, spec.size) &&
                Objects.equals(color, spec.color);
    }

    @Override
    public int hashCode() {

        return Objects.hash(size, color);
    }

    public Spec(String size, String color) {
        this.size = size;
        this.color = color;
    }

    public String getSize() {
        return size;
    }

    public String getColor() {
        return color;
    }

    public static void main(String[] args) {
        HashSet<Spec> specs = new HashSet<>();
        boolean add1 = specs.add(new Spec("老马", "red"));
        boolean add2 = specs.add(new Spec("老马", "red"));
        System.out.println(add1);
        System.out.println(add2);
        for (Spec spec :specs) {
            System.out.println(spec.getColor()+spec.getSize());
        }
    }
}
