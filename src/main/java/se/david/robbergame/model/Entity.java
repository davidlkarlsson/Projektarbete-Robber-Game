package se.david.robbergame.model;


public abstract class Entity {

    private int health;
    private int damage;
    private String role;


    public Entity(int health, int damage, String role) {
        this.health = health;
        this.damage = damage;
        this.role = role;

    }

    public int getHealth() {
        return health;
    }

    public int getDamage() {
        return damage;
    }

    public String getRole() {
        return role;
    }

    //minska entitys hälsa med värdet av parametern damage
    public void takeHit(int damage) {
        health -= damage;
        if (health < 0) {
            health = 0;
        }
    }

    //attackera det Creature-objekt som är parameter till denna metod
    public void punch(Entity toPunch) {
        toPunch.takeHit(this.damage);
    }

    //returnera sant om hälsan är över 0, returnera falskt annars
    public boolean isConscious() {
        return this.health > 0;
    }
}

