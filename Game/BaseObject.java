package Game;

public class BaseObject {
    /*
     * 플레이어와 보스의 기본적인 기능을 구현한 클래스,
     * 모든 보스가 이 클래스를 상속해서 배열로 묶기 쉽게 만들었다.
     */
    public int maxHealth;
    public int health;

    public int shield; // 캐릭터의 방어막

    public Status status; // 캐릭터의 상태(힘,취약 등등의 스탯이 담겨있다.)

    public void damage(int amount) {
        /*
         * 객체의 체력을 깎는 함수
         * 방어막 수치가 있으면 방어막 먼저 깎고 남은 수치만큼 체력을 깎는다.
         */

        // amount += status.weakness.power;
        // 취약의 수치만큼 데미지 증가

        if (shield > 0) {
            if (shield >= amount) {
                shield -= amount;
                return;
            } else {
                amount -= shield;
                health -= amount;
                return;
            }
        } else {
            health -= amount;
        }
    }

    public void addShield(int amount) {
        shield += amount;
    }

    public void addHealth(int amount) {
        health = Math.min(maxHealth, health + amount);
    }

    public int getHealth() {
        return health;
    }

    public int getMaxHealth() {
        return maxHealth;
    }
}
