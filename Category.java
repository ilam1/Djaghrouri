public abstract class Category {
    public abstract String[] questions;
    public abstract String[] answers;

    public final boolean isName(String in, String[] alias) {
        if (isName(in))
            return true;
        for (String s : alias)
            if (in.equalsIgnoreCase(s))
                return true;
        return false;
    }

    public final boolean isName(String in) {
        return in.equalsIgnoreCase(getClass().getName());
    }


    @Override
    public String toString() {
        return getClass().getName();
    }
}
