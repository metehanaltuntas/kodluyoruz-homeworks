package org.kodluyoruz.questions.question2;

public class Main {
    // Bir araba nesnesi oluşturup bu arabaya ait özellikleri kurucu methodlar sayesinde tanımlayabildiğimiz bir sınıf yazınız.
    public static void main(String[] args) {
        Car car = new Car(); // parametresiz araba nesnesi
        Car car1 = new Car("Audi", "A8", 2019, "3.0 Turbo"); // parametreli araba nesnesi

        System.out.println(car.toString());
        System.out.println(car1.toString());

    }
}
