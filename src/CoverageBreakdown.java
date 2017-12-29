import java.util.ArrayList;
import java.util.Comparator;

/**
 * Created by Danny_Elliott on 2017-11-30.
 */
public class CoverageBreakdown {

    private ArrayList<Integer> vertexCoverage;
    private int firstVertex;

    public CoverageBreakdown(int firstvert, ArrayList breakdown){
        this.vertexCoverage = breakdown;
        this.firstVertex = firstvert;
    }

    public int getCoverageSize(){
        return vertexCoverage.size();
    }

    public int getFirstVertex(){
        return firstVertex;
    }

    public ArrayList<Integer> getCoverage(){
        return vertexCoverage;
    }

    public static Comparator<CoverageBreakdown> sortByCoverageSize(){
        int a = 2;
        Integer.toString(a);
        Comparator sizeCompare = new Comparator<CoverageBreakdown>() {
            @Override
            public int compare(CoverageBreakdown c1, CoverageBreakdown c2) {
                return Integer.toString(c1.getCoverageSize()).compareTo(Integer.toString(c2.getCoverageSize()));
            }
        };
        return sizeCompare;
    }

}
