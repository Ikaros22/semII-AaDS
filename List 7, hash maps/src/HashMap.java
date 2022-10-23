import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.function.Function;

public class HashMap<TKey, TValue> {
private int mapSize;
private final int firstSize;
private double maxLoad = 1;
private Function<TKey, Integer> hasher;
private LinkedList<Data>[] table;
private int maxElements;
private int elements;
private final int INCREASE = 2;

private final class Data {
		private final TKey key;
		private TValue value;

		private Data(TKey key, TValue value) {
				this.key = key;
				this.value = value;
		}
}


public HashMap(int initialSize, double loadFactor, Function<TKey, Integer> hashFunction) {
		mapSize = initialSize;
		firstSize = initialSize;
		hasher = hashFunction;
		table = new LinkedList[mapSize];
		maxElements = mapSize;
		elements = 0;

		if (loadFactor <= 1)
				maxLoad = loadFactor;
}

public void add(TKey key, TValue value) throws DuplicateKeyException {
		int position = hasher.apply(key);

		if (table[position] == null) {
				table[position] = new LinkedList<Data>();
		} else {
				maxElements++;
				if (ifContains(position, key) != -1 && (key==table[position].get(ifContains(position,key)).key || key==table[position].get(ifContains(position,key))))
						throw new DuplicateKeyException();
		}
		table[position].add(new Data(key, value));
		elements++;

		if ((double) elements / maxElements > maxLoad)
				makeItBigger(mapSize * INCREASE);

}

public void clear() {
		elements = 0;
		mapSize = firstSize;
		table = new LinkedList[firstSize];
}

public boolean containsKey(TKey key) {
		int position = hasher.apply(key);
		return ifContains(position, key) != -1;
}

public boolean containsValue(TValue value) {
		for(int position =0; position<table.length; position++){
				if(table[position]!= null) {
						Iterator<Data> iterator = table[position].stream().iterator();
						while (iterator.hasNext()) {
								TValue temp = iterator.next().value;
								if (temp.equals(value))
										return true;
						}
				}
		}
		return false;
}

public int elements() {
		return elements;
}

public TValue get(TKey key) throws NoSuchElementException {
		int position = hasher.apply(key);
		int index = ifContains(position, key);

		if (index != -1)
				return table[position].get(index).value;
		else
				throw new NoSuchElementException();
}

public void put(TKey key, TValue value) {
		int position = hasher.apply(key);
		int index = ifContains(position, key);
		if(index == -1){
				try {
						add(key, value);
				} catch (DuplicateKeyException e) {
						e.printStackTrace();
				}
		} else {
				table[position].get(index).value = value;
		}
}

public TValue remove(TKey key) {
		int position = hasher.apply(key);
		int index = ifContains(position, key);
		if(index!=-1) {
				TValue temp = table[position].get(index).value;
				table[position].remove(index);
				elements--;
				return temp;
		}
		return null;
}

public int size() {
		return mapSize;
}

public void rehash(Function<TKey, Integer> newHashFunction) {
	HashMap temp = new HashMap(mapSize, maxLoad, newHashFunction);

	for(int position = 0; position<mapSize; position++){
		if(table[position]!=null)
		for (Data data:table[position]) {
			try {
				temp.add(data.key, data.value);
			} catch (DuplicateKeyException e) {
				throw new RuntimeException(e);
			}
		}
}
	this.clear();
	for( int position = 0; position< temp.size(); position++){
		table[position] = temp.table[position];
	}
	elements=temp.elements();
	hasher=newHashFunction;
}

private int ifContains(int position, TKey key) {
		int elementPosition = -1;
		if (table[position] != null)
				for (Data data : table[position]) {
						elementPosition++;
						if (data.key == key)
								return elementPosition;
				}
		return elementPosition;
}

private void makeItBigger(int newSize) throws DuplicateKeyException {
		HashMap temp = new HashMap(newSize, maxLoad,hasher);
	for(int position = 0; position<mapSize; position++){
		if(table[position]!=null)
		for (Data data:table[position]) {
			temp.add(data.key, data.value);
		}
	}

		maxElements = newSize;
		mapSize = newSize;
}
}
