package org.kodluyoruz.homework.hw1.classes;

import org.kodluyoruz.homework.hw1.interfaces.IFileShare;
import org.kodluyoruz.homework.hw1.interfaces.IKeyboard;
import org.kodluyoruz.homework.hw1.interfaces.IListenToMp3;

public class Samsung extends MobilePhone implements IFileShare, IListenToMp3 {

    public Samsung(String modelName, String imeiNumber, String versionNumber, int memoryCapacity, double screenSize, IKeyboard iKeyboard) {
        super(modelName, imeiNumber, versionNumber, memoryCapacity, screenSize, iKeyboard);
    }

    @Override
    void turnOnPhone() {
        if (super.isTurnOn()) {
            System.out.println("You can't turn on the phone again. Because phone is already on");
        } else {
            super.setTurnOn(true);
            System.out.println(this.getClass().getSimpleName() + " turning on...");
        }
    }

    @Override
    void turnOffPhone() {
        if (super.isTurnOn()) {
            super.setTurnOn(false);
            System.out.println(this.getClass().getSimpleName() + " turning off...");
        } else {
            System.out.println(this.getClass().getSimpleName() + " is not on. You can't turn off the phone");
        }
    }

    @Override
    void startCall() {
        if (super.isTurnOn()) {
            System.out.println(this.getClass().getSimpleName() + " calling...");
        } else {
            System.out.println(this.getClass().getSimpleName() + " is not on. You can't turn off the phone");
        }
    }


    @Override
    void stopCall() {
        if (super.isTurnOn()) {
            System.out.println(this.getClass().getSimpleName() + " call is ending...");
        } else {
            System.out.println("You can't stop the call. Please first turn on the phone....");
        }
    }

    @Override
    void sendSms() {
        if (super.isTurnOn()) {
            System.out.println(this.getClass().getSimpleName() + " sending sms...");
        } else {
            System.out.println("You can't send sms. Please first turn on the phone....");
        }
    }

    @Override
    public void shareFile() {
        if (super.isTurnOn()) {
            System.out.println(this.getClass().getSimpleName() + " File sharing...");
        } else {
            System.out.println("You can't share file. Please first turn on the phone...");
        }
    }

    @Override
    public void listenToMp3() {
        if (super.isTurnOn()) {
            System.out.println(this.getClass().getSimpleName() + " Listening to mp3...");
        } else {
            System.out.println("You can't listen to music. Please first turn on the phone...");
        }
    }
}
