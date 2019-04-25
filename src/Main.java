import java.util.ArrayList;

public class Main {
    public static void main(String[] arg)
    {
        Grath grath = new Grath("example.txt",0,6);
        ArrayList<Double> formula = new ArrayList<>();
        ArrayList<Double>  bruteForce  = new ArrayList<>();
        ArrayList<Double> moderation = new ArrayList<>();
        ArrayList<Double> moderationImproved = new ArrayList<>();
        for(int i = 0; i <= 10; i++) {
            formula.add(grath.getTeor(0.1 * i));
            bruteForce.add(grath.work(0.1 * i));
            moderation.add(grath.workLab2(0.1 * i, false, 0.01));
            moderationImproved.add(grath.workLab2(0.1 * i, true, 0.01));
        }
        Schedule.printS(formula,"formula");
        Schedule.printS(bruteForce,"bruteForce");
        Schedule.printS(moderation,"moderation");
        Schedule.printS(moderationImproved,"moderationImproved");
        Schedule.print(formula,bruteForce,moderation,moderationImproved,"Grath");
    }
}
