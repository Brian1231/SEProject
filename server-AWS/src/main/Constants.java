package main;

import java.awt.*;

public final class Constants {

    private Constants() {
        // private constructor so cant be instantiated as all variables are public static final
    }


    // 24 investment
    // 3 tax
    // 4 stations
    // 2 utils
    // 3 chance

    // Property rent constants - 28 total including 2 utils at index 7 & 20
    public static final int[][] SITE_RENTS = {
        {2,10,30,90,160,250},{4,20,60,180,320,450},{25,50,100,200,200,200},
        {6,30,90,270,400,550},{6,30,90,270,400,550},{8,40,100,300,450,600},
        {10,50,150,450,625,750},{4,10,0,0,0,0},{10,50,150,450,625,750},
        {12,60,180,500,700,900},{25,50,100,200,200,200},{14,70,200,550,750,950},
        {14,70,200,550,750,950},{16,80,220,600,800,1000},{18,90,250,700,875,1050},
        {18,90,250,700,875,1050},{20,100,300,750,925,1100},{25,50,100,200,200,200},
        {22,110,330,800,975,1150},{22,110,330,800,975,1150},{4,10,0,0,0,0},
        {22,120,360,850,1025,1200},{26,130,390,900,1100,1275},
        {26,130,390,900,1100,1275},{28,150,450,1000,1200,1400},{25,50,100,200,200,200},
        {35,175,500,1100,1300,1500}, {50,200,600,1400,1700,2000}
    };


    // Property price constants - 28 total including 2 utils at index 7 & 20
    public static final int[] SITE_PRICES = {
        60,60,200,100,100,120,140,150,140,160,200,180,180,200,
        220,220,240,200,260,260,150,280,300,300,320,200,350,400
    };

    // Property house prices(hotels same price) - 22 total
    private static final int[] HOUSE_PRICES = 	{
        30, 30, 50, 50, 50,
        100,100,100,100,100,100,
        150,150,150,150,150,150,
        150,150,160,200,200
    };


    // Property mortgage constants - 28 total including 2 utils at index 7 & 20
    private static final int[] SITE_MORTGAGE_VALUE = {
        50,50,100,50,50,60,70,
        75,70,80,100,90,90,100,110,
        110,120,100,150,150,75,150,
        200,200,200,100,175,200
    };


    // Player colours
    public static final Color[] playerColours = {
        new Color(66,229,244),
        new Color(26,224,59),
        new Color(242, 50, 226),
        new Color(255, 244, 43)
    };
}
