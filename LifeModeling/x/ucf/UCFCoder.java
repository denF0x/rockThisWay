package LifeModeling.x.ucf;

/**
 * Created by Денис on 02.03.2017.
 */
public class UCFCoder {

    //Units Combination Format = XXXX XXXX XXXX XXXX;
    public static final int LANDSCAPE_MASK = 0xF000;


    public static final int LANDSCAPE_WATER_LOW = 0x1;
    public static final int LANDSCAPE_WATER_HIGH = 0x2;
    public static final int LANDSCAPE_GROUND_LOW = 0x3;
    public static final int LANDSCAPE_GROUND_HIGH = 0x4;
    public static final int LANDSCAPE_DESERT_LOW = 0x5;
    public static final int LANDSCAPE_DESERT_HIGH = 0x6;
    public static final int LANDSCAPE_SNOW_LOW = 0x7;
    public static final int LANDSCAPE_SNOW_HIGH = 0x8;
    public static final int LANDSCAPE_TREE_LOW = 0x9;
    public static final int LANDSCAPE_TREE_HIGH = 0xA;
    public static final int LANDSCAPE_MOUNTAIN_LOW = 0xB;
    public static final int LANDSCAPE_MOUNTAIN_HIGH = 0xC;
    public static final int LANDSCAPE_SWAMP_LOW = 0xD;
    public static final int LANDSCAPE_SWAMP_HIGH = 0xE;
    public static final int LANDSCAPE_CAVE = 0xF;


    public static final int CREATURE_MASK = 0xF000;

    public static final int CREATURE_HUMAN = 0x1;
    public static final int CREATURE_MAMMOTH = 0x2;
    public static final int CREATURE_WOLF = 0x3;
    public static final int CREATURE_RABBIT = 0x4;
    public static final int CREATURE_SHARK = 0x5;
    public static final int CREATURE_DRAGON = 0x6;
    public static final int CREATURE_UNICORN = 0x7;
    public static final int CREATURE_DOLPHIN = 0x8;
    public static final int CREATURE_SNAKE = 0x9;
    public static final int CREATURE_CHICKEN = 0xA;
    public static final int CREATURE_SCORPIO = 0xB;
    public static final int CREATURE_BEAR = 0xC;
    public static final int CREATURE_HORSE = 0xD;
    public static final int CREATURE_RUH = 0xE;
    public static final int CREATURE_KRAKEN = 0xF;


    public static final int decodeLandscape(int ucf) {
        //1010 1010 1100 0000
        //&
        //1111 0000 0000 0000
        //-------------------
        //1010 0000 0000 0000
        return (ucf &  LANDSCAPE_MASK) >> 12;
    }

    public static final int encodeLandscape(int ucf, int uf) {
        //1010 1010 1100 0000
        //&
        //0000 1111 1111 1111
        //-------------------
        //0000 1010 1100 0000

        //0000 0000 0000 1110
        //<<12
        //-------------------
        //1110 0000 0000 0000

        //1110 0000 0000 0000
        //|
        //0000 1010 1100 0000
        //-------------------
        //1110 1010 1100 0000
        return (ucf & ~LANDSCAPE_MASK) | (uf << 12);
    }

    public static final int decodeCreature(int ucf) {
        return (ucf &  CREATURE_MASK) >> 8;
    }
    public static final int encodeCreature(int ucf, int uf) {
        return (ucf &  ~CREATURE_MASK) | (uf << 8);
    }

}