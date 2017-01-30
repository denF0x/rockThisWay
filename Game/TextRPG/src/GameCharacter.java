/**
 * Created by Денис on 27.01.2017.
 */
public class GameCharacter implements Cloneable {

    //методы для имени
    public String getFullName(){
        return name + " " + charClass + " hp: " + hpMax + " attack: " + attack + " defense: " + defense;
    }
    public String getName() {
        return name;
    }

    //базовые параметры
    protected String name;
    protected String charClass;

    protected int hpMax;
    public int getHpMax(){
        return hpMax;
    }
    protected int attack;
    protected int defense;

    //дополнительные параметры
    protected int defaultStrength;
    protected int defaultDexterity;
    protected int defaultEndurance;
    protected int strength;
    protected int dexterity;
    protected int endurance;

    //критические шансы
    protected int critChance;
    protected float critMultiplier;
    protected int avoidChance;

    //внутриигровые параметры
    protected int hp;
    protected int level;
    protected boolean blockStance;
    public boolean life;
    public boolean isAlive(){
        return life;
    }

    protected Inventory myInventory;

    //параметры персонажа

    public GameCharacter(String _charClass, String _name, int _strength, int _dexterity, int _endurance){
        name = _name;
        charClass = _charClass;
        strength = _strength;
        dexterity = _dexterity;
        endurance = _endurance;
        defaultStrength = _strength;
        defaultDexterity = _dexterity;
        defaultEndurance = _endurance;
        calculateSecondaryParameters();
        level = 1;
        hp = hpMax;
        life = true;
        blockStance = false;
    }

    //расчет базовых параметров через внутриигровые
    public void calculateSecondaryParameters(){
        attack = strength *2;
        hpMax = endurance * 50;
        defense = (int)((strength + dexterity) / 4.0f);
        critChance = dexterity * 1;
        critMultiplier = 1.2f + (dexterity / 20.0f);
        avoidChance = 8 + (int)(dexterity/5.0f);
    }

    //инфа о персонаже
    public void showInfo(){
        System.out.println("Имя: " + name + "\nЗдоровье: " + hp + "/" + hpMax + " Уровень: " + level);
    }

    //защитная стойка
    public void setBlockStance(){
        blockStance = true;
        System.out.println(name + " встал в защитную стойку");
    }

    public void cure (int _val) {
        hp += _val;
        if (hp > hpMax) {
            hp = hpMax;
        }
    }

    public Object clone(){
        try{
            return super.clone();
        } catch (CloneNotSupportedException e) {
            System.out.println("Клонирование невозможно");
            return this;
        }
    }

    //метод, сбрасывающий все на дефолт
    public void makeNewRound(){
        blockStance = false;
    }

    //метод, расчитывающий показатель атаки с 20% разбросом от базового параметра
    public int makeAttack(){
        //20 -> 16..24
        int minAttack = (int)(attack * 0.8f);
        int deltaAttack = (int)(attack * 0.4f);
        int currentAttack = minAttack + Utils.rand.nextInt(deltaAttack);
        if(Utils.rand.nextInt(100) < critChance)
        {
            currentAttack = (int)(currentAttack * critMultiplier);
            System.out.println(name + " нанес критический урон в размере " + currentAttack + " ед. урона");
        }
        else {
            System.out.println(name + " нанес " + currentAttack + " ед. урона");
        }
        return currentAttack;
    }
    //а этот отвечает за получение урона и смерть
    public void getDamage(int _inputDamage){
        if (Utils.rand.nextInt(100) < avoidChance){
            System.out.println(name + " увернулся от атаки и получил 0 ед. урона");
        } else {
                _inputDamage -= Utils.rand.nextInt(defense);
            if (blockStance) {
                System.out.println(name + " заблокировал часть урона в защитной стойке");
                _inputDamage -= Utils.rand.nextInt(defense);
            }
            if (_inputDamage < 0) _inputDamage = 0;
            System.out.println(name + " получил " + _inputDamage + " ед. урона");
            hp -= _inputDamage;
            if (hp < 1) {
                life = false;
            }
        }

    }

    public void useItem (String _item)
    {
        switch (_item)
        {
            case "Бутылка здоровья":
                cure(50);
                System.out.println(name + " повысил здоровья на 50 единиц. Здоровье: " + hp );
                break;
            case "Слабый камень здоровья":
                cure(20);
                System.out.println(name + " лизнул камень и восстановил 20 ед. здоровья. Здоровье: " + hp   );
        }
    }

    public void fullHeal(){
        hp = hpMax;
        System.out.println("Здоровье персонажа " + name + " полностью восстановлено");
    }
}
