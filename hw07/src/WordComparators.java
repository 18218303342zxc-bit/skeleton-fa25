import java.util.Comparator;
import java.util.List;

public class WordComparators {

    /** Returns a comparator that orders strings by the number of lowercase 'x' characters (ascending). */
    public static Comparator<String> getXComparator() {
        class xorder implements  Comparator<String> {
            @Override
            public int compare(String o1, String o2) {
                int counta = countx(o1);
                int countb = countx(o2);
                if(counta > countb) {
                    return 1;
                } else if (counta == countb) {
                    return 0;
                } else {
                    return -1;
                }
            }
            public int countx (String o) {
                int count = 0;
                for(int i = 0; i < o.length();i++){
                    if(o.charAt(i) == 'x'){
                        count++;
                    }
                }
                return count;
            }
        }
        return new xorder();

    }

    /** Returns a comparator that orders strings by the count of the given character (ascending). */
    public static Comparator<String> getCharComparator(char c) {
         class xorder implements  Comparator<String> {
             @Override
             public int compare(String o1, String o2) {
                 int counta = countx(o1);
                 int countb = countx(o2);
                 if(counta > countb) {
                     return 1;
                 } else if (counta == countb) {
                     return 0;
                 } else {
                     return -1;
                 }
             }
             public int countx (String o) {
                 int count = 0;
                 for(int i = 0; i < o.length();i++){
                     if(o.charAt(i) == c){
                         count++;
                     }
                 }
                 return count;
             }
         }
        return new xorder();
    }

    /** Returns a comparator that orders strings by the total count of the given characters (ascending). */
    public static Comparator<String> getCharListComparator(List<Character> chars) {
        class xorder implements Comparator<String> {
            @Override
            public int compare(String o1, String o2) {
                int counta = countx(o1);
                int countb = countx(o2);
                if (counta > countb) {
                    return 1;
                } else if (counta == countb) {
                    return 0;
                } else {
                    return -1;
                }
            }

            public int countx(String o) {
                int count = 0;
                for (int i = 0; i < o.length(); i++) {
                    if (chars.contains(o.charAt(i))) {
                        count++;
                    }
                }
                return count;
            }
        }
        return new xorder();
    }
}
