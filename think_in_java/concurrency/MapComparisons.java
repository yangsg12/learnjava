package think_in_java.concurrency;

import java.util.Map;

/**
 * Created by Yang on 2015/3/26.
 * 比较 synchronizedHashMap 和 ConcurrentHashMap 性能
 */

abstract class MapTest extends Tester<Map<Integer, Integer>> {
    MapTest(String testId, int nReaders, int nWriters) {
        super(testId,nReaders,nWriters);
    }
}
public class MapComparisons {
}
