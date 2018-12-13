package random;

import java.text.DecimalFormat;
import java.util.Random;

/**
 * 红包，提供一个抢红包方法
 */
public class RedPacket {
    private double leftMoney;
    private int leftNum;
    private Random random;

    public RedPacket(double leftMoney, int leftNum) {
        this.leftMoney = leftMoney;
        this.leftNum = leftNum;
        this.random = new Random();
    }

    /**
     * 抢红包方法
     * @return
     */
    public synchronized String fight(){
        if(this.leftNum <= 0)
            throw new IllegalStateException("抢光了");

        DecimalFormat df = new DecimalFormat("#.##");

        if(this.leftNum == 1){
            return df.format(this.leftMoney);
        }

        double max = leftMoney/leftNum*2d;
        double money = random.nextDouble()*max;
        money = Math.max(0.01d,money);

        leftNum = leftNum - 1;
        leftMoney -= money;

        String money2 = df.format(money);
        return money2;
    }

    public static void main(String[] args) {
        RedPacket redPacket = new RedPacket(100,5);
        for (int i = 0; i < 5; i++) {
            System.out.println(redPacket.fight());
        }
    }
}
