package com.company;

import java.util.Iterator;

public class Distincter {
    public static TwoWayLinkedList<Integer> distinct(TwoWayLinkedList<Integer> list)
    {
        TwoWayLinkedList <Integer> distincted = new TwoWayLinkedList<Integer>();
        Iterator <Integer> iterator = list.iterator();

        if(list.isEmpty()){
            return distincted;
        }

        int value = list.get(0);
        distincted.add(value);

        while(iterator.hasNext()){
            int value2 = iterator.next();
            if(value != value2) {
                distincted.add(value2);
                value=value2;
            }
        }
        return distincted;
    }

    public static void main(String[] args) {
        TwoWayLinkedList <Integer> one = new TwoWayLinkedList<>();
        one.add(1);
        one.add(2);
        one.add(2);
        one.add(3);
        distinct(one).print();
    }
    }

