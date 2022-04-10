package pogodynka;

import java.util.Scanner;

public class menu {
    public static void main(String[] args) {
        boolean isWorking = true;

        System.out.println("witamy w aplikacji!");
        Scanner scanner = new Scanner(System.in);
        while (isWorking){
            System.out.println("prosze wybrac opcje z menu\n" +
                    "1.\n" +
                    "2.\n" +
                    "3.\n" +
                    "4.exit");
            String option = scanner.nextLine();
            switch (option){
                case "1":
                    break;
                case "2":
                    break;
                case "3":
                    break;
                case "exit":
                    isWorking = false;
                    break;
                default:
                    System.out.println("prosze wybrac dostepna opcje");
            }
        }
    }
}
