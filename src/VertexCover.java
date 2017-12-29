import java.util.ArrayList;

/**
 * Created by Danny_Elliott on 2017-12-02.
 */
public class VertexCover {

    private static int edgeCount;
    private static int vertexCoverSize;

    public static void main(String[] args) {

        edgeCount = Integer.parseInt(args[0]);
        vertexCoverSize = Integer.parseInt(args[1]);

        double edgePower = Math.pow(edgeCount, edgeCount/2);
        double iterations = Math.round(edgePower);
        Graph myGraph = new Graph(edgeCount);

        int graphSize = myGraph.edges.size();
        int[] edgeEnds = new int[graphSize];
        for(int i = 0; i < graphSize; i++){
            edgeEnds[i] = myGraph.edges.get(i).vertexB;
        }

        System.out.println("Graph Edges:");
        myGraph.printEdges();

        System.out.println("Order of coverings:");
        myGraph.printCoverGroupings();
        System.out.println("");

        Graph bruteGraph = new Graph(edgeEnds);
        Coverage coverage01 = new Coverage(bruteGraph);
        Timer bruteTimer = new Timer();
        bruteTimer.start();
        ArrayList<Integer> coverageResults01 = coverage01.iterativeBruteForceVertexCover(iterations);
        long bruteTime = bruteTimer.end();
        System.out.println("Best Brute Force Coverage after " + iterations + " iterations: " + coverageResults01);
        System.out.println("Found in " + bruteTime + "ms");
        boolean kOrLess01 = coverageResults01.size() <= vertexCoverSize;
        if(kOrLess01){
            System.out.println("Brute Force Algorithm was able to find coverage of " + vertexCoverSize + " or less veritices");
        }
        else{
            System.out.println("Brute Force Algorithm was not able to find coverage of " + vertexCoverSize + " or less veritices");
        }
        System.out.println("");

        Graph greedyGraph = new Graph(edgeEnds);
        Coverage coverage02 = new Coverage(greedyGraph);
        Timer greedyTimer = new Timer();
        greedyTimer.start();
        ArrayList<Integer> coverageResults02 = coverage02.greedyVertexCover();
        long greedyTime = greedyTimer.end();
        System.out.println("Best Greedy Coverage: " + coverageResults02);
        System.out.println("Found in " + greedyTime + "ms");
        boolean kOrLess02 = coverageResults02.size() <= vertexCoverSize;
        if(kOrLess02){
            System.out.println("Greedy Algorithm was able to find coverage of " + vertexCoverSize + " or less veritices");
        }
        else{
            System.out.println("Greedy Algorithm was not able to find coverage of " + vertexCoverSize + " or less veritices");
        }
        System.out.println("");

        Graph approxGraph = new Graph(edgeEnds);
        Coverage coverage03 = new Coverage(approxGraph);
        Timer approxTimer = new Timer();
        approxTimer.start();
        ArrayList<Integer> coverageResults03 = coverage03.approxVertexCover();
        long approxTime = approxTimer.end();
        System.out.println("Best Approximate Coverage:" + coverageResults03);
        System.out.println("Found in " + approxTime + "ms");
        boolean kOrLess03 = coverageResults03.size()/2 <= vertexCoverSize;
        if(kOrLess03){
            System.out.println("Approximation Algorithm was able to find coverage of " + vertexCoverSize + " or less veritices");
        }
        else{
            System.out.println("Approximation Algorithm was not able to find coverage of " + vertexCoverSize + " or less veritices");
        }
        System.out.println("");

    }

}
