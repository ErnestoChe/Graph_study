package com.company;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
	    SimpleGraph sg = new SimpleGraph(12);
        for (int i = 0; i < 12; i++) {
            sg.AddVertex(i);
        }
        sg.AddEdge(0,1);
        sg.AddEdge(0,4);
        sg.AddEdge(5,6);
        //sg.AddEdge(0,2);
        sg.AddEdge(1,3);
        sg.AddEdge(1,7);
        sg.AddEdge(3,6);
        sg.AddEdge(0,5);
        //sg.AddEdge(3,7);
        sg.AddEdge(3,5);
        //sg.AddEdge(4,9);
        sg.AddEdge(5,8);
        sg.AddEdge(9,10);
        sg.AddEdge(6,10);
        sg.AddEdge(7,11);
        ArrayList<Vertex> list1 = sg.DepthFirstSearch(2, 6);
        ArrayList<Vertex> list2 = sg.DepthFirstSearch(8, 11);
        ArrayList<Vertex> list3 = sg.DepthFirstSearch(11, 8);


        System.out.println(list1);
        System.out.println(list2);
        System.out.println(list3);
        System.out.println();
    }
}
