package com.company;

import java.util.*;

class Vertex
{
    public int Value;
    public boolean Hit;
    public Vertex(int val)
    {
        Value = val;
        Hit = false;
    }
}

class SimpleGraph
{
    Vertex [] vertex;
    int [][] m_adjacency;
    int max_vertex;

    public SimpleGraph(int size)
    {
        max_vertex = size;
        m_adjacency = new int [size][size];
        vertex = new Vertex[size];
    }

    public void AddVertex(int value)
    {
        for (int i = 0; i < max_vertex; i++) {
            if(vertex[i]==null){
                vertex[i] = new Vertex(value);
                break;
            }
        }
        // ваш код добавления новой вершины
        // с значением value
        // в незанятую позицию vertex
    }

    // здесь и далее, параметры v -- индекс вершины
    // в списке  vertex
    public void RemoveVertex(int v)
    {
        vertex[v] = null;
        for (int i = 0; i < max_vertex; i++) {
            m_adjacency[v][i] = 0;
            m_adjacency[i][v] = 0;
        }
        // ваш код удаления вершины со всеми её рёбрами
    }

    public boolean IsEdge(int v1, int v2)
    {
        // true если есть ребро между вершинами v1 и v2
        if(m_adjacency[v1][v2] == 1){return true;}
        return false;
    }

    public void AddEdge(int v1, int v2)
    {
        m_adjacency[v1][v2] = 1;
        m_adjacency[v2][v1] = 1;
    }

    public void RemoveEdge(int v1, int v2)
    {
        m_adjacency[v1][v2] = 0;
        m_adjacency[v2][v1] = 0;
        // удаление ребра между вершинами v1 и v2
    }

    public ArrayList<Vertex> DepthFirstSearch(int VFrom, int VTo)
    {
        ArrayList<Vertex> list = new ArrayList<>();
        for (int i = 0; i<max_vertex; i++){
            vertex[i].Hit = false;
        }
        Stack path = new Stack();

        //System.out.println(VFrom + " начало");
        int x = VFrom;
        path.push(x);

        while(!path.empty()){
            //System.out.println(x);
            if(m_adjacency[x][VTo] == 1){
                x = VTo;
            }else{
                x = getUnHit(x);
                if(x == -1) {
                    path.pop();
                    if(!path.empty()){x = (int)path.pop();}else break;
                }
            }
            vertex[x].Hit = true;
            path.push(x);
            if(x == VTo){
                //System.out.println("naiden " + path);
                break;
            }
        }

        // Узлы задаются позициями в списке vertex.
        // Возвращается список узлов -- путь из VFrom в VTo.
        // Список пустой, если пути нету.
        while(!path.empty()){
            list.add(0, vertex[(int)path.pop()]);
        }
        return list;
    }


    public int getUnHit(int start)
    {
        for (int i = 0; i < max_vertex; i++) {
            if(m_adjacency[start][i] == 1 && !vertex[i].Hit){
                if(i!=start)
                return i;
            }
        }
        return -1;
    }
}
