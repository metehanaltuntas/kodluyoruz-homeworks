package org.kodluyoruz.questions.question4;

import java.util.Scanner;

public class Main {
    // Kullanıcıdan alınan ondalıklı bir sayının ondalık kısmına göre aşağı ve yukarı doğru yuvarlama işlemi yapacak bir program yazınız.
    //  - aşağı veya yukarı yuvalarlanacağını kullanıcıdan bir bilgi olarak alınız.

    public static void main(String[] args) {
        Main main = new Main();
        main.start();
    }

    void start() {
        int operation;
        boolean control = true;
        while (control) {
            printOperations();

            Scanner scanner = new Scanner(System.in);
            operation = scanner.nextInt();

            Main main = new Main();
            control = main.chooseOperation(operation);
        }
    }

    void printOperations() {
        System.out.println("Yapmak istediğiniz işlemi seçiniz:");
        System.out.println("1. Aşağı yuvarlama işlemi");
        System.out.println("2. Yukarı yuvarlama işlemi");
        System.out.println("3. Çıkış");
    }

    boolean chooseOperation(int operation) {
        Scanner scanner = new Scanner(System.in);
        Main main = new Main();
        double number;
        switch (operation) {
            case 1:
                System.out.println("Ondalıklı bir sayı giriniz: ");
                number = scanner.nextDouble();
                main.roundDownAndPrint(number);
                break;
            case 2:
                System.out.println("Ondalıklı bir sayı giriniz: ");
                number = scanner.nextDouble();
                main.roundUpAndPrint(number);
                break;
            case 3:
                return false;
            default:
                System.out.println("Tanımlanmayan bir işlem seçildi");
        }
        return true;
    }

    void roundDownAndPrint(double number) {
        System.out.println("Yuvarlanan sayı: " + number + ", aşağı yuvarlandıktan sonra: " + Math.floor(number));
    }

    void roundUpAndPrint(double number) {
        System.out.println("Yuvarlanan sayı: " + number + ", yukarı yuvarlandıktan sonra: " + Math.ceil(number));
    }
}
