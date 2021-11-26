package Core.Entity.Enum;

public enum UserRole {
    ADMIN(1),
    DOCTOR(2);

    private int i;

    UserRole(int i) {
        this.i = i;
    }

    public static UserRole valueOf(int value) {
        for (UserRole e : values()) {
            if (e.i == value) {
                return e;
            }
        }
        return null;
    }

    public int getI() {
        return i;
    }
}
