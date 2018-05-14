import java.util.List;

@FunctionalInterface
public interface CacheReplacement<K, V> {

    CacheEntry<K, V> removeCache(List<CacheEntry<K, V>> entrySet);

}
