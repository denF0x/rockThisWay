package Game.TextRPG.src.Enemies;

import Game.TextRPG.src.Inventory;

/**
 * Created by Денис on 14.02.2017.
 */
public class Troll extends Enemy {

    public Troll(int levelOfHero) {
        super(levelOfHero);
        myInventory = new Inventory();
        name = "Troll";
        charClass = "Monster";
        strength = 10;
        dexterity = 4;
        endurance = 12;
        calculateSecondaryParameters();
        levelUp(levelOfHero);
        spawnItemsForEnemy(50);
        spawnCoinsForEnemy(70);
    }
}
