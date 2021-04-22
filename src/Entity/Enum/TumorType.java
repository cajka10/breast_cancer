package Entity.Enum;

public enum TumorType {
    MALIGNANT('M'),
    BENIGM('B');

    private char b;
    TumorType(char b) {
        this.b = b;
    }

    private TumorType valueOf(char b){
        for (TumorType type : values()){
            if (type.b == b){
                return type;
            }
        }
        return null;
    }
}
