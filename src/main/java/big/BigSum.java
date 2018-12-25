package big;

/**
 * 大整数相加
 */
public class BigSum {

    /**
     * 大整数相加，两个字符串都不为空且长度大于0时才计算，其他情况抛出异常
     * @param num1 大整数1
     * @param num2 大整数2
     * @return 相加后的结果保存为字符串输出
     */
    public static String bigNumSum(String num1,String num2) throws Exception{
        if(check(num1) && check(num2)) {
            //把两个大整数用数组逆序存储，数组长度为较大整数位数
            int maxLength = num1.length() > num2.length() ? num1.length():num2.length();
            int[] arr1 = trans2IntArr(num1,maxLength);
            int[] arr2 = trans2IntArr(num2,maxLength);

            //循环遍历计算
            int[] result = new int[maxLength+1];
            result = calculate(arr1,arr2,result);

            //结果逆序，处理首位0，输出字符串
            String finalResult = reverse(result);
            return finalResult;
        }else{
            throw new IllegalArgumentException("nums cant't be null or length must bigger than 0");
        }
    }

    private static boolean check(String num){
        if(num != null && num.length() > 0){
            return true;
        }
        return false;
    }

    private static int[] trans2IntArr(String num1, int resultLength) {
        int[] arr = new int[resultLength];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = num1.charAt(num1.length()-1-i) - '0';
        }
        return arr;
    }

    private static int[] calculate(int[] numArr1, int[] numArr2, int[] result) {
        for (int i = 0; i < result.length; i++) {
            //进位
            int temp = result[i];
            //防止溢出
            if(i < numArr1.length){
                temp += numArr1[i];
            }
            //防止溢出
            if(i < numArr2.length){
                temp += numArr2[i];
            }

            //处理进位情况
            if(temp >= 10){
                temp = temp - 10;
                result[i+1] = 1;
            }
            result[i] = temp;
        }
        return result;
    }

    private static String reverse(int[] result) {
        StringBuilder sb = new StringBuilder();
        for (int i = result.length-1; i >= 0 ; i--) {
            if(i == result.length - 1){
                if(result[i] == 0){
                    continue;
                }
            }
            sb.append(result[i]);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
//        int[] ints1 = trans2IntArr("", 0);
//        int[] ints2 = trans2IntArr("123", 3);
//        for (int anInt : ints1) {
//            System.out.println(anInt);
//        }
//        System.out.println();
//
//
//        int[] result = calculate(ints1,ints2,new int[1]);
//        for (int anInt : result) {
//            System.out.println(anInt);
//        }
//
//        System.out.println();
//        String reverse = reverse(result);
//        System.out.println(reverse);
//        System.out.println("end");

        try {
            System.out.println(bigNumSum("1","1"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
