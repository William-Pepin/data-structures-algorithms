package com.williampepin.hash;

import java.util.LinkedList;

public class HashTable<K extends Comparable<K>,V extends Comparable<V>> {
    // constructor for capacity
    private LinkedList[] entries = new LinkedList[5];

    public void put(K key, V value){
        var entry = getEntry(key);

        if(entry != null){
            entry.value = value;
            return;
        }
        getOrCreateBucket(key).addLast(new Entry(key, value));
    }

    public V get(K key){
        var entry = getEntry(key);
        return (entry == null) ? null : entry.value;
    }

    public Boolean remove(K key){
        var entry = getEntry(key);
        if(entry == null)
            throw new IllegalStateException();
        return getBucket(key).remove(entry);
    }

    private Entry getEntry(K key){
        var bucket = getBucket(key);
        if(bucket != null) {
            for (var entry : bucket) {
                if (entry.key.equals(key))
                    return entry;
            }
        }
        return null;
    }

    private LinkedList<Entry> getBucket(K key){
        return entries[hash(key)];
    }
    private LinkedList<Entry> getOrCreateBucket(K key){
        var index = hash(key);
        var bucket = entries[index];
        if(bucket == null)
            entries[index] = new LinkedList<Entry>();
        return bucket;
    }


    private int hash(K key){
        return Math.abs(key.hashCode()) % entries.length;
    }

    private class Entry {
        private K key;
        private V value;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}

