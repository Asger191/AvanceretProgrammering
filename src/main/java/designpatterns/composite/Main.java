package designpatterns.composite;

public class Main {
    public static void main(String[] args){
        Enemy enemy = new Enemy("Asger");
        Enemy enemy1 = new Enemy("Daniel");
        Enemy enemy2 = new Enemy("Signe");
        Enemy enemy3 = new Enemy("MAgnus");
        Enemy enemy4 = new Enemy("Anthon");
        Enemy enemy5 = new Enemy("Alex");
        Enemy enemy6 = new Enemy("Oscar");
        Enemy enemy7 = new Enemy("Mikkel");
        Enemy enemy8 = new Enemy("Styr");
        Enemy enemy9 = new Enemy("Malene");
        Enemy enemy10 = new Enemy("Emil");

        EnemyGroup enemyGroup = new EnemyGroup("The voids");
        EnemyGroup enemyGroup1 = new EnemyGroup("The raiders");
        EnemyGroup enemyGroup2 = new EnemyGroup("The flødeboller");

        enemyGroup.add(enemy);
        enemyGroup.add(enemy1);
        enemyGroup.add(enemy2);
        enemyGroup.add(enemy3);
        enemyGroup.add(enemy4);

        enemyGroup1.add(enemy5);
        enemyGroup1.add(enemy6);
        enemyGroup1.add(enemy7);

        enemyGroup2.add(enemy8);
        enemyGroup2.add(enemy9);
        enemyGroup2.add(enemy10);

        enemyGroup1.add(enemyGroup2);
        enemyGroup.add(enemyGroup1);

        enemyGroup.update();





    }
}
