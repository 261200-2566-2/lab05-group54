import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public interface Characters {
    void displayStat();
    void displayAllInventory();
    void levelUp();
    void takeDamage(double damage);
    boolean hasNoFirsthitBoots();
    boolean hasArmorBoots();
    boolean hasWarRing();
    boolean hasGodRing();
    void equip(Accessories accessory);
    void unequip(Accessories accessory);
    void attack(RPGCharacter target);
    String getJobClass();
    String getName();
    void setDamage(double d);
    void setHp(double h);
    void setMoveSpeed(double s);
    double getDamage();
    double getHp();
    double getMoveSpeed();
    void setLevel(int l);
    int getLevel();
    void setMaxHp(double h);
    double getMaxHp();
    void setMaxMana(double m);
    double getMaxMana();
    void setMana(double m);
    double getMana();
    double getBasedamage();
    void setJob(String s);

}

interface Magician extends Characters{
    void fireball(RPGCharacter target);
}

interface Warriror extends Characters{
    void sword_dance();
}

interface Necromancer_0 extends Magician{
    void equipNoOnehitkill();
    void ghostRush(RPGCharacter target);
}

interface Witcher_0 extends Magician,Warriror {
    void MagicBlade();
}

interface Samurai_0 extends Warriror{
    void highDamage_attack(RPGCharacter target);
}

class RPGCharacter implements Characters {
    private String name;
    private int level;
    private double hp;
    private double mana;
    private double max_hp;
    private double max_mana;
    private double moveSpeed;
    private String Job;
    private List<Accessories> inventory;
    private double damage;
    private int tempNUM;
    private double base_damage;

    public RPGCharacter(String name ,int level ,double r) {
        this.name = name;
        this.level = level;
        this.moveSpeed = r;
        this.hp = 100 + (10 * level);
        this.mana = 50 + (2 * level);
        this.max_hp = 100 + (10 * level);
        this.max_mana = 50 + (2 * level);
        this.inventory = new ArrayList<>();
        this.base_damage = 20;
        this.damage = base_damage * (1+0.1*level);
        this.Job = "Jobless";
        this.tempNUM = 0;
    }


    public void displayStat() {
        System.out.println(name + " - Level: " + level + ", Job: " + this.getJobClass() + ", HP: " + hp + "/" + max_hp +
                ", Mana: " + mana + "/" + max_mana + ", moveSpeed: " + moveSpeed + ", Damage: " + damage);
    }

    public void displayAllInventory() {
        System.out.println("Inventory for " + name + ":");
        for (Accessories item : inventory) {
            System.out.println("- " + item.getName() + ": " + item.getDescription());
        }
        System.out.println("End of inventory.");
    }

    public void levelUp() {
        double diff_maxHp = (100 + (10 * level)) - (100 + (10 * (level - 1)));
        double diff_maxMana = (50 + (2 * level)) - (50 + (2 * (level - 1)));
        this.hp = Math.min(this.hp + diff_maxHp, this.max_hp);//heal hp when level up
        this.mana = Math.min(this.mana + diff_maxMana, this.max_mana);//heal mana when level up
        for (Accessories acType : inventory) {
            if (acType instanceof Ringclass) {
                Ringclass ring = (Ringclass) acType;
                this.mana = mana + ring.manaIncrease();

            }
            if (acType instanceof Bootsclass) {
                Bootsclass boots = (Bootsclass) acType;
                this.moveSpeed = moveSpeed + boots.moveSpeedIncrease();

            }
        }
        if (hasGodRing()) {
            for (Accessories item : inventory) {
                if (item instanceof godRing) {
                    godRing GodRing = (godRing) item;
                    switch (tempNUM) {
                        case 1:
                            damage *= GodRing.multipleOneStat();
                            break;
                        case 2:
                            hp *= GodRing.multipleOneStat();
                            break;
                        default:
                            mana *= GodRing.multipleOneStat();
                    }
                }

            }
        }


        System.out.println("---------------------------");
        System.out.println(name + " Level up to " + level + "!!");
        System.out.println("maxHp up to " + max_hp);
        System.out.println("maxMana up to " + max_mana);
        System.out.println("damage up to " + damage);
        System.out.println("---------------------------");
    }

    public void takeDamage(double damage) {
        if (hasNoFirsthitBoots()) {
            for (Accessories item : inventory) {
                if (item instanceof noFirsthitBoots) {
                    noFirsthitBoots nofirsthitBoots = (noFirsthitBoots) item;
                    if (nofirsthitBoots.noOnehit_kill()) {
                        damage = 0;
                    }
                }
            }
        }

        if (hasArmorBoots()) {
            for (Accessories item : inventory) {
                if (item instanceof armorBoots) {
                    armorBoots ArmorBoots = (armorBoots) item;
                    damage = damage - (damage * ArmorBoots.DecreaseDamagePercent()/100);
                }
            }
        }

        if (hasWarRing()) {
            for (Accessories item : inventory) {
                if (item instanceof warRing) {
                    warRing WarRing = (warRing) item;
                    damage = damage + (damage * WarRing.DamageIncreasePercent()/100);
                }
            }
        }
        hp -= damage;
        if (hp <= 0) {
            System.out.println(name + " took " + damage + " and has been defeated!");
        } else {
            System.out.println(name + " took " + damage + " damage. Remaining health: " + hp);
        }
    }

    public boolean hasNoFirsthitBoots() {
        for (Accessories item : inventory) {
            if (item instanceof noFirsthitBoots) {
                return true;
            }
        }
        return false;
    }

    public boolean hasArmorBoots() {
        for (Accessories item : inventory) {
            if (item instanceof armorBoots) {
                return true;
            }
        }
        return false;
    }
    public boolean hasWarRing() {
        for (Accessories item : inventory) {
            if (item instanceof warRing) {
                return true;
            }
        }
        return false;
    }
    public boolean hasGodRing() {
        for (Accessories item : inventory) {
            if (item instanceof godRing) {
                return true;
            }
        }
        return false;
    }

    public void equip(Accessories accessory) {
        inventory.add(accessory);
        System.out.println(name + " equipped " + accessory.getName());
        for (Accessories acType : inventory) {
            if (acType instanceof Ringclass) {
                Ringclass ring = (Ringclass) acType;
                this.mana = mana + ring.manaIncrease();

            }
            if (acType instanceof Bootsclass) {
                Bootsclass boots = (Bootsclass) acType;
                this.moveSpeed = moveSpeed + boots.moveSpeedIncrease();

            }
        }
        if (hasGodRing()) {
            for (Accessories item : inventory) {
                if (item instanceof godRing) {
                    godRing GodRing = (godRing) item;
                    switch (GodRing.RandomNumber()) {
                        case 1:
                            damage *= GodRing.multipleOneStat();
                            System.out.println(GodRing.getName() + " Bless Damage!!");
                            this.tempNUM = 1;
                            break;
                        case 2:
                            hp *= GodRing.multipleOneStat();
                            System.out.println(GodRing.getName() + " Bless HP!!");
                            this.tempNUM = 2;
                            break;
                        default:
                            mana *= GodRing.multipleOneStat();
                            System.out.println(GodRing.getName() + " Bless Mana!!");
                            this.tempNUM = 3;
                    }
                }

            }
        }

    }

    // Operation 5: Attack
    public void attack(RPGCharacter target) {
        System.out.println(name + " attacks " + target.name + " !!!");
        target.takeDamage(this.damage);
    }

    public String getJobClass() {
        return Job;
    }

    public void unequip(Accessories accessory) {
        if (inventory.contains(accessory)) {
            inventory.remove(accessory);
            if (accessory instanceof godRing) {
                godRing GodRing = (godRing) accessory;
                switch (tempNUM) {
                    case 1:
                        damage /= GodRing.multipleOneStat();
                        break;
                    case 2:
                        hp /= GodRing.multipleOneStat();
                        break;
                    default:
                        mana /= GodRing.multipleOneStat();
                }
            }
            if (accessory instanceof Ringclass) {
                Ringclass ring = (Ringclass) accessory;
                this.mana = mana - ring.manaIncrease();

            }
            if (accessory instanceof Bootsclass) {
                Bootsclass boots = (Bootsclass) accessory;
                this.moveSpeed = moveSpeed - boots.moveSpeedIncrease();

            }

            System.out.println(name + " unequipped " + accessory.getName());
        } else {
            System.out.println(name + " does not have " + accessory.getName() + " in the inventory.");
        }
    }

    @Override
    public String getName() {
        return name;
    }

    public void setDamage(double d) {this.damage = d;}
    public void setHp(double h) {this.hp = h;}
    public void setMoveSpeed(double s) {this.moveSpeed = s;}

    @Override
    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public double getDamage() {
        return damage;
    }

    @Override
    public double getHp() {
        return hp;
    }

    @Override
    public double getMoveSpeed() {
        return moveSpeed;
    }

    @Override
    public int getLevel() {
        return level;
    }

    @Override
    public double getMaxHp() {
        return max_hp;
    }

    @Override
    public double getMaxMana() {
        return max_mana;
    }

    @Override
    public void setMaxHp(double h) {
        this.max_hp = h;
    }

    @Override
    public void setMaxMana(double m) {
        this.max_mana = m;
    }

    public double getMana() {
        return mana;
    }

    @Override
    public void setMana(double mana) {
        this.mana = mana;
    }

    @Override
    public double getBasedamage() {
        return base_damage;
    }

    @Override
    public void setJob(String s) {
        this.Job = s;
    }
}

class Mage extends RPGCharacter implements Magician {
    public Mage(String name,int level,double movespeed) {
        super(name,level,movespeed);
        this.setHp(80 + (2 * this.getLevel()));
        this.setMana(100 + (2 * getLevel()));
        this.setMaxHp(getHp());
        this.setMaxMana(getMana());
        this.setDamage(getBasedamage() * (1+0.3*getLevel()));
        this.setJob("Magician");

    }
    @Override
    public void fireball(RPGCharacter target) {
        if(this.getMana() - 50 >= 0) {
            this.setDamage(50 + this.getDamage());
            System.out.println(this.getName() + " use fireball \uD83D\uDD25 !!");
            target.takeDamage(getDamage());
            this.setDamage(this.getDamage() - 50);
            this.setMana(getMana() - 50);
        }else{
            System.out.println(this.getName() + " can't use skill");
        }

    }

    @Override
    public void levelUp() {
        this.setLevel(this.getLevel() + 1);
        this.setMaxHp(80 + (2 * this.getLevel()));
        this.setMaxMana(100 + (2 * getLevel()));
        double diff_maxHp = (80 + (2 * getLevel())) - (80 + (2 * (getLevel() - 1)));
        double diff_maxMana = (100 + (2 * getLevel())) - (100 + (2 * (getLevel() - 1)));
        this.setHp(Math.min(this.getHp() + diff_maxHp, this.getMaxHp()));
        this.setMana(Math.min(this.getMana() + diff_maxMana, this.getMaxMana()));
        this.setDamage(getBasedamage() * (1+0.3*getLevel()));
        super.levelUp();
    }
}

class Soldier extends RPGCharacter implements Warriror {
    public Soldier(String name,int level,double movespeed) {
        super(name,level,movespeed);
        this.setHp(100 + (3 * this.getLevel()));
        this.setMana(50 + (2 * getLevel()));
        this.setMaxHp(getHp());
        this.setMaxMana(getMana());
        this.setDamage(getBasedamage() * (1 + 0.05*getLevel()));
        this.setJob("Magician");

    }

    @Override
    public void sword_dance() {
        if(this.getMana() - 25 >= 0) {
            System.out.println(this.getName() + " use sword dance ⚔");
            System.out.println("HP increase 75%");
            System.out.println("Damage X1.5 !!");
            setDamage(getDamage() * 1.75);
            setHp(getHp() + 50*getMaxHp()/100);
            setMana(getMana() - 25);
        }else System.out.println(this.getName() + " can't use skill");

    }

    @Override
    public void levelUp() {
        this.setLevel(this.getLevel() + 1);
        this.setMaxHp(100 + (3 * this.getLevel()));
        this.setMaxMana(50 + (2 * getLevel()));
        double diff_maxHp = (100 + (3 * this.getLevel())) - (100 + (3 * (getLevel() - 1)));
        double diff_maxMana = (50 + (2 * getLevel())) - (50 + (2 * (getLevel() - 1)));
        this.setHp(Math.min(this.getHp() + diff_maxHp, this.getMaxHp()));
        this.setMana(Math.min(this.getMana() + diff_maxMana, this.getMaxMana()));
        this.setDamage(getBasedamage() * (1+0.05*getLevel()));
        super.levelUp();
    }
}

class Ghost {
    private String name;
    private double damage;
    private double hp;

    public Ghost() {
        this.name = "Ghost";
        this.damage = 30;
        this.hp = 1;
        System.out.println("\uD83D\uDC80");
    }

    public double getDamage() {
        Random rand = new Random();
        int n1 = rand.nextInt(3) + 1;
        switch (n1) {
            case 1:
                System.out.println("แกไม่รอดแน่!!");
                break;
            case 2:
                System.out.println("ผีหลอกวิญญาณหลอน~~");
                break;
            default:
                System.out.println("ตามติดไปทุกที่!!");
        }
        return damage;
    }

    public String getName() {
        return name;
    }

    public double getHp() {
        return hp;
    }
}



class Necromancer extends Mage implements Necromancer_0 {
    public Necromancer(String name,int level,double movespeed) {
        super(name,level,movespeed);
    }

    @Override
    public void equipNoOnehitkill() {
        this.equip(new noFirsthitBoots("Sorcerer Boots","avoid next damage"));
    }

    public void ghostRush(RPGCharacter target) {
        if(this.getMana() - 50 >= 0 && this.getHp() - 10 >= 0) {
            System.out.println(this.getName() + " summon 3 ghost.");
            Ghost g1 = new Ghost();
            Ghost g2 = new Ghost();
            Ghost g3 = new Ghost();
            target.takeDamage(g1.getDamage() + 10 * getDamage()/100);
            target.takeDamage(g2.getDamage() + 10 * getDamage()/100);
            target.takeDamage(g3.getDamage() + 10 * getDamage()/100);
            this.setMana(getMana() - 50);
            this.setHp(getHp() - 10);
        }else{
            System.out.println(this.getName() + " can't use skill");
        }

    }
}

class Witcher extends Soldier implements Witcher_0 {
    public Witcher(String name,int level,double movespeed) {
        super(name,level,movespeed);
    }

    @Override
    public void MagicBlade() {
        if(this.getMana() - 50 >= 0) {
            this.sword_dance();
            System.out.println(this.getName() + " Enchance to MagicBlade \uD83C\uDF87 !!");
            this.setDamage(getDamage() + getMaxMana()*50/100);
            System.out.println(this.getName() + " obtains bonus damage");
            this.setMana(getMana() - 25);
        }else{
            System.out.println(this.getName() + " can't use skill");
        }
    }

    @Override
    public void fireball(RPGCharacter target) {

        if(this.getMana() - 50 >= 0) {
            this.setDamage(50 + this.getDamage());
            System.out.println(this.getName() + " use fireball \uD83D\uDD25 !!");
            target.takeDamage(getDamage());
            this.setDamage(this.getDamage() - 50);
            this.setMana(getMana() - 50);
        }else{
            System.out.println(this.getName() + " can't use skill");
        }
    }
}

class Samurai extends Soldier implements Samurai_0 {
    public Samurai(String name,int level,double movespeed) {
        super(name,level,movespeed);
    }

    @Override
    public void highDamage_attack(RPGCharacter target) {
        if(this.getHp() - 50*getMaxHp()/100 > 0) {
            System.out.println(this.getName() + " use Getsuga Tenshō 1ﾒ");
            target.takeDamage(25*getMaxHp()/100 + this.getDamage() + 50*getMaxMana()/100 + this.getLevel() * 1.25 + 5);

            this.setHp(this.getHp() - 50*getMaxHp()/100);
        }else{
            System.out.println(this.getName() + " can't use skill");
        }
    }
}