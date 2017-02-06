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

    public void raiseAttack(int attackRaiser){ //повысили атаку на столько-то едениц. сброс при addKillCounter
        attack += attackRaiser;
    }

    public void fullHeal(){
        hp = hpMax;
        System.out.println("Здоровье персонажа " + name + " полностью восстановлено");
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
            case "Большой эликсир здоровья":
                cure(150);
                System.out.println(name + " повысил здоровья на 150 единиц. Здоровье: " + hp );
                break;
            case "Бутылка здоровья":
                cure(50);
                System.out.println(name + " повысил здоровья на 50 единиц. Здоровье: " + hp );
                break;
            case "Камень рыцаря":
                cure(20);
                System.out.println(name + " лизнул камень и восстановил 20 ед. здоровья. Здоровье: " + hp   );
                break;
            case "Огнеупорный лифчик":
                System.out.println(Utils.answerForAllQuestions);
                break;
            case "Черный большой елдак":
                System.out.println(Utils.answerForAllQuestions);
                break;
            case "Гигантские шары кролика (дарует удачу)":
                System.out.println(Utils.answerForAllQuestions);
                break;
            case "Карта, ведущая к острову амазонок":
                System.out.println(Utils.answerForAllQuestions + ". Но знайте, что тут будет чумовой квест");
                break;
            case "Точильный камень":
                raiseAttack(10);
                System.out.println(name + " использовал на оружии точильный камень и повысил свою атаку на 10 едениц. Текущая атака: " + attack);
                break;
            case "Сырое мясо убитого оленя":
                cure(10);
                System.out.println(name + " очень неприятно есть сырое мясо, но хотя бы это восстанавливает герою 10 ед. здоровья. Здоровье: " + hp);
                break;
            case "Шкура убитого оленя":
                System.out.println(Utils.answerForAllQuestions);
                break;
            case "Жареное мясо убитого оленя":
                cure(100);
                System.out.println(name + " не без удовольствия съедает шмат оленины. Это восстанавливает герою 10 ед. здоровья. Здоровье: " + hp);
                break;
        }
    }



}
