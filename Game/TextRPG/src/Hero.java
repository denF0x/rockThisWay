/**
 * Created by Денис on 27.01.2017.
 */
public class Hero extends GameCharacter implements Cloneable {

        private int currentExp;
        private int expToNextLevel;

    public Hero(String _charClass, String _name, int _hp, int _attack, int _defense) {
        super(_charClass, _name, _hp, _attack, _defense);
        initHero();
    }

    private void initHero(){
        currentExp = 0;
        expToNextLevel = 1000;
    }

    public void expGain(int _exp){
        currentExp += _exp;
        System.out.println(name + " получил " + _exp + " ед. опыта");
        if(currentExp > expToNextLevel){
            currentExp -= expToNextLevel;
            expToNextLevel *= 2;
            level++;
            System.out.println(name + " повысил уровень до " + level);
            System.out.println("Атака героя повысилась до " + attack + " ед. урона\nЗдоровье полностью восстановлено");
            attack += 5;
            hp = hpMax;
        }
    }

    public void ShowFullInfo(){

    }

    public Object clone(){
        try{
            return super.clone();
        } catch (CloneNotSupportedException e) {
            //System.out.println("Клонирование невозможно");
            return this;
        }
    }
}

