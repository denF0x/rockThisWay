package Game.TextRPG.src.Enemies;

import Game.TextRPG.src.Inventory;

/**
 * Created by Денис on 14.02.2017.
 */
public class Orc extends Enemy{
    public Orc(int levelOfHero) {
        super(levelOfHero);
        myInventory = new Inventory();
        name = "Orc";
        charClass = "Monster";
        strength = 10;
        dexterity = 5;
        endurance = 10;
        calculateSecondaryParameters();
        levelUp(levelOfHero);
        spawnItemsForEnemy(35);
        spawnCoinsForEnemy(50);
    }
}
