import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Code {
    public static void main(String[] args) {

        HashMap hm = new HashMap();
        hm.put("Wallet", new Integer(700));
        hm.put("Belt", new Integer(600));
        hm.put("Backpack", new Integer(1200));

        Set keys = hm.keySet();
        Iterator i = keys.iterator();
        while (i.hasNext()) {
            System.out.println(i.next());
        }

        String s ="Jatin"+"."+"Gupta";
        System.out.println(s);

    }
}






