import java.util.*;

public class Grath {
    ArrayList<int []> connections;
    int start;
    int finish;
    ArrayList<ArrayList<Integer>> list;
    double probability;
    int verticesSize;
    int ribsSize;
    int lMin = 0;
    int lMax = 0;
    int tmpss= 0;

    public Grath(String fileName, int s, int f)
    {
       list = ReadGrath.read(fileName);
       verticesSize = list.size();
       start = s;
       finish = f;
       connections = getRibs(list);
       ribsSize = connections.size();
       lMin = 3;
       lMax = 10;
    }
    public double work(double p)
    {
        probability = p;
        ArrayList<int []> ribs;
        ArrayList<Integer> result = new ArrayList<>();
       for(int i = 1; i <= Math.pow(2,ribsSize); i++)
       {
            ribs = getCombination(i);
            if(isWay(getMatrix(ribs)))
            {
                result.add(ribs.size());
            }
        }
        double res = 0;
        for(int i = 0; i < result.size(); i++)
        {
            double tmp = 1.0;
            for(int j = 1; j <= result.get(i); j++)
            {
                tmp *= probability;
            }
            for(int z = result.get(i) + 1; z <= ribsSize; z++)
            {
                tmp *= (1.0 - probability);
            }
            res += tmp;
        }
        return res;
    }
    private ArrayList<ArrayList<Integer>> getMatrix(ArrayList<int []> ribs)
    {
        ArrayList<ArrayList<Integer>> matrix = new ArrayList<>();
        for(int i = 0; i < verticesSize; i++)
        {
            matrix.add(new ArrayList<>());
        }
        for (int i = 0; i < ribs.size(); i++)
        {
            int[]tmp = ribs.get(i);
            matrix.get(tmp[0]).add(tmp[1]);
            matrix.get(tmp[1]).add(tmp[0]);
        }
        return matrix;
    }
    private ArrayList<int []> getRibs(ArrayList<ArrayList<Integer>> arrayList)
    {
        ArrayList<int[]> ribs = new ArrayList<>();
        int[] tmp;
        ArrayList<Integer> listTmp;
        for (int i = 0; i < arrayList.size(); i++)
        {

            listTmp = arrayList.get(i);
            for (int j = 0; j < listTmp.size(); j++) {
                tmp = new int[2];
                tmp[0] = i;
                tmp[1] = listTmp.get(j);
                if(check(tmp,ribs))
                {
                    ribs.add(tmp);
                }
        }
    }
    return ribs;
    }
    private  boolean check(int[] mas,ArrayList<int []> ribs)
    {
        int[] tmp;
        for(int i = 0; i < ribs.size(); i++)
        {
            tmp = ribs.get(i);
            if((mas[0] == tmp[0] && mas[1] == tmp[1])||(mas[0] == tmp[1] && mas[1] == tmp[0]))
            return  false;
        }
        return true;
    }
    private boolean isWay(ArrayList<ArrayList<Integer>> matrix)
    {
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        queue.push(start);
        boolean[] used = new boolean [verticesSize];
        Arrays.fill(used,false);
        used[start] = true;
        while (queue.size() != 0)
        {
            int v = queue.pollFirst();
            for(int i = 0; i < matrix.get(v).size(); i++)
            {
                if(!used[matrix.get(v).get(i)])
                {
                    queue.push(matrix.get(v).get(i));
                    used[matrix.get(v).get(i)] = true;
                    if(used[finish])
                    {
                        return true;
                    }
                }
            }
        }
        if(used[finish])
            return true;
        else
            return false;
    }
    private ArrayList<int []> getCombination(int m)
    {
        ArrayList<int []> res = new ArrayList<>();
        for(int i = 0;i < ribsSize; i++) {
            if ((m &(int)Math.pow(2,i)) == (int)Math.pow(2,i)) {
                res.add(connections.get(i));
            }
        }
        return res;
    }
    public double getTeor(double probability)
    {
        double result = Math.pow(probability,3.0) + 3.0 * Math.pow(probability,4.0) + 3.0 * Math.pow(probability,5.0) -
                5.0 * Math.pow(probability,6.0) - 11.0 * Math.pow(probability,7.0) + 5.0 * Math.pow(probability,8.0)+
                17.0 * Math.pow(probability,9.0) - 16.0 * Math.pow(probability,10.0) + 4.0 * Math.pow(probability,11.0);
        return result;
    }
    public double workLab2(double p,boolean upgrade, double accuracy) {
        double n = 2.25/Math.pow(accuracy,2.0);
        double count = 0;
        tmpss = 0;
        for (int i = 0; i < (int)n; i++)
        {
            int r = getNumber(p);
            count += upgrade(r,upgrade);
        }
        if(upgrade)
        System.out.println("n/N   " + n/(n - tmpss));
        return count/n;

    }
    private double upgrade(int num,boolean flag)
    {
        ArrayList<int []> ribs;
        ribs = getCombination(num);
        if(flag)
        {
            if(lMin >= ribs.size()) {
                tmpss++;
                return 0.0;
            }
            if(lMax <= ribs.size()) {
                tmpss++;
                return 1.0;
            }
        }
        if(isWay(getMatrix(ribs)))
        {
            return 1.0;
        }
        else
            return 0.0;
    }
    private int getNumber(double probability)
    {
        StringBuilder str = new StringBuilder();
        for(int i = 0; i < ribsSize; i++)
        {
            if(Math.random() < probability)
                str.append("1");
            else
                str.append("0");
        }
        return Integer.parseInt(str.toString(),2);
    }


    }
