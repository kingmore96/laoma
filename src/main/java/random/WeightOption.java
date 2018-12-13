package random;

import java.util.Arrays;
import java.util.Random;

/**
 * 带权重的随机算法
 */
public class WeightOption {

    private Pair[] pairs;
    private double[] cumulateWeights;
    private Random random;

    public WeightOption(Pair[] pairs) {
        this.pairs = pairs;
        this.random = new Random();
        prepare();
    }

    //准备累积概率分布数组
    private void prepare(){
        int weights = 0;

        for (Pair pair:pairs) {
            weights += pair.getWeight();
        }

        cumulateWeights = new double[pairs.length];
        int sum = 0;
        for (int i = 0; i < cumulateWeights.length; i++) {
            sum += pairs[i].getWeight();
            cumulateWeights[i] = sum/(double)weights;
        }
    }

    public Object nextItem(){
        double d = random.nextDouble();

        int index = Arrays.binarySearch(cumulateWeights,d);
        if(index < 0){
            index = -index -1;
        }
        return pairs[index].getItem();
    }

    public static void main(String[] args) {
        Pair[] pairs = new Pair[3];
        pairs[0] = new Pair("1元",90);
        pairs[1] = new Pair("2元",9);
        pairs[2] = new Pair("7元",1);

        WeightOption weightOption = new WeightOption(pairs);
        for (int i = 0; i < 10; i++) {
            System.out.println(weightOption.nextItem());
        }
    }
}
