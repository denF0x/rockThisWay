package Game.TextRPG.src;

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
        myInventory.addToInventory(Items.bigElicsirOfHealth);
        myInventory.addToInventory(Items.smallElicsirOfHealth);
        myInventory.addToInventory(Items.defaultItemForKnight); //пока так
        myInventory.addToInventory(Items.stoneForBlade);
        myInventory.addToInventory(Items.rawMeatOfDear);
        myInventory.addSomeCoins(10000);
    }
    //метод, расчитывающий получение опыта за врага и повышение левела
    public void expGain(int _exp){
        currentExp += _exp;
        System.out.println(name + " получил " + _exp + " ед. опыта");
        if(currentExp >= expToNextLevel)
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
            System.out.println(name + " повысил уровень до " + level + " Необходимо опыта до след. уровня: " + expToNextLevel);
        }
    }
    public int getLevel() {
        return level;
    }

    public void showInfo(){
        System.out.println("Имя: " + name + "\nЗдоровье: " + hp + "/" + hpMax + " Уровень: " + level + "(" + currentExp + "/" + expToNextLevel + ")");
    }

    public void addKillCounter(){
        killedMonsters++;
        if (killedMonsters % 3 == 0 && killedMonsters !=0 ){
            calculateSecondaryParameters();
            System.out.println("Атака героя вернулась в норму и снова равна " + attack + " ед. урона");
        }
    }

    public void doHunting(){
        int huntingDecision = Utils.getAction(0,3, "\n\nГерой отправился на охоту.\n1.Отправиться к водопою и устроить засаду\n2.Поискать лесные тропы, чтобы выследить добычу\n3.Затаиться с верным боевым луком в кустах и надеяться на удачу\n0.Вернуться на дорогу");
        switch (huntingDecision) {
            case 1:
                System.out.println(name + " приходит к водопою и видит большого оленя. Собравшись с силами герой делает рывок и...");
                if ((dexterity * 5) < 75) {
                    if (Utils.rand.nextInt(100) < dexterity * 2){
                        myInventory.addToInventory(Items.skinOfDear);
                        myInventory.addToInventory(Items.rawMeatOfDear);
                        System.out.println("... настигает свою жертву. Мясо и шкура убитого оленя добавлены в инвентарь");
                    } else{
                        System.out.println("... промахивается. Олень успевает сбежать и спугнуть остальную дичь на водопое, оставляя нашего героя ни с чем.");
                    }
                } else {
                    if (Utils.rand.nextInt(100) < 75){
                        myInventory.addToInventory(Items.skinOfDear);
                        myInventory.addToInventory(Items.rawMeatOfDear);
                        System.out.println("... настигает свою жертву. Мясо и шкура убитого оленя добавлены в инвентарь");
                    } else{
                        System.out.println("... промахивается. Олень успевает сбежать и спугнуть остальную дичь на водопое, оставляя нашего героя ни с чем.");
                    }
                }
                break;
            case 2:
                System.out.println(name + " отправляется на поиски лесных троп. Спустя пару часов плутаний по лесу он находит свежий след и отправляется за добычей...");
                if ((endurance * 5) < 75) {
                    if (Utils.rand.nextInt(100) < endurance * 2){
                        myInventory.addToInventory(Items.skinOfDear);
                        myInventory.addToInventory(Items.rawMeatOfDear);
                        System.out.println("... Поиски оказались не напрасными и охота увенчалась успехом. Мясо и шкура убитого оленя добавлены в инвентарь");
                    } else{
                        System.out.println("...Видимо, след был не таким уж и свежим. Да и вообще следопыт из нашего героя так себе. Не лучшее было решение");
                    }
                } else {
                    if (Utils.rand.nextInt(100) < 75){
                        myInventory.addToInventory(Items.skinOfDear);
                        myInventory.addToInventory(Items.rawMeatOfDear);
                        System.out.println("... Поиски оказались не напрасными и охота увенчалась успехом. Мясо и шкура убитого оленя добавлены в инвентарь");
                    } else{
                        System.out.println("...Видимо, след был не таким уж и свежим. Да и вообще следопыт из нашего героя так себе. Не лучшее было решение");
                    }
                }
                break;
            case 3:
                System.out.println(name + " решил не мудрствовать лукаво и спрятался в кустах. Немного повозившись герою удается удобно разместиться в засаде и практически сразу накопленная усталость берет свое и " + name + " засыпает...");
                if (Utils.rand.nextInt(100) < 25){
                    myInventory.addToInventory(Items.skinOfDear);
                    myInventory.addToInventory(Items.rawMeatOfDear);
                    System.out.println("... Проснувшись, герой замечает неподалеку молодого оленя. С улыбкой " + name + " натягивает тетиву и поражает свою жертву прямо в сердце. Мясо и шкура убитого оленя добавлены в инвентарь");
                } else{
                    System.out.println("... " + name + " просыпается поздним вечером изрядно продрогшим и с осознанием того, что сегодня придется ложиться спать на пустой желудок");
                }
                break;
            case 0:
                System.out.println("Герой решает, что сейчас не время для охоты");
                return;
        }
    }

    public void doCooking(){
        myInventory.showAllItems();
        int invInput = Utils.getAction(0, myInventory.getSize(), "Из чего вы хотите приготовить еду?");
        String ingred = myInventory.useItem(invInput);
        if(ingred == "Сырое мясо убитого оленя"){
            System.out.println("\n\nТерпеливо " + name + " пожарил себе мясо. В инвентарь добавлено Жареное мясо убитого оленя");
            myInventory.addToInventoryAfterWin(Items.friedMeatOfDear);
        } else{
            System.out.println("Приготовить что-то съедобное из этого будет не получилось. Вы потеряли " + ingred);
            doCooking();
        }
    }


}

