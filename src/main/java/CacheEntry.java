import lombok.Data;

@Data
public class CacheEntry<K, V> {
    private K key;
    private V value;
    private int counter;
    private long timestamp;

    public CacheEntry(K key, V value, int counter, long timestamp) {
        this.key = key;
        this.value = value;
        this.counter = counter;
        this.timestamp = timestamp;
    }
}
