package com.company;

public class Main {

    public static void main(String[] args) {
	    SimpleGraph sg = new SimpleGraph(5);
	    sg.AddVertex(0);
        sg.AddVertex(1);
        sg.AddVertex(2);
        sg.AddVertex(3);
        System.out.println(sg.IsEdge(0,1));
        sg.AddEdge(0,1);
        sg.AddEdge(0,2);
        sg.AddEdge(1,3);
        System.out.println(sg.IsEdge(0,1));
        System.out.println(sg.IsEdge(1,3)+ " 1-3");
        sg.RemoveEdge(1,3);
        System.out.println(sg.IsEdge(1,3)+ " 1-3");
        System.out.println();
    }
}
