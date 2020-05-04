package com.company;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class Main {

    public static void main(String[] args) {
	    /*SimpleGraph sg = new SimpleGraph(17);
        for (int i = 0; i < 17; i++) {
            sg.AddVertex(i);
        }
        sg.AddEdge(0,1);
        sg.AddEdge(3,1);
        sg.AddEdge(2,3);
        sg.AddEdge(3,4);
        sg.AddEdge(4,7);
        sg.AddEdge(10,7);
        sg.AddEdge(10,9);
        sg.AddEdge(3,5);
        sg.AddEdge(5,6);
        sg.AddEdge(6,8);
        sg.AddEdge(5,10);
        sg.AddEdge(2,8);
        sg.AddEdge(12,10);
        sg.AddEdge(12,11);
        sg.AddEdge(12,13);
        sg.AddEdge(14,15);
        sg.AddEdge(15,16);
        //for triangles
        sg.AddEdge(5,8);
        sg.AddEdge(1,2);
        sg.AddEdge(14,16);
        sg.AddEdge(11,13);


        ArrayList<Vertex> list1 = sg.DepthFirstSearch(14,2);

        ArrayList<Vertex> list2 = sg.BreadthFirstSearch(14,16);
        for (Vertex i:list2){
            System.out.print(i.Value + " ");
        }
        System.out.println();

        ArrayList l = sg.WeakVertices();
        System.out.println();*/
        SimpleGraph test = SimpleGraph.RandomGenerated(5);
        test.log();
        System.out.println(test.Check55());

        int[][] aaa =
                {
                        {0,1,1,1,1},
                        {1,0,1,1,1},
                        {1,1,0,1,1},
                        {1,1,1,0,1},
                        {1,1,1,1,0}
                };
        SimpleGraph test2 = new SimpleGraph(aaa);
        test2.log();
        System.out.println(test2.Check55());
    }
}
