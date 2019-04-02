package enumeration;

public enum FormationChangeGroup {
    CHANGE,
    NOT_CHANGE;

    public static FormationChangeGroup getChangeGroup(int position) {
        if (position < 1 || position > 10)
            return NOT_CHANGE;
        else
            return CHANGE;
    }
}
