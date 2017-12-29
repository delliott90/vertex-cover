/**
 * Created by Danny_Elliott on 2017-12-01.
 */
public class Edge {

    protected int vertexA;
    protected int vertexB;

    public Edge(int vertA, int vertB){
        this.vertexA = vertA;
        this.vertexB = vertB;
    }

    public void setVertex(int index, int value){
        if(index == 0){
            this.vertexA = value;
        }
        else{
            this.vertexB = value;
        }

    }

}
