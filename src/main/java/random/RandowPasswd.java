package random;

import java.util.Random;

/**
 * 随机密码生成器
 */
public class RandowPasswd {

    private static final String SPECIAL = "!@#$%^&*_=+-/";

    /**
     * 6位数字随机密码
     * @return
     */
    public static String randomPasswd_6(){
        char[] chars = new char[6];
        Random random = new Random();

        for (int i = 0; i < chars.length; i++) {
            chars[i] = (char) ('0'+random.nextInt(10));
        }
        return new String(chars);
    }

    /**
     * 8位长度，选择范围：大写、小写、数字，特殊字符
     * @return
     */
    public static String randomPasswd_8(){
        char[] chars = new char[8];
        Random random = new Random();
        for (int i = 0; i < chars.length; i++) {
            chars[i] = nextChar(random);
        }
        return new String(chars);
    }

    /**
     * 先随机选类型，再选中类型的基础上随机选字符
     * @param random
     * @return
     */
    private static char nextChar(Random random) {
        switch (random.nextInt(4)){
            case 0:
                return (char) ('a'+random.nextInt(26));
            case 1:
                return (char) ('A'+random.nextInt(26));
            case 2:
                return (char) ('0'+random.nextInt(10));
            default:
                    return RandowPasswd.SPECIAL.charAt(random.nextInt(RandowPasswd.SPECIAL.length()));
        }
    }

    private static char nextLowLetter(Random random){
        return (char) ('a'+random.nextInt(26));
    }

    private static char nextHighLetter(Random random){
        return (char) ('A'+random.nextInt(26));
    }

    private static char nextNumber(Random random){
        return (char) ('0'+random.nextInt(26));
    }

    private static char nextSpecial(Random random){
        return RandowPasswd.SPECIAL.charAt(random.nextInt(RandowPasswd.SPECIAL.length()));
    }

    //随机选位置
    private static int nextIndex(char[] chars,Random random){
        int index = random.nextInt(chars.length);
        while(chars[index] != '中'){
            index = random.nextInt(chars.length);
        }
        return index;
    }

    private static char[] initChars(char[] chars){
        for (int i = 0; i < chars.length; i++) {
            chars[i] = '中';
        }
        return chars;
    }

    /**
     * 8位密码，要求至少含一个大写一个小写一个数字，一个特殊字符
     * @return
     */
    public static String randomPasswd_8_1(){
        char[] chars = new char[8];
        Random random = new Random();

        initChars(chars);
        chars[nextIndex(chars,random)] = nextHighLetter(random);
        chars[nextIndex(chars,random)] = nextLowLetter(random);
        chars[nextIndex(chars,random)] = nextNumber(random);
        chars[nextIndex(chars,random)] = nextSpecial(random);

        for (int i = 0; i < chars.length; i++) {
            if(chars[i] == '中'){
                chars[i] = nextChar(random);
            }
        }
        return new String(chars);
    }

    public static void main(String[] args) {
        System.out.println(RandowPasswd.randomPasswd_6());
        System.out.println(RandowPasswd.randomPasswd_8());
        System.out.println(RandowPasswd.randomPasswd_8_1());
    }
}
