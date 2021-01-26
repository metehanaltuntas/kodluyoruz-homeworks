package org.kodluyoruz.homework.hw1.classes;

import org.kodluyoruz.homework.hw1.interfaces.IKeyboard;

public class TouchKeyboard implements IKeyboard {
    @Override
    public void printKeyboardType() {
        System.out.println("This is a: " + this.getClass().getSimpleName());
    }
}
