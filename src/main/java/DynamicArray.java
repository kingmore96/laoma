public class DynamicArray<T> {

    public void addAll(DynamicArray<? extends T> array){
        System.out.println("haha");
    }

    public static void main(String[] args) {
        DynamicArray<Integer> array1 = new DynamicArray<>();
        DynamicArray<Number> array2 = new DynamicArray<>();
        array2.addAll(array1);
    }
}
