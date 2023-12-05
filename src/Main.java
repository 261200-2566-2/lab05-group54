// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {

        noFirsthitBoots b1 = new noFirsthitBoots("Sturdy Boots","This Boots will prevent you from first hit damage one time");
        armorBoots b2 = new armorBoots("Sonic Boots","This Boot will decrease damage you took 15%");
        warRing r1 = new warRing("Gyro Ring","This Ring will increase your damage 10%");
        godRing r2 = new godRing("God Ring","This Ring will X2 user Random stat no mention max of it at the stat in time user use it.");

        Witcher w1 = new Witcher("Garault",1,100);
        Necromancer s1 = new Necromancer("Joe",1,100);
        Soldier s2 = new Soldier("Tankkk",1,100);
        Samurai sa1 = new Samurai("Ichiko",1,100);

        s1.equip(r2);
        s1.displayStat();
        s1.ghostRush(s2);
        s1.displayStat();

    }
}