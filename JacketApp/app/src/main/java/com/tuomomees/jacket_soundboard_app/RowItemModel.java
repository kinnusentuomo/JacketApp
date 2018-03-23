package com.tuomomees.jacket_soundboard_app;

/**
 * Luokan on luonut tuomo päivämäärällä 29.10.2017.
 */

public class RowItemModel {

    private String buttonNumber;
    private int buttonId1, buttonId2, buttonId3;
    private String button1Text, button2Text, button3Text;

    RowItemModel(int buttonId1, int buttonId2, int buttonId3, String txt1, String txt2, String txt3)
    {
        this.buttonId1 = buttonId1;
        this.buttonId2 = buttonId2;
        this.buttonId3 = buttonId3;
        this.button1Text = txt1;
        this.button2Text = txt2;
        this.button3Text = txt3;
    }

    String getText(int getTextId)
    {
        String str = null;
        switch (getTextId)
        {
            case 1:
                str =  button1Text;
                break;

            case 2:
                str =  button2Text;
                break;

            case 3:
                str = button3Text;
                break;
        }
        return str;
    }

    public String getButtonNumber() {
        return buttonNumber;
    }

    public void setButtonNumber(String buttonNumber) {
        this.buttonNumber = buttonNumber;
    }

    int getButtonId1() {
        return buttonId1;
    }
    int getButtonId2() {
        return buttonId2;
    }
    int getButtonId3() {
        return buttonId3;
    }

    public void setButtonId(int buttonId) {
        this.buttonId1 = buttonId;
    }
}
