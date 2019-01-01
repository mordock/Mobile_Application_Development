package com.example.maxwe.numbertrivia;

public class Numbers {
    private String numberText;
    private Integer numberValue;

    public String getNumberText(){return numberText;}
    public void setNumberText(String numberText){this.numberText = numberText;}

    public Integer getNumberValue(){return numberValue;}
    public void setNumberValue(Integer numberValue){this.numberValue = numberValue;}

    public Numbers(Integer numberValue, String numberText){
        this.numberText = numberText;
    }

    @Override
    public String toString(){return numberText;}
}
