package org.kodluyoruz.questions.question2;

public class Car {
    private String brand;
    private String model;
    private int generation;
    private String engine;

    // parametresiz constructor
    public Car() {
    }
    // parametreli constructor (farklı ihtiyaçlara göre parametreler azaltılabilir. bu constructor' da hepsi kullanıldı)

    public Car(String brand, String model, int generation, String engine) {
        this.brand = brand;
        this.model = model;
        this.generation = generation;
        this.engine = engine;
    }

    // private attribute' lara okuma ve yazma işlemleri için
    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getGeneration() {
        return generation;
    }

    public void setGeneration(int generation) {
        this.generation = generation;
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    @Override
    public String toString() {
        return "Car{" +
                "brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", generation=" + generation +
                ", engine='" + engine + '\'' +
                '}';
    }
}
