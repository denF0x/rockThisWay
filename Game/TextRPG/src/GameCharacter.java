/**
 * Created by Денис on 27.01.2017.
 */
public class GameCharacter {
    protected String name;
    public String getFullName(){
        return name + " " + charClass + " hp: " + hpMax + " attack: " + attack + " defense: " + defense;
    }
    public String getName() {
        return name;
    }
    protected int hp;
    protected int hpMax;
    public int getHpMax(){
        return hpMax;
    }
    protected int attack;
    protected int defense;
    protected int critChance;
    protected int level = 1;
    protected boolean blockStance;
    public boolean isAlive;

    protected String charClass;
    public GameCharacter(String _charClass, String _name, int _hp, int _attack, int _defense){
        name = _name;
        charClass = _charClass;
        hpMax = _hp;
        hp = hpMax;
        attack = _attack;
        defense = _defense;
        critChance = 10;
        isAlive = true;
        blockStance = false;
    }

    public void ShowInfo(){
        System.out.println("Имя: " + name + "\nЗдоровье: " + hp + "/" + hpMax);
    }

    public void setBlockStance(){
        blockStance = true;
        System.out.println(name + " встал в защитную стойку");
    }

    public void makeNewRound(){
        blockStance = false;
    }

    public int makeAttack(){
        //20 -> 16..24
        int minAttack = (int)(attack * 0.8f);
        int deltaAttack = (int)(attack * 0.4f);
        int currentAttack = minAttack + GameClass.rand.nextInt(deltaAttack);
        if(GameClass.rand.nextInt(100) < critChance)
        {
            currentAttack *= 2;
            System.out.println(name + " нанес критический урон в размере " + currentAttack + " ед. урона");
        }
        else {
            System.out.println(name + " нанес " + currentAttack + " ед. урона");
        }
        return currentAttack;
    }

    public void getDamage(int _inputDamage){
        _inputDamage -= defense;
        if (blockStance) {
            System.out.println(name + " дополнительно заблокировал " + defense + " ед. урона в защитной стойке");
            _inputDamage -=defense;
        }
        if (_inputDamage < 0) _inputDamage =0;
        System.out.println(name + " получил " + _inputDamage + " ед. урона");
        hp -= _inputDamage;
        if(hp < 1){
            isAlive = false;
        }
    }

}
