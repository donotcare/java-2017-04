import org.junit.Test;

import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;

public class CacheTest {

    @Test
    public void testCache() {
//        CacheEngine<Long, UserDataSet> ru.otus.l10.cache = new CacheEngine<>(new CacheSettings(10, 0, 0, false));
//        DBService<UserDataSet> ru.otus.l10.service = new UsersCachedService(new UsersService(), ru.otus.l10.cache);
//        UserDataSet user = new UserDataSet(10, "Ivan");
//        ru.otus.l10.service.save(user);
//        ru.otus.l10.service.read(user.getId());
//
//        assertEquals(1, ru.otus.l10.cache.getHitCount());
//
//        ru.otus.l10.service.read(user.getId() + 1);
//        assertEquals(1, ru.otus.l10.cache.getMissCount());
//        DBConfigHibernate.shutdown();
    }

    @Test
    public void testSoftReferenceCache() {
        //-Xmx1024
//        CacheEngine<Long, UserDataSet> ru.otus.l10.cache = new CacheEngine<>(new CacheSettings(10, 0, 0, false));
//        DBService<UserDataSet> ru.otus.l10.service = new UsersCachedService(new UsersService(), ru.otus.l10.cache);
//        UserDataSet user = new UserDataSet(10, "Ivan");
//        ru.otus.l10.service.save(user);
//        ru.otus.l10.service.read(user.getId());
//        Long userId = user.getId();
//        user = null;
//        assertEquals(1, ru.otus.l10.cache.getHitCount());
//        fillMemory();
//        ru.otus.l10.service.read(userId);
//        assertEquals(1, ru.otus.l10.cache.getMissCount());
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
