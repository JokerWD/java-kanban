import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        try (Scanner scanner = new Scanner(System.in)) {
            UIHandler menu = new UIHandler();
            menu.ShowMenu(scanner);
        }
    }
}
