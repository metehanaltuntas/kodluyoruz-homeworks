package org.kodluyoruz.questions.question3;

// örnek singleton çalışması
public class SingletonClass {

    static SingletonClass instance = null; // her yerden erişiebilir olması için static bir referans
    public int number = 0; // 0 değerine sahip bir değişken

    // constructor artık dışarıdan doğrudan erişilemez
    private SingletonClass() {

    }

    // kontrol ekleyerek nesnenin daha önceden bir örneğinin oluşturulup oluşturulmadığına bakılıyor
    // daha önceden oluşturulmadıysa oluşturup return ediliyor
    // oluşturulduysa doğrudan return ediliyor
    static public SingletonClass getInstance() {
        if (instance == null) {
            instance = new SingletonClass();
            System.out.println("oluşturuldu"); // 1 defa oluşturulduğundan emin olmak için yazdırma yapılabilir
        }
        return instance;
    }
}
