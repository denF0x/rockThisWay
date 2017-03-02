package Games.TextRPG.src.Enemies;

import Games.TextRPG.src.*;

/**
 * Created by Денис on 14.02.2017.
 */
public class Enemy extends GameCharacter {

    public Enemy(int levelOfHero) {
        super(levelOfHero);
    }

    void spawnItemsForEnemy(int randomparameter) {
        if (Utils.rand.nextInt(100) < randomparameter) {
            myInventory.addToInventory(Items.smallElicsirOfHealth);
        }
    }

    void spawnCoinsForEnemy(int maxPossibleMoney) {
        myInventory.addSomeCoins(Utils.rand.nextInt(maxPossibleMoney));
    }

    void showFullestInfo() {
        System.out.println("name: " + name + " charClass: " + charClass + "\nattack: " + attack + " defense: " + defense + " hpMax " + hpMax + "\ndexterity: " + dexterity + " strength: " + strength + " endurance: " + endurance);
    }

    void levelUp(int level) {
        for (int i = 0; i <= level; i++) {
            strength += (int) (defaultStrength * 0.3f);
            dexterity += (int) (defaultDexterity * 0.3f);
            endurance += (int) (defaultEndurance * 0.3f);
            calculateSecondaryParameters();
            hp = hpMax;
        }
    }

    public static Enemy randomMonster(Hero hero) {
        int choice = Utils.rand.nextInt(3);
        switch (choice) {
            case 0:
                return new Goblin(hero.getLevel());
            case 1:
                return new Orc(hero.getLevel());
            case 2:
                return new Troll(hero.getLevel());
        }
        return null;
    }
}
