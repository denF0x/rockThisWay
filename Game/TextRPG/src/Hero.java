/**
 * Created by Денис on 27.01.2017.
 */
public class Hero extends GameCharacter  {
        //параметры для героя. в основном связанные с ростом опыта
        private int currentExp;
        private int expToNextLevel;
        private int killedMonsters;
        private int currentZone;
        private int posX;
        public int getX(){
            return posX;
        }
        private int posY;
        public int getY(){
            return posY;
        }

        public void setPosByXAndY(int _x, int _y){
            posX = _x;
            posY = _y;

        }

        public void moveHero(int _vx, int _vy){
            posX += _vx;
            posY += _vy;
        }

        public void goToDangerousZone(){
            currentZone++;
            System.out.println("Вы перешли в зону опасности " + currentZone);
        }

        public int getDangerofZone(){
            return currentZone;
        }

    public Hero(String _charClass, String _name, int _strength, int _dexterity, int _endurance) {
        super(_charClass, _name, _strength, _dexterity, _endurance);
        currentZone = 1;
        currentExp = 0;
        expToNextLevel = 1000;
        killedMonsters = 0;
        myInventory = new Inventory();
        myInventory.addToInventory(new Item("Слабый камень здоровья", Item.ItemType.InfinityConsumables));
        myInventory.addToInventory(new Item("Бутылка здоровья", Item.ItemType.Consumables));
        myInventory.addSomeCoins(1000);
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

    public void showInfo(){
        System.out.println("Имя: " + name + "\nЗдоровье: " + hp + "/" + hpMax + " Уровень: " + level + "(" + currentExp + "/" + expToNextLevel + ")");
    }

    public void addKillCounter(){
        killedMonsters++;
    }
}

