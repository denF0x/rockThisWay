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
    private InGameShop shop;
    private int currentRound;
    private int inpInt;

    //инициализация игры
    public GameClass() {
        initGame();
    }


    //основной игровой цикл
    public void mainGameLoop() {
        map = new GameMap();
        shop = new InGameShop();

        inpInt = 0;
        System.out.println("Игра началась!");

        selectHero();
        mainHero.setPosByXAndY(10, 3);
        //сюжетная вставка
        System.out.println(mainHero.getName() + " отправляется в свой первый эпический квест. Авторская команда в лице Дениса, Насти и мерзкой падлы Ванды желает ему удачи");
        //выход первого врага. оставил для тестов
        //currentMonster = (Monster)monsterPattern[0].clone();
        //currentMonster.levelUp(6);
        //System.out.println("Первым на арену выходит " + currentMonster.getFullName());
        //battle(mainHero, currentMonster);
        //map.buildMapOfDanger(10, 3);
        map.updateMap(mainHero.getX(), mainHero.getY());
        map.showMap();

        while (true) {
            int desOnMainMap = Utils.getAction(1, 6, "Что вы хотите сделать дальше?\n1.Идти влево\n2.Идти вправо\n3.Идти вверх\n4.Идти вниз\n5.Найти монстров\n6.Разбить лагерь");
            switch (desOnMainMap) {
                case 1:
                    if (map.isCellEmpty(mainHero.getX() - 1, mainHero.getY()))
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
                    currentMonster.levelUp(mainHero.getLevel());
                    battle(mainHero, currentMonster);
                    break;
                case 6:
                    setCamp();
                    break;
            }
            if (map.getObstValue(mainHero.getX(), mainHero.getY()) == 'S') {
                InGameShop.shopActionsByHero(mainHero);
            }
            map.updateMap(mainHero.getX(), mainHero.getY());
            map.showMap();


            if (Utils.rand.nextInt(100) < 2) {
                currentMonster = (Monster) monsterPattern[Utils.rand.nextInt(3)].clone();
                System.out.println("На вас внезапно напал " + currentMonster.getName());
                currentMonster.levelUp(mainHero.getLevel());
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


    public void selectHero() {
        //выбор героя
        String s = "Выберите героя:\n";
        for (int i = 0; i < 3; i++) {
            s += (i + 1) + ". " + heroPattern[i].getFullName() + "\n";
        }
        inpInt = Utils.getAction(1, 3, s);
        mainHero = (Hero) heroPattern[inpInt - 1].clone();
        System.out.println("Вы выбрали " + mainHero.getFullName());
    }

    public void battle(Hero battleHero, Monster battleMonster) {
        //вся магия здесь. UPD: Здесь всего лишь боевка. И то не вся
        currentRound = 1;
        System.out.println("Бой между игроком " + battleHero.getName() + " и монстром " + battleMonster.getName() + " начался!");
        do {
            //начало раунда
            System.out.println("Текущий раунд: " + currentRound);
            battleHero.showInfo();
            battleMonster.showInfo();
            //ход героя
            System.out.println("Ход игрока");
            battleHero.makeNewRound();
            inpInt = Utils.getAction(0, 3, "\n\n1. Атака\n2. Защита\n3. Открыть инвентарь\n0. Выйти из игры");
            //пропуск двух строк
            System.out.println("\n\n");
            //1.Атака
            if (inpInt == 1) {
                battleMonster.getDamage(battleHero.makeAttack()); //монстр получает урон
                if (!battleMonster.isAlive()) //жив ли монстр проверяем, ибо бесит меня, что он атакует после смерти
                {
                    System.out.println(battleMonster.getName() + " погиб");
                    battleHero.expGain(battleMonster.getHpMax() * 2); //экспы за монстра дают в размере двух его макс хп
                    battleHero.addKillCounter();
                    battleMonster.myInventory.transferAllItemsToAnotherInventoryWithTwentyPercentChance(battleHero.myInventory);
                    //currentMonster = (Monster)monsterPattern[Utils.rand.nextInt(3)].clone(); //сложносоставленная херня, которая бесконечно копирует монстров из патерна рандомным образом
                    break;
                    //System.out.println("На поле боя выходит " + currentMonster.getName()); //сообщение о выходе нового монстра
                }
            }
            //2. Защита
            if (inpInt == 2) {
                battleHero.setBlockStance();
            }
            if (inpInt == 3) {
                battleHero.myInventory.showAllItems();
                int invInput = Utils.getAction(0, battleHero.myInventory.getSize(), "\n\nВыберите предмет для использования");
                String usedItem = battleHero.myInventory.useItem(invInput);
                if (usedItem != "") {
                    System.out.println(battleHero.getName() + " использовал " + usedItem);
                    battleHero.useItem(usedItem);
                } else {
                    System.out.println(battleHero.getName() + " просто закрыл сумку");
                }
            }
            //0.Выйти из игры
            if (inpInt == 0) break;
            //ход монстра
            battleMonster.makeNewRound();
            if (Utils.rand.nextInt(100) < 80) {
                battleHero.getDamage(battleMonster.makeAttack());
            } else {
                battleMonster.setBlockStance();
            }
            //конец текущего раунда
            currentRound++;

        } while (true);
        //сообщение при победе или поражении
        if (battleMonster.isAlive() && battleHero.isAlive())
            System.out.println(battleHero.getName() + " сбежал с поля боя");
        if (!battleHero.isAlive()) System.out.println("Победил " + battleMonster.getName());
        if (!battleMonster.isAlive()) System.out.println("Победил " + battleHero.getName());
    }

    /*public void shopActions() {
        shop.showItems();
        System.out.println("0.Выход из магазина");
        int numOfBuyingItem = Utils.getAction(0, shop.itm.size(), "\n\nВыберите какой товар вы желаете приобрести: ");
        if (numOfBuyingItem == 0) return;
        shop.buyByHero(numOfBuyingItem - 1, mainHero);
        System.out.println("Желаете приобрести что-то еще?");
        shopActions();
    }*/

    //вся инфа по персонажам пока здесь
    private void initGame() {
        heroPattern[0] = new Hero("Knight", "Lancelot", 16, 10, 20);
        heroPattern[1] = new Hero("Barbarian", "Konan", 20, 5, 20);
        heroPattern[2] = new Hero("Dwarf", "Gimli", 18, 7, 20);

        monsterPattern[0] = new Monster("Humanoid", "Goblin", 8, 10, 6);
        monsterPattern[1] = new Monster("Humanoid", "Orc", 12, 6, 10);
        monsterPattern[2] = new Monster("Humanoid", "Troll", 15, 8, 8);

        currentRound = 1;
    }

    public void setCamp() {
        while (true) {
            int campActions = Utils.getAction(0, 5, "\n\nВы развели костер и разбили вокруг него лагерь. Чем вы хотите заняться?\n1.Поспать\n2.Покопаться в инвентаре\n3.Потренироваться в обращении с оружием\n4.Охотиться\n5.Готовить\n0.Собрать лагерь и отправиться в путь");
            switch (campActions) {
                case 1:
                    mainHero.fullHeal();
                    break;
                case 2:
                    mainHero.myInventory.showAllItems();
                    int invInput = Utils.getAction(0, mainHero.myInventory.getSize(), "\n\nСодержимое инвентаря:");
                    mainHero.myInventory.showAllItems();
                    String usedItem = mainHero.myInventory.useItem(invInput);
                    if (usedItem != "") {
                        System.out.println(mainHero.getName() + " использовал " + usedItem);
                        mainHero.useItem(usedItem);
                    } else {
                        System.out.println(mainHero.getName() + " просто закрыл сумку");
                    }
                    break;
                case 3:
                    System.out.println(Utils.answerForAllQuestions);
                    break;
                case 4:
                    mainHero.doHunting();
                    break;
                case 5:
                    mainHero.doCooking();
                    break;
                case 0:
                    System.out.println("Приключение героя продолжилось");
                    return;
            }
        }
    }
}