package org.kodluyoruz.homework.hw1.classes;

import org.kodluyoruz.homework.hw1.interfaces.IKeyboard;

public abstract class MobilePhone {
    private String modelName;
    private String imeiNumber;
    private String versionNumber;
    private int memoryCapacity;
    private double screenSize;
    private IKeyboard iKeyboard; // has-a
    private boolean isTurnOn = false;

    // constructors
    public MobilePhone() {
    }

    public MobilePhone(String modelName, String imeiNumber, String versionNumber, int memoryCapacity, double screenSize, IKeyboard iKeyboard) {
        this.modelName = modelName;
        this.imeiNumber = imeiNumber;
        this.versionNumber = versionNumber;
        this.memoryCapacity = memoryCapacity;
        this.screenSize = screenSize;
        this.iKeyboard = iKeyboard;
    }

    // getters and setters
    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getImeiNumber() {
        return imeiNumber;
    }

    public void setImeiNumber(String imeiNumber) {
        this.imeiNumber = imeiNumber;
    }

    public String getVersionNumber() {
        return versionNumber;
    }

    public void setVersionNumber(String versionNumber) {
        this.versionNumber = versionNumber;
    }

    public int getMemoryCapacity() {
        return memoryCapacity;
    }

    public void setMemoryCapacity(int memoryCapacity) {
        this.memoryCapacity = memoryCapacity;
    }

    public double getScreenSize() {
        return screenSize;
    }

    public void setScreenSize(double screenSize) {
        this.screenSize = screenSize;
    }

    public IKeyboard getiKeyboard() {
        return iKeyboard;
    }

    public void setiKeyboard(IKeyboard iKeyboard) {
        this.iKeyboard = iKeyboard;
    }

    public boolean isTurnOn() {
        return this.isTurnOn;
    }

    public void setTurnOn(boolean turnOn) {
        this.isTurnOn = turnOn;
    }

    /** common methods
     * eğer ortak metodlar tamamen aynı işleri yapacaksa burada da yazılabilir. (1. ihtimal)
     * ama yapılacak işler ortak olsa bile o işi yapma yöntemi farklı nesneler için farklı ise
     * abstract metod haline getirilip kalıtımla her sınıfın kendi davranışını kendisinin yazması sağlanmalıdır (2. ihtimal)
     * burada 2. ihtimal göz önüne alındığından kodlar o şekilde yazılmıştır.
     * 1. ihtimalin kodu ise aşağıda yorum satırı halindedir
    */
    // abstract metodlar
    abstract void turnOnPhone();
    abstract void turnOffPhone();
    abstract void startCall();
    abstract void stopCall();
    abstract void sendSms();

    public void printKeyboardType(){
        if (this.isTurnOn) {
            this.iKeyboard.printKeyboardType();
        } else {
            System.out.println("You can't write something. Please first turn on the phone...");
        }
    }

    @Override
    public String toString() {
        return "{" +
                "Model name = '" + modelName + '\'' +
                ", IMEI number = '" + imeiNumber + '\'' +
                ", Version number = '" + versionNumber + '\'' +
                ", Memory capacity= " + memoryCapacity +
                ", Screen size= " + screenSize +
                ", Keyboard type= " + iKeyboard.getClass().getSimpleName() +
                ", Is turn on? = " + isTurnOn +
                '}';
    }

    /*protected void turnOnPhone() {
        if (this.isTurnOn) {
            System.out.println("You can't turn on the phone again. Because phone is already on");
        } else {
            this.isTurnOn = true;
            System.out.println(this.getClass().getSimpleName() + " turning on...");
        }
    }

    protected void turnOffPhone() {
        if (this.isTurnOn) {
            this.isTurnOn = false;
            System.out.println(this.getClass().getSimpleName() + " turning off...");
        } else {
            System.out.println(this.getClass().getSimpleName() + " is not on. You can't turn off the phone");
        }
    }

    protected void startCall() {
        if (this.isTurnOn) {
            if (!this.isCalling) {
                this.isCalling = true;
                System.out.println(this.getClass().getSimpleName() + " calling...");
            } else {
                System.out.println(this.getClass().getSimpleName() + " already calling. You can't make another call...");
            }
        } else {
            System.out.println("You can't make a call. Please first turn on the phone....");
        }
    }

    protected void stopCall() {
        if (this.isTurnOn) {
            if (this.isCalling) {
                this.isCalling = false;
                System.out.println(this.getClass().getSimpleName() + " call is ending...");
            } else {
                System.out.println(this.getClass().getSimpleName() + " no call. You can't hang up the call.. ");
            }
        } else {
            System.out.println("You can't stop the call. Please first turn on the phone....");
        }
    }

    protected void sendSms() {
        if (this.isTurnOn) {
            System.out.println(this.getClass().getSimpleName() + " sending sms...");
        } else {
            System.out.println("You can't send sms. Please first turn on the phone....");
        }
    }

    protected void write() {
        if (this.isTurnOn) {
            this.iKeyboard.write();
        } else {
            System.out.println("You can't write something. Please first turn on the phone...");
        }
    }*/
}
