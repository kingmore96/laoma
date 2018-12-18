package random;

import java.util.Objects;

/**
 * 保存商品和权重
 */
public class Pair {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pair pair = (Pair) o;
        return weight == pair.weight &&
                Objects.equals(item, pair.item);
    }

    @Override
    public int hashCode() {

        return Objects.hash(item, weight);
    }

    private Object item;
    private int weight;

    public Pair(Object item, int weight) {
        this.item = item;
        this.weight = weight;
    }

    public Object getItem() {
        return item;
    }

    public void setItem(Object item) {
        this.item = item;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
