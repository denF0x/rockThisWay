package Games.TextRPG.src.Enemies;

import Games.TextRPG.src.Inventory;

/**
 * Created by Денис on 14.02.2017.
 */
public class Goblin extends Enemy {

    public Goblin(int levelOfHero) {
        super(levelOfHero);
        myInventory = new Inventory();
        name = "Goblin";
        charClass = "Monster";
        strength = 4;
        dexterity = 10;
        endurance = 3;
        calculateSecondaryParameters();
        levelUp(levelOfHero);
        spawnItemsForEnemy(20);
        spawnCoinsForEnemy(20);
    }
}
