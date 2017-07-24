import org.junit.Test;

import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;

public class CacheTest {

    @Test
    public void testCache() {
//        CacheEngine<Long, UserDataSet> cache = new CacheEngine<>(new CacheSettings(10, 0, 0, false));
//        DBService<UserDataSet> service = new UsersCachedService(new UsersService(), cache);
//        UserDataSet user = new UserDataSet(10, "Ivan");
//        service.save(user);
//        service.read(user.getId());
//
//        assertEquals(1, cache.getHitCount());
//
//        service.read(user.getId() + 1);
//        assertEquals(1, cache.getMissCount());
//        DBConfigHibernate.shutdown();
    }

    @Test
    public void testSoftReferenceCache() {
        //-Xmx1024
//        CacheEngine<Long, UserDataSet> cache = new CacheEngine<>(new CacheSettings(10, 0, 0, false));
//        DBService<UserDataSet> service = new UsersCachedService(new UsersService(), cache);
//        UserDataSet user = new UserDataSet(10, "Ivan");
//        service.save(user);
//        service.read(user.getId());
//        Long userId = user.getId();
//        user = null;
//        assertEquals(1, cache.getHitCount());
//        fillMemory();
//        service.read(userId);
//        assertEquals(1, cache.getMissCount());
//        DBConfigHibernate.shutdown();
    }

    private void fillMemory() {
        int size = 1000;
        List<SoftReference<BigObject>> weakReferences = new ArrayList<>(size);

        for (int k = 0; k < size; k++) {
            weakReferences.add(new SoftReference<>(new BigObject()));
        }

    }

    private static class BigObject {
        final byte[] array = new byte[1024 * 1024];

        public byte[] getArray() {
            return array;
        }
    }
}
