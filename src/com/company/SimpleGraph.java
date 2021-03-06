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

    public SimpleGraph(int[][] set){
        max_vertex = set.length;
        m_adjacency = set;
        vertex = new Vertex[set.length];
    }
    public void AddVertex(int value)
    {
        for (int i = 0; i < max_vertex; i++) {
            if(vertex[i]==null){
                vertex[i] = new Vertex(value);
                break;
            }
        }
        // код добавления новой вершины
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

    public static  SimpleGraph RandomGenerated(int size)
    {
        SimpleGraph simpleGraph = new SimpleGraph(size);
        for (int i = 0; i < size; i++) {
            simpleGraph.AddVertex(i);
        }
        for (int i = 0; i < size-1; i++) {
            for (int j = i+1; j < size; j++) {
                if((int)(Math.random()*10 % 2) == 1){
                    simpleGraph.AddEdge(i,j);
                }
            }
        }
        return simpleGraph;
    }

    public boolean CycleCheck55()
    {
        boolean result = false;
        for (int i = 0; i < max_vertex; i++) {
            result = result || Check55(i);
        }
        return result;
    }

    public boolean Check55(int k){
        boolean result = true;
        int n = 5 + k;
        for (int i = k; i <n - 1 ; i++) {
            for (int j = i + 1; j < n; j++) {
                result &= IsEdge((i % max_vertex), (j % max_vertex));
            }
        }
        return result;
    }

    public boolean CycleCheck33(){
        boolean result = false;
        for (int i = 0; i < max_vertex; i++) {
            result = result || Check33(i);
        }
        return result;
    }

    public boolean Check33(int k){
        boolean result = true;
        int n = 6 + k;
        for (int i = k; i < n - 1; i++) {
            for (int j = i + 1; j < n; j += 2) {
                result &= IsEdge((i % max_vertex), (j % max_vertex));
            }
        }
        return result;
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
                vertex[i].Hit = true;
                if(i!=start)
                return i;
            }
        }
        return -1;
    }

    public ArrayList<Vertex> WeakVertices()
    {
        ArrayList<Vertex> list = new ArrayList<>();
        // возвращает список узлов вне треугольников
        for (int i = 0; i < max_vertex; i++) {
            if(isWeak(i)) list.add(vertex[i]);
        }
        return list;
    }

    public boolean isWeak(int v)
    {
        for (int i = 0; i < max_vertex; i++) {
            for (int j = 0; j < max_vertex; j++) {
                if(IsEdge(v,i) && IsEdge(v,j) && IsEdge(i,j)){
                    //System.out.println(v + " not weak(in triangle)");
                    return false;
                }
            }
        }
        //System.out.println(v + " weak");
        return true;
    }

    public ArrayList<Vertex> BreadthFirstSearch(int VFrom, int VTo)
    {

        int[] level = new int[max_vertex];
        //счетчик уровней
        int l = 0;
        ArrayList list_n = new ArrayList<>();
        ArrayList<Vertex> list = new ArrayList<>();
        for (int i = 0; i<max_vertex; i++){
            vertex[i].Hit = false;
        }
        ArrayList pq = new ArrayList();


        int tmp = VFrom;
        level[VFrom] = 0;
        pq.add(VFrom);
        vertex[VFrom].Hit = true;

        int lvl_tmp;
        while(!pq.isEmpty()){
            tmp = (int)pq.get(0);
            l++;
            lvl_tmp = tmp;
            int t = getUnHit(tmp);
            while(true){
                if(t != -1){
                    pq.add(t);
                    //list.add(vertex[t]);
                    level[t] = level[lvl_tmp] + 1;
                    t = getUnHit(tmp);

                }else break;

                //logs
                //System.out.println(pq);
            }
            pq.remove(0);
        }

        if(level[VTo] != 0){
            list_n.add(VTo);
            list.add(vertex[VTo]);
            tmp = VTo;
            while((int)list_n.get(0)!= VFrom){
                for (int i = 0; i < max_vertex; i++) {
                    if(m_adjacency[tmp][i] == 1 && level[tmp] == level[i] + 1){
                        list_n.add(0, i);
                        list.add(0, vertex[i]);
                        tmp = i;
                        break;
                    }
                }
            }
        }
        //System.out.println(list_n);
        // Узлы задаются позициями в списке vertex.
        // Возвращается список узлов -- путь из VFrom в VTo.
        // Список пустой, если пути нету.
        return list;
    }

    public void log() {
        for (int i = 0; i < max_vertex; i++) {
            for (int j = 0; j < max_vertex; j++) {
                if(j == max_vertex-1){
                    System.out.print(m_adjacency[i][j]);
                }else{
                    System.out.print(m_adjacency[i][j] + ", ");
                }
            }
            System.out.println();
        }
    }
}
