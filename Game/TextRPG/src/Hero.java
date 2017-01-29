/**
 * Created by Денис on 27.01.2017.
 */
public class Hero extends GameCharacter  {
        //параметры для героя. в основном связанные с ростом опыта
        private int currentExp;
        private int expToNextLevel;
        private int killedMonsters;
        public Inventory myInventory;

    public Hero(String _charClass, String _name, int _strength, int _dexterity, int _endurance) {
        super(_charClass, _name, _strength, _dexterity, _endurance);
        currentExp = 0;
        expToNextLevel = 1000;
        killedMonsters = 0;
        myInventory = new Inventory();
        myInventory.addToInventory("Бутылка здоровья");
    }
    //метод, расчитывающий получение опыта за врага и повышение левела
    public void expGain(int _exp){
        currentExp += _exp;
        System.out.println(name + " получил " + _exp + " ед. опыта");
        if(currentExp > expToNextLevel)
        {
            currentExp -= expToNextLevel;
            expToNextLevel *= 2;
            level++;
            attack += 5;
            hpMax += 50;
            strength += 2;
            dexterity += 2;
            endurance += 1;
            calculateSecondaryParameters();
            hp = hpMax;
            System.out.println(name + " повысил уровень до " + level);
        }
    }

    public void ShowInfo(){
        System.out.println("Имя: " + name + "\nЗдоровье: " + hp + "/" + hpMax + " Уровень: " + level + "(" + currentExp + "/" + expToNextLevel + ")");
    }

    public void addKillCounter(){
        killedMonsters++;
    }
}

