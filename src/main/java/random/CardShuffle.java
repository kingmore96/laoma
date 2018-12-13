package random;

import java.util.Arrays;
import java.util.Random;

/**
 * 洗牌工具测试类
 */
public class CardShuffle {

    private static void swap(int[] arr,int i,int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * 洗牌,采用交换算法
     * @param arr
     */
    public static void shuffle(int[] arr){
        Random random = new Random();
        for (int i = arr.length; i > 1; i--) {
            swap(arr,i-1,random.nextInt(i));
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[13];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i;
        }
        System.out.println(Arrays.toString(arr));
        shuffle(arr);
        System.out.println(Arrays.toString(arr));
    }

}
