import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class NWaySetCache<K, V> {
    int nWay;
    int cacheSize;
    CacheReplacement<K, V> cacheReplacement;
    GetValueIfNotExist<K, V> getValueIfNotExist;
    Map<Integer, Map<K, CacheEntry<K, V>>> cacheMap = new HashMap<>();

    public V get(K key) {

        int keyToHash = key.hashCode() % (cacheSize / nWay);
        Map<K, CacheEntry<K, V>> cacheEntryMap = cacheMap.get(keyToHash);

        if (cacheEntryMap.containsKey(key)) {
            CacheEntry<K, V> cacheEntry = cacheEntryMap.get(key);
            int count = cacheEntry.getCounter();
            cacheEntry.setCounter(count++);
            System.out.printf("%s%n", cacheEntry.toString());
            return cacheEntry.getValue();

        } else {

            V value = getValueIfNotExist.getValue(key);

            if (cacheEntryMap.size() < nWay) {
                cacheEntryMap.put(key, new CacheEntry<>(key, value, 1, System.currentTimeMillis()));
                return value;

            } else {
                CacheEntry<K, V> cacheToRemove = cacheReplacement.removeCache(new ArrayList<>(cacheEntryMap.values()));
                cacheEntryMap.remove(cacheToRemove.getKey());
                cacheEntryMap.put(key, new CacheEntry<>(key, value, 1, System.currentTimeMillis()));
                return value;
            }
        }
    }
}
