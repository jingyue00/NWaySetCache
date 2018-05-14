
@FunctionalInterface
public interface GetValueIfNotExist<K, V> {

    V getValue(K key);

}
