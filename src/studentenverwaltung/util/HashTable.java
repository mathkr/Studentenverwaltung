package studentenverwaltung.util;

public class HashTable<K, T> {
	private static final int DEFAULT_BUCKET_CAPACITY = 10;
	private static final int DEFAULT_HASH_VARIETY = 20000;

	private ArrayList<Map<K, T>> buckets;
	private int initBucketCapacity;

	public HashTable(int initBucketCapacity, int hashVariety){
		buckets = new ArrayList<Map<K, T>>(hashVariety);
		for(int i = 0; i < hashVariety; ++i){
			buckets.add(null);
		}
		this.initBucketCapacity = initBucketCapacity;
	}

	public HashTable(){
		this(DEFAULT_BUCKET_CAPACITY, DEFAULT_HASH_VARIETY);
	}

	public void add(K key, T element){
		int hash = getHash(key);
		if (buckets.get(hash) == null){
			buckets.set(hash, new Map<K, T>(initBucketCapacity));
			buckets.get(hash).put(key, element);
		} else {
			buckets.get(hash).put(key, element);
		}
	}

	public T get(K key){
		int hash = getHash(key);
		if(buckets.get(hash) != null){
			return (T) buckets.get(hash).get(key);
		} else {
			return null;
		}
	}

	public ArrayList<T> getValues(){
		ArrayList<T> res = new ArrayList();

		for(int i = 0; i < buckets.getSize(); ++i){
			Map<K, T> map;
			if((map = buckets.get(i)) != null){
				for(int j = 0; j < map.getSize(); ++j){
					res.add(map.get(j));
				}
			}
		}

		return res;
	}

	public void remove(K key){
		int hash = getHash(key);
		if(buckets.get(hash) != null){
			buckets.get(hash).remove(key);
		}
	}

	public boolean contains(K key){
		int hash = getHash(key);
		if(buckets.get(hash) == null){
			return false;
		} else  {
			return buckets.get(hash).containsKey(key);
		}
	}

	private int getHash(K key){
		return key.hashCode() % buckets.getSize();
	}
}
