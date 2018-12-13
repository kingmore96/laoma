import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Test {
    public static void main(String[] args) throws UnsupportedEncodingException, ParseException {
        float f = (float) (1.2*Math.pow(2,2));
        int i = Float.floatToIntBits(f);
        System.out.println(Integer.toBinaryString(i));

        String s = "ÀÏÂí";
        System.out.println(new String(s.getBytes("windows-1252"),"GB18030"));

        int a = 10;
        switch (a){
            case 10:
                System.out.println(1);
                break;
            case 12:
                System.out.println(2);
                break;
        }

        Size size = Size.MEDIUM;
        System.out.println(size.toString());
        System.out.println(size.name());
        System.out.println(size.equals(Size.MEDIUM));
        System.out.println(size==Size.LARGE);

        System.out.println(size.ordinal());
        System.out.println(size.compareTo(Size.SMALL));

        switch (size){
            case SMALL:
                System.out.println("small");
                break;
            case MEDIUM:
                System.out.println("medium");
                break;
            case LARGE:
                System.out.println("large");
                break;
        }

        // System.out.println(Size.valueOf("small"));
        Size[] values = Size.values();
        for (Size size1: values) {
            System.out.println(size1);
        }

        Size_1 size_1 = Size_1.SMALL;
        System.out.println(size_1.getAbbr());

        //System.out.println(Size_1.fromAbbr("s").getTitle());

        try{
            String text = args[0];
            System.out.println(Integer.parseInt(text));
            System.out.println("haha");
        }catch (NumberFormatException e){
            System.out.println("请输入正确的数字");
        }
        System.out.println("end");

        System.out.println(Integer.signum(0));
        System.out.println(Integer.toBinaryString(Integer.lowestOneBit(20)));
        System.out.println(Integer.bitCount(20));

        System.out.println(Character.isValidCodePoint(0x1FFFFF));
        System.out.println(Character.isBmpCodePoint(0x1FFFF));
        System.out.println(Character.charCount(0xFFFF));

        System.out.println(Character.toLowerCase('A'));

        String text1 = "a";
        byte[] bytes = text1.getBytes("UTF-16");
        System.out.println(bytes.length);

        System.out.println(Character.isIdeographic('马'));

        System.out.println(Charset.defaultCharset().name());

        String s_t = new String(new byte[]{(byte)0xC2,(byte)0xED},"GBK");
        char[] chars = s_t.toCharArray();
        for (char c: chars){
            System.out.println(Integer.toHexString((int)c));
        }
        System.out.println(s_t);


        String[] arr = {"hello","world", "Break","abc"};
        //Arrays.sort(arr,String.CASE_INSENSITIVE_ORDER);
        //Arrays.sort(arr, Collections.reverseOrder(String.CASE_INSENSITIVE_ORDER));
        String[] strings = Arrays.copyOf(arr, 5);
        System.out.println(Arrays.toString(strings));

        String[] a1 = null;
        System.out.println(Arrays.toString(a1));


        System.out.println(TimeZone.getDefault().getDisplayName());

        System.out.println(TimeZone.getTimeZone("US/Eastern").getDisplayName());

        System.out.println(TimeZone.getTimeZone("GMT+08:00").getID());

        System.out.println(Locale.getDefault());

        Calendar calendar = Calendar.getInstance();
        System.out.println("year: "+calendar.get(Calendar.YEAR));
        System.out.println("month: "+calendar.get(Calendar.MONTH));
        System.out.println("day: "+calendar.get(Calendar.DAY_OF_MONTH));
        System.out.println("hour: "+calendar.get(Calendar.HOUR_OF_DAY));
        System.out.println("minute: "+calendar.get(Calendar.MINUTE));
        System.out.println("second: "+calendar.get(Calendar.SECOND));
        System.out.println("millisecond: " +calendar.get(Calendar.MILLISECOND));
        System.out.println("day_of_week: " + calendar.get(Calendar.DAY_OF_WEEK));


        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = dateFormat.format(new Date());
        System.out.println(format);

        String date1 = "2018-12-13 15:14:23";
        SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date parse = dateFormat1.parse(date1);
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(parse);
        System.out.println("year: "+calendar1.get(Calendar.YEAR));

    }

    /**
     * 内部枚举
     */
    private enum Size{
        SMALL,MEDIUM,LARGE
    }

    /**
     * 内部枚举
     */
    private enum Size_1{
        SMALL("s","小号");

        private String abbr;
        private String title;

        private Size_1(String abbr, String title) {
            this.abbr = abbr;
            this.title = title;
        }

        public String getAbbr() {
            return abbr;
        }

        public String getTitle() {
            return title;
        }
    }
}
