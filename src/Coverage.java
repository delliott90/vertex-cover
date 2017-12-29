import java.util.*;

/**
 * Created by Danny_Elliott on 2017-11-29.
 */
public class Coverage {

    private Graph inGraph;
    protected Coverage(Graph theGraph){
        inGraph = theGraph;
    }

    protected ArrayList<Integer> iterativeBruteForceVertexCover(double iterations){
        // Initial ordering of visited cover breakdowns
        ArrayList<CoverageBreakdown> coverBreakdown = inGraph.coverageGroups;
        // Clone initial order so it can be shuffled
        ArrayList<CoverageBreakdown> tempCoverBreakdown = (ArrayList<CoverageBreakdown>)coverBreakdown.clone();
        int edgeCount = inGraph.edges.size();
        int bestSize = edgeCount;
        ArrayList<Integer> edgeList = new ArrayList<Integer>();
        ArrayList<Integer> tempBestCover = new ArrayList<Integer>();
        ArrayList<Integer> bestCover = new ArrayList<Integer>();

        if(inGraph.edges.size() == 1 || inGraph.edges.size() == 2){
            bestCover.add(1);
            return bestCover;
        }

        for(int k=0; k < iterations; k++){
            for(int i=0; i < edgeCount; i++){
                scanVertexCoverage(edgeList, tempBestCover, tempCoverBreakdown, i);
            }
            if(tempBestCover.size() < bestSize){
                bestSize = tempBestCover.size();
                bestCover = (ArrayList<Integer>)tempBestCover.clone();
            }
            // Shuffle collection to find the best cover
            Collections.shuffle(tempCoverBreakdown);
            tempBestCover.clear();
            edgeList.clear();
        }
        return bestCover;
    }

    protected ArrayList<Integer> greedyVertexCover(){
        ArrayList<CoverageBreakdown> coverBreakdown = inGraph.coverageGroups;
        int edgeCount = inGraph.edges.size();
        ArrayList<Integer> edgeList = new ArrayList<Integer>();
        ArrayList<Integer> bestCover = new ArrayList<Integer>();

        if(inGraph.edges.size() == 1 || inGraph.edges.size() == 2){
            bestCover.add(1);
            return bestCover;
        }

        // Sort coverings by largest to smallest sub-arrays
        coverBreakdown = sortBreakdown(coverBreakdown, edgeList);

        while(edgeList.size() < edgeCount){
            scanVertexCoverage(edgeList, bestCover, coverBreakdown, 0);
            coverBreakdown = sortBreakdown(coverBreakdown, edgeList);
        }
        return bestCover;
    }

    private void scanVertexCoverage(ArrayList<Integer> edgeList, ArrayList<Integer> bestCover, ArrayList<CoverageBreakdown> vertexCoverBreakdown, int coverageGroupIndex){
        int i = coverageGroupIndex;
        // check if there are any new edges in coverBreakdown[i]
        // if so, add the firstVertex to bestCover array and current vertex to edgeList
        for(int x=0; x < vertexCoverBreakdown.get(i).getCoverageSize(); x++){
            int currentVertex = vertexCoverBreakdown.get(i).getCoverage().get(x);
            boolean existing = edgeList.contains(currentVertex);
            // if vertex already exists in the edgeList, first vertex has already been added to bestCover
            if(!existing){
                edgeList.add(currentVertex);
                // bestCover will only contain the first vertex of an edge
                int firstVertex = vertexCoverBreakdown.get(i).getFirstVertex();
                if(!bestCover.contains(firstVertex)){
//                    System.out.println("adding " + firstVertex + " to besties");
                    bestCover.add(firstVertex);
                }
            }

        }
    }

    protected ArrayList<Integer> approxVertexCover(){
        ArrayList<Integer> bestCover = new ArrayList<Integer>();

        if(inGraph.edges.size() == 1 || inGraph.edges.size() == 2){
            bestCover.add(0);
            bestCover.add(1);
            return bestCover;
        }

        while(inGraph.edges.size() > 0){
            Edge firstEdge = inGraph.edges.get(0);
            int firstVertex = firstEdge.vertexA;
            int secondVertex = firstEdge.vertexB;
            if(!bestCover.contains(firstVertex)){
                bestCover.add(firstVertex);
            }
            if(!bestCover.contains(secondVertex)){
                bestCover.add(secondVertex);
            }
            inGraph.removeEdges(firstEdge);
        }
        return bestCover;
    }

    private ArrayList<CoverageBreakdown> sortBreakdown(ArrayList<CoverageBreakdown> coverBreakdown, ArrayList<Integer> edgeList){
        // for each array in the coverBreakdown
        for(int i=0; i < coverBreakdown.size(); i++){
            // for each vertex in the edgeList
            for(int x=0; x < edgeList.size(); x++){
                coverBreakdown.get(i).getCoverage().remove(new Integer(edgeList.get(x)));
            }
        }
        Collections.sort(coverBreakdown, CoverageBreakdown.sortByCoverageSize());
        Collections.reverse(coverBreakdown);
//        System.out.println("Sorted Groupings:");
//        inGraph.printCoverGroupings();
        return coverBreakdown;
    }

}
