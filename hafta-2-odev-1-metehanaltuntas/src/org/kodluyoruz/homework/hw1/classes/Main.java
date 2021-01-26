package org.kodluyoruz.homework.hw1.classes;

public class Main {
    public static void main(String[] args) {
        Apple applePhone = new Apple("iPhone 12", "14557885949201", "1.1.02", 4096, 4.5, new TouchKeyboard());
        System.out.println(applePhone.toString());
        // telefon çalışmıyorsa bu işlemler gerçekleşmez. önce telefon açılmalı
        applePhone.startCall();
        applePhone.stopCall();
        applePhone.sendSms();
        applePhone.printKeyboardType();
        applePhone.videoCall();
        applePhone.shareScreen();
        // telefon açılıyor
        applePhone.turnOnPhone();
        applePhone.startCall();
        applePhone.videoCall();
        applePhone.stopCall();
        applePhone.sendSms();
        applePhone.printKeyboardType();
        applePhone.shareScreen();
        applePhone.videoCall();
        applePhone.turnOffPhone();

        System.out.println("----------------------------------------------------------------------------------------------" +
                "---------------------------------------------------------------------------------------------------------");

        Samsung samsungPhone = new Samsung("Samsung Galaxy Note 10", "5612346123", "123.21341", 8192, 6.5, new PhysicalKeyboard());
        System.out.println(samsungPhone.toString());
        // telefon çalışmıyorsa bu işlemler gerçekleşmez. önce telefon açılmalı
        samsungPhone.startCall();
        samsungPhone.stopCall();
        samsungPhone.sendSms();
        samsungPhone.printKeyboardType();
        samsungPhone.listenToMp3();
        samsungPhone.shareFile();
        // telefon açılıyor
        samsungPhone.turnOnPhone();
        samsungPhone.startCall();
        samsungPhone.stopCall();
        samsungPhone.sendSms();
        samsungPhone.printKeyboardType();
        samsungPhone.listenToMp3();
        samsungPhone.shareFile();
        samsungPhone.turnOffPhone();

    }
}
