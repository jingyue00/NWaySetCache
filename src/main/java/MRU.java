import java.util.List;

public class MRU<K, V> implements CacheReplacement<K, V> {

    @Override
    public CacheEntry<K, V> removeCache(List<CacheEntry<K, V>> entrySet) {
        CacheEntry<K, V> cacheEntryToRemove = null;
        int leastUsed = Integer.MIN_VALUE;
        for (CacheEntry<K, V> cacheEntry : entrySet) {
            if (cacheEntry.getCounter() > leastUsed) {
                cacheEntryToRemove = cacheEntry;
                leastUsed = cacheEntryToRemove.getCounter();
            }
        }
        return cacheEntryToRemove;
    }
}
