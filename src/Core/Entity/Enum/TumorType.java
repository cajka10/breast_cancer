package Core.Entity.Enum;

public enum TumorType {
    MALIGNANT("M"),
    BENIGM("B"),
    UNKNOWN("U");

    private String b;
    TumorType(String b) {
        this.b = b;
    }

    public static TumorType getValueOf(String b){
        if (b.equals(""))
            return TumorType.UNKNOWN;
        for (TumorType type : values()){
            if (type.b.equals(b)){
                return type;
            }
        }
        return null;
    }
}
