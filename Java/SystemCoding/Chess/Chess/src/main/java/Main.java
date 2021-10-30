import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        System.out.println("Driver of our appication.");
        Pattern p = Pattern.compile("[a-hA-H][1-8]");
        Scanner input = new Scanner(System.in);
        System.out.println(p.matcher("B7").matches());
        System.out.println(p.matcher("t2").matches());
        for (int i=0; i<10; i++) {
            System.out.println(p.matcher(input.next()).matches());
        }
    }
}
