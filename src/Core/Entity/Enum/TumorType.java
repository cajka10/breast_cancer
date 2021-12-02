package Core.Entity.Enum;

import java.util.HashMap;
import java.util.Map;

public enum TumorType {
    MALIGNANT("M"),
    BENIGM("B"),
    UNKNOWN("U");

    private String value;
    private static Map map = new HashMap<>();


    TumorType(String b) {
        this.value = b;
    }

    static{
        for (TumorType type : values()){
            map.put(type.value, type);
        }
    }

    public static TumorType getValueOf(String value){
        return (TumorType) map.get(value);
    }
}
