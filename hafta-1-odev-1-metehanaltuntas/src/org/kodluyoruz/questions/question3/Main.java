package org.kodluyoruz.questions.question3;

public class Main {
    // kurucu methodlarda private erişim belirteci ile method oluşturabilir miyiz? OIuşturabiliyorsak buna neden ihtiyacımız var.
    public static void main(String[] args) {
        /**
         * Evet oluşturulabilir.
         * Bir uygulama geliştirirken tanımladığımız değişkenler ve nesneler için bellekte bir alan ayrılır.
         * Bu alanı optimize etmek de aslında temel anlamda geliştiricinin görevidir.
         * Her ne kadar java Garbage Collector ile bizim için temizlik yapsa da belleği gereksiz kullanmaktan kaçınmalıyız.
         * Bu amaç için de bellekte yer tutacak olan ne ise bunun oluşturulması ve kullanılması aşamaları optimize edilmedilir.
         * Bazen kullanılacak nesneleri her bir işlem için ayrı ayrı oluşturmaya gerek yoktur.
         * İşte bu gibi durumlarda nesneleri Singleton Design Pattern' i kullanarak oluşturmak avantajlı olacaktır.
         * Singleton ile oluşturulan nesneler bir kere oluşturulur ve her gereken durumda bu oluşturulan örnek üzerinden bu nesneye ulaşılabilir.
         *
         * Özetle amaç: oluşturulan nesneye global bir erişim noktası tanımlamaktır. Bu sayede sistem çalıştığı sürece ikinci bir örnek oluşturulmaz
         * ve nesnenin bir defa oluşturularak sürekli kullanılması sağlanır.
         */

        // aşağıdaki işlemlerde görüleceği üzere nesne örneği public static metod üzerinden alınmıştır ve her iki örnek de aynı nesneyi temsil etmektedir.
        SingletonClass singletonClassReference1 = SingletonClass.getInstance();
        SingletonClass singletonClassReference2 = SingletonClass.getInstance();

        singletonClassReference1.number += 10;

        System.out.println("singletonClassReference1.number değeri = " + singletonClassReference1.number);
        System.out.println("singletonClassReference2.number değeri = " + singletonClassReference2.number);

    }
}
