import java.util.ArrayList;

/**
 * Created by Danny_Elliott on 2017-11-26.
 */
public final class Graph {

    // edges are represented by the array index (the first vertex) and the stored value (the second vertex)
    protected ArrayList<Edge> edges = new ArrayList<Edge>();
    protected ArrayList<CoverageBreakdown> coverageGroups = new ArrayList<CoverageBreakdown>();

    // generate a graph with the desired number of edges
    public Graph(int edgeCount){
        if(edgeCount == 1){
            edges.add(new Edge(0, 1));
        }
        else if(edgeCount == 2){
            edges.add(new Edge(0, 1));
            edges.add(new Edge(1, 2));
        }
        else{
            for(int i=0; i < edgeCount; i++) {
                edges.add(new Edge(i, i));
            }
            for(int i=0; i < edgeCount; i++){
                Edge currentEdge = this.edges.get(i);
                while(currentEdge.vertexB == i){
                    // chose second vertex value between 0 and edgeCount and not equal to first vertex

                    int secondVertex = (int)(Math.random() * edgeCount);
                    // make sure an edge with the second vertex doesn't already exist
                    if(this.edges.get(secondVertex).vertexB != i){
                        currentEdge.setVertex(1, secondVertex);
                    }
                }
            }
        }
        this.coverPerVertex();
    }


    public Graph(int[] edgeArray){
        for(int i=0; i< edgeArray.length; i++){
            edges.add(new Edge(i, edgeArray[i]));
        }
        this.coverPerVertex();
    }


    public void printEdges(){
        String resultString = "";
        for(int i=0; i < edges.size(); i++){
            resultString += edges.get(i).vertexA + " => " + edges.get(i).vertexB + "\n";
        }
        System.out.println(resultString);
    }

    public void removeEdges(Edge inEdge){
        ArrayList<Edge> newEdges = new ArrayList<Edge>();
        int[] coveredVerts = {inEdge.vertexA, inEdge.vertexB};
        for(int i=0; i < this.edges.size(); i++){
            boolean shouldAdd = true;
            for(int x=0; x < coveredVerts.length; x++){
                if(this.edges.get(i).vertexA == coveredVerts[x] || this.edges.get(i).vertexB == coveredVerts[x]){
                    shouldAdd = false;
                    continue;
                }
            }
            if(shouldAdd){
                newEdges.add(this.edges.get(i));;
            }
        }
        this.edges = newEdges;
    }

    protected void coverPerVertex(){
        this.coverageGroups.clear();
        ArrayList<Edge> edges = (ArrayList<Edge>)this.edges.clone();
        int edgeCount = edges.size();

        // Each array value represents an edge's vertex cover grouping
        for(int i=0; i < edgeCount; i++){
            // Each array value stores the vertices covered by the first vertex of the edge
            ArrayList<Integer> coverList = new ArrayList<Integer>();

            coverList.add(edges.get(i).vertexA);
            coverList.add(edges.get(i).vertexB);

            // find any incident edges and add unique vertices to the set
            for(int x=0; x < edgeCount; x++){
                // skip if same edge, find edge that points to the first vertex
                if(x != i && edges.get(x).vertexB == i){
                    // add index of found edge
                    coverList.add(x);
                }
            }
            this.coverageGroups.add(new CoverageBreakdown(coverList.get(0), coverList));
        }
    }


    protected void printCoverGroupings(){
        ArrayList<CoverageBreakdown> coverBreakdown = this.coverageGroups;
        for(int i=0; i < coverBreakdown.size(); i++){
            System.out.println(coverBreakdown.get(i).getCoverage());
        }
    }

}
