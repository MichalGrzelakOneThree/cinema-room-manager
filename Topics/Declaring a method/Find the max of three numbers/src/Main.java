import java.util.Scanner;

public class Main {

    public static int getNumberOfMaxParam(int a, int b, int c) {
        int minNumber = a > b ? a : b > c ? b : c;
        if (minNumber == a) {
            return 1;
        } else if (minNumber == b) {
            return 2;
        } else {
            return 3;
        }

    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        final int a = scanner.nextInt();
        final int b = scanner.nextInt();
        final int c = scanner.nextInt();

        System.out.println(getNumberOfMaxParam(a, b, c));
    }
}