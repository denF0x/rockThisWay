package Game.TextRPG.src;

/**
 * Created by Денис on 27.01.2017.
 */
public class Monster extends GameCharacter {
    public Monster(String _charClass, String _name, int _strength, int _dexterity, int _endurance) {
        super(_charClass, _name, _strength, _dexterity, _endurance);
        myInventory = new Inventory();
        myInventory.addSomeCoins(Utils.rand.nextInt(100));
        myInventory.addToInventory(Items.smallElicsirOfHealth);
    }

    public void levelUp (int _level){
        for (int i = 0; i < _level; i++)
        {
            strength += (int)(defaultStrength *0.3f);
            dexterity +=(int)(defaultDexterity *0.3f);
            endurance += (int)(defaultEndurance * 0.3f);
            calculateSecondaryParameters();
            hp = hpMax;
            level++;
        }
        showInfo();
    }
}
