package ro;

import java.io.*;
import java.util.*;

import java.util.ArrayList;
import java.util.Arrays;

public class BellmanFord {
    
    public BellmanFord(){
        
    }

    class DWEdge{
        int from, to;
        double weight;
        
        public double getWeight(int from,int to){
            if (this.from == from && this.to == to) {
                return this.weight;
            }
            return 0;
        }

        DWEdge(int from, int to, double weight){
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }   
    
    private static ArrayList<ArrayList<DWEdge>> graph;
    private int edgeTo[];
    private double distTo[];
    private int V;
    
    BellmanFord(int V){
        graph = new ArrayList<>();
        edgeTo = new int[V];
        distTo = new double[V]; Arrays.fill(distTo, Double.POSITIVE_INFINITY);
        this.V = V;
        
        graph.ensureCapacity(V);
        for(int i=0;i<V;++i)graph.add(new ArrayList<>());
    }
    
    public void addEdge(int from, int to, double weight){
        graph.get(from).add(new DWEdge(from, to, weight));
    }
    
    public void relax(DWEdge e){  
        if(distTo[e.to] > distTo[e.from] + e.weight){ 
            distTo[e.to] = distTo[e.from] + e.weight; 
            edgeTo[e.to] = e.from; 
        }
    }
      
    public void BellmanFord(int source){
        distTo[source] = 0;
        
        for(int i=0;i<V-1;++i){ 
            for(int j=0;j<V;++j){
                for(DWEdge e:graph.get(j)){
                    relax(e);
                }
            }
        }
    }
    
    
    public static void main(String[] args){
        int i,j;
        

        System.out.println("Number of vertex");
        Scanner scanner = new Scanner(System.in);
        int nbrVertex = scanner.nextInt();
        BellmanFord bf = new BellmanFord();

        while (nbrVertex <3 ) {
            System.out.println("Number of vertex");
            scanner = new Scanner(System.in);
            nbrVertex = scanner.nextInt();
        }
        bf = new BellmanFord(nbrVertex);            

        for (i = 0; i < nbrVertex; i++) {
            for (j = 0; j < nbrVertex; j++) {
                if (i!=j) {
                    System.out.println("Distance between "+i+" and "+j);
                    int arc = scanner.nextInt();
                    if (arc != 0) {
                        bf.addEdge(i, j, arc);
                    }       
                }
                                         
            }
        }
        
              
        System.out.println("The vertex source");
        scanner = new Scanner(System.in);
        int source = scanner.nextInt();
        bf.BellmanFord(source);
        
        System.out.println("Results : ");
        for(i=0;i<bf.V;++i){
            if(i==source)continue;
            System.out.println("Distance from "+source+" to " +i+ ": "+bf.distTo[i]+ " and his predecessor :"+bf.edgeTo[i]);
        }
    }
}