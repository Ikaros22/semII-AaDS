package com.company;


public class Merger {

    public static OneWayLinkedList<Integer> merge(
            OneWayLinkedList<Integer> list1,
            OneWayLinkedList<Integer> list2) {
		    OneWayLinkedList <Integer> merged = new OneWayLinkedList<>();

		   if(list1.isEmpty()){
		   		return list2;
		   }
		   if(list2.isEmpty()){
		   		return list1;
		   }

		   list1.getLastReturned();
		   list2.getLastReturned();

		    while(list1.ifDone()||list2.ifDone()){
		    		int value1 =(list1.value()!=null) ? list1.value() : list2.value();
				    int value2 =(list2.value()!=null) ? list2.value() : list1.value();

				    if(value1==value2){
						    merged.add(value1);
						    list1.getLastReturned();
						    list2.getLastReturned();
				    } else if(value1<value2){
						    merged.add(value1);
						    list1.getLastReturned();
				    } else if(value2<value1){
						    merged.add(value2);
						    list2.getLastReturned();
				    }
		    }

        return merged;
    }

public static void main(String[] args) {
    		OneWayLinkedList one = new OneWayLinkedList();
		one.add(1);
		one.add(3);
		one.add(5);
    		OneWayLinkedList two = new OneWayLinkedList();
    		two.add(2);
    		two.add(4);
    		two.add(6);
		merge(one, two).print();

}
}
