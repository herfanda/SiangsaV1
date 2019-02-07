package id.yeha.siangsav1.helper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class SimpleValidate {

    public static boolean isCollectionNotEmpty(Collection c) {
        return (c != null && !c.isEmpty());
    }

    public static Collection isCollectionNotEmptyOrDefault(Collection c, Collection ac) {
        if (c != null && !c.isEmpty()) {
            return c;
        } else {
            return ac;
        }
    }

    public static <T> ArrayList<T> isCollectionNotEmptyOrDefault(ArrayList<T> t, ArrayList<T> s) {
        if (t != null && !t.isEmpty()) {
            return t;
        } else {
            return s;
        }
    }

    public static <T> List<T> isCollectionNotEmptyOrDefault(List<T> t, List<T> s) {
        if (t != null && !t.isEmpty()) {
            return t;
        } else {
            return s;
        }
    }

    public static <T> T whenListNotEmptyAtPosition(List<T> c, final int position) {
        if (c != null && !c.isEmpty()) {
            if ((position <= -1) || (position >= c.size())) {
                return null;
            }
            return c.get(position);

        }
        return null;
    }

    public static <T> T whenArrayListNotEmptyAtPosition(ArrayList<T> c, final int position) {
        if (c != null && !c.isEmpty()) {
            if ((position <= -1) || (position >= c.size())) {
                return null;
            }
            return c.get(position);

        }
        return null;
    }

    public static boolean isCollectionNotEmpty(Map m) { return (m != null && !m.isEmpty());}

    public static boolean checkNotNullOrEmpty(String string) {
        return (string != null && !string.contains("null") && !string.isEmpty());
    }


}
