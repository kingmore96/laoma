package collection;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class Clothes {
    private Size size;
    private String id;

    public Clothes(String id, Size size) {
        this.size = size;
        this.id = id;
    }

    public Size getSize() {
        return size;
    }

    public String getId() {
        return id;
    }

    public static Map<Size,Integer> countBySize(List<Clothes> clothesList){
        Map<Size,Integer> map = new EnumMap<>(Size.class);
        for (Clothes clothes : clothesList) {
            Size size = clothes.getSize();
            Integer count = map.get(size);
            if(count == null){
                map.put(size,1);
            }else{
                map.put(size,count+1);
            }
        }
        return map;
    }

    public static void main(String[] args) {
        List<Clothes> clothes = Arrays.asList(new Clothes[]{
                new Clothes("C001",Size.SMALL),
                new Clothes("C002", Size.LARGE),
                new Clothes("C003", Size.LARGE),
                new Clothes("C004", Size.MEDIUM),
                new Clothes("C005", Size.SMALL),
                new Clothes("C006", Size.SMALL),
        });

        System.out.println(countBySize(clothes));
    }
}
