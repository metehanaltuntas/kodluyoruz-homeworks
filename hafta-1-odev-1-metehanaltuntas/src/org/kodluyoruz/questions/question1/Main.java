package org.kodluyoruz.questions.question1;

/**
 * 0 ile 2000 arasında 3,5,7 ve 53’e bölünebilen sayıların ağırlıklı ortalamasını bulan bir uygulama yazınız.
 */
public class Main {
    public static void main(String[] args) {
        float sumOfDivisible = 0;
        int divisibleNumber = 0;
        for (int i = 0; i <= 2000; i++) {
            if ((i % 3 == 0) && (i % 5 == 0) && (i % 7 == 0) && (i % 53 == 0)) {
                sumOfDivisible += i;
                divisibleNumber++;
                System.out.println(i + " sayısı 3,5,7 ve 53’e bölünebilmektedir.");
            }
        }
        if (divisibleNumber != 0) {
            System.out.println("0 ile 2000 arasında 3,5,7 ve 53' e bölünebilen sayıların aritmetik ortalaması: " + sumOfDivisible + "/" + divisibleNumber + " işleminden " + sumOfDivisible / divisibleNumber + " bulunur");
        } else {
            System.out.println("0 ile 2000 arasında 3,5,7 ve 53' e bolunebilen bir sayı yoktur.");
        }
    }
}
