package random;

/**
 * 保存商品和权重
 */
public class Pair {

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
