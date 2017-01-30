/**
 * Created by Денис on 26.01.2017.
 */
public class GameClass {

    //массивы героев и врагов
    private Hero[] heroPattern = new Hero[3];
    private Monster[] monsterPattern = new Monster[3];

    //параметры основного цикла
    private Hero mainHero;
    private Monster currentMonster;
    private GameMap map;
    private int currentRound;
    private int inpInt;

    //инициализация игры
    public GameClass(){
        initGame();
    }


    //основной игровой цикл
    public void mainGameLoop() {
        map = new GameMap();

        inpInt = 0;
        System.out.println("Игра началась!");

        selectHero();
        mainHero.setPosByXAndY(10, 3);
        //сюжетная вставка
        System.out.println(mainHero.getName() + " отправляется в свой первый эпический квест. Авторская команда в лице Дениса, Насти и мерзкой падлы Ванды желает ему удачи");
        //выход первого врага
        //currentMonster = (Monster)monsterPattern[0].clone();
        //currentMonster.levelUp(6);
        //System.out.println("Первым на арену выходит " + currentMonster.getFullName());
        //battle(mainHero, currentMonster);
        map.buildMapOfDanger(10,3);
        map.updateMap(mainHero.getX(), mainHero.getY());
        map.showMap();

        while (true) {
            int desOnMainMap = getAction(1, 6, "Что вы хотите сделать дальше?\n1.Идти влево\n2.Идти вправо\n3.Идти вверх\n4.Идти вниз\n5.Найти монстров\n6.Разбить лагерь");
            switch (desOnMainMap) {
                case 1:
                    if (map.isCellEmpty(mainHero.getX()-1, mainHero.getY()))
                        mainHero.moveHero(-1, 0);
                    break;

                case 2:
                    if (map.isCellEmpty(mainHero.getX() + 1, mainHero.getY()))
                        mainHero.moveHero(1, 0);
                    break;
                case 3:
                    if (map.isCellEmpty(mainHero.getX(), mainHero.getY() - 1))
                        mainHero.moveHero(0, -1);
                    break;
                case 4:
                    if (map.isCellEmpty(mainHero.getX(), mainHero.getY() + 1))
                        mainHero.moveHero(0, 1);
                    break;
                case 5:
                    currentMonster = (Monster) monsterPattern[Utils.rand.nextInt(3)].clone();
                    currentMonster.levelUp(map.getMapOfDangerCoordinates(mainHero.getY(),mainHero.getX()));
                    battle(mainHero, currentMonster);
                    break;
                case 6:
                    mainHero.fullHeal();
                    break;
            }
            map.updateMap(mainHero.getX(), mainHero.getY());
            map.showMap();

            if (Utils.rand.nextInt(100) < 20) {
                currentMonster = (Monster) monsterPattern[Utils.rand.nextInt(3)].clone();
                System.out.println("На вас внезапно напал " + currentMonster.getName());
                currentMonster.levelUp(map.getMapOfDangerCoordinates(mainHero.getY(),mainHero.getX()));
                battle(mainHero, currentMonster);
                map.updateMap(mainHero.getX(), mainHero.getY());
                map.showMap();
            }

            if (!mainHero.isAlive()) {
                System.out.println("Игра завершена");
                break;
            }
        }
    }



    public void selectHero()
    {
        //выбор героя
        String s = "Выберите героя:\n";
        for (int i = 0; i < 3; i++){
            s +=  (i+1) + ". " + heroPattern[i].getFullName() + "\n";
        }
        inpInt = getAction(1,3, s);
        mainHero = (Hero)heroPattern[inpInt-1].clone();
        System.out.println("Вы выбрали " + mainHero.getFullName());
    }

    public void battle(Hero battleHero, Monster battleMonster)
    {
        //вся магия здесь. UPD: Здесь всего лишь боевка. И то не вся
        currentRound = 1;
        System.out.println("Бой между игроком " + battleHero.getName() + " и монстром " + battleMonster.getName() + " начался!");
        do{
            //начало раунда
            System.out.println("Текущий раунд: " + currentRound);
            battleHero.showInfo();
            battleMonster.showInfo();
            //ход героя
            System.out.println("Ход игрока");
            battleHero.makeNewRound();
            inpInt = getAction(0,3, "1. Атака\n2. Защита\n3. Открыть инвентарь\n0. Выйти из игры");
            //пропуск двух строк
            System.out.println("\n\n");
            //1.Атака
            if(inpInt == 1){
                battleMonster.getDamage(battleHero.makeAttack()); //монстр получает урон
                if(!battleMonster.isAlive()) //жив ли монстр проверяем, ибо бесит меня, что он атакует после смерти
                {
                    System.out.println(battleMonster.getName() + " погиб");
                    battleHero.expGain(battleMonster.getHpMax() * 2); //экспы за монстра дают в размере двух его макс хп
                    battleHero.addKillCounter();
                    battleMonster.myInventory.transferAllItemsToAnotherInventory(battleHero.myInventory);
                    //currentMonster = (Monster)monsterPattern[Utils.rand.nextInt(3)].clone(); //сложносоставленная херня, которая бесконечно копирует монстров из патерна рандомным образом
                    break;
                    //System.out.println("На поле боя выходит " + currentMonster.getName()); //сообщение о выходе нового монстра
                }
            }
            //2. Защита
            if (inpInt == 2){
                battleHero.setBlockStance();
            }
            if (inpInt == 3){
                battleHero.myInventory.showAllItems();
                int invInput = getAction(0, battleHero.myInventory.getSize(), "Выберите предмет для использования");
                String usedItem = battleHero.myInventory.useItem(invInput);
                if (usedItem != ""){
                    System.out.println(battleHero.getName() + " использовал " + usedItem);
                    battleHero.useItem(usedItem);
                } else {
                    System.out.println(battleHero.getName() + " просто закрыл сумку");
                }
            }
            //0.Выйти из игры
            if (inpInt == 0 ) break;
            //ход монстра
            battleMonster.makeNewRound();
            if (Utils.rand.nextInt(100) < 80) {
                battleHero.getDamage(battleMonster.makeAttack());
            }else {
                battleMonster.setBlockStance();
            }
            //конец текущего раунда
            currentRound ++;

        } while(true);
        //сообщение при победе или поражении
        if (battleMonster.isAlive() && battleHero.isAlive()) System.out.println(battleHero.getName() + " сбежал с поля боя");
        if(!battleHero.isAlive()) System.out.println("Победил " + battleMonster.getName());
        if(!battleMonster.isAlive()) System.out.println("Победил " + battleHero.getName());
    }

        //вся инфа по персонажам пока здесь
    private void initGame() {
        heroPattern[0] = new Hero("Knight", "Lancelot", 16, 10, 20);
        heroPattern[1] = new Hero("Barbarian", "Konan", 20, 5, 20);
        heroPattern[2] = new Hero("Dwarf", "Gimli", 18, 7, 20);

        monsterPattern[0] = new Monster("Humanoid", "Goblin", 6, 6, 1);
        monsterPattern[1] = new Monster("Humanoid", "Orc", 12, 6, 1);
        monsterPattern[2] = new Monster("Humanoid", "Troll", 16, 12, 1);

        currentRound = 1;
    }

    //метод для адекватной работы с выбором вариантов
    public int getAction(int _min, int _max, String _str)
    {
        int x = 0;
        do
        {
          if(_str != "")  System.out.println(_str);
            x = Utils.sc.nextInt();
        } while ( x < _min || x > _max);

        return x;
    }
}



//здесь у нас мусор, который жалко выбросить:
/*int desOnMainMap = getAction(1, 5, "Что вы хотите сделать дальше?\n1. Следующий бой\n2.Перейти в более опасную локацию\n3.Перейти в город\n4.Отдохнуть\n5.Выход из игры");
            switch (desOnMainMap) {
                case 1:
                    currentMonster = (Monster) monsterPattern[Utils.rand.nextInt(3)].clone();
                    currentMonster.levelUp(mainHero.getDangerofZone());
                    battle(mainHero, currentMonster);
                    break;

                case 2:
                    mainHero.goToDangerousZone();
                    break;
                case 4:
                    mainHero.fullHeal();
                    break;
            }
            if(!mainHero.isAlive()) break;
        }*/