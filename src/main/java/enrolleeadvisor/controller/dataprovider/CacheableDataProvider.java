package enrolleeadvisor.controller.dataprovider;

import java.io.IOException;
import java.util.HashMap;

public abstract class CacheableDataProvider<DataObject> implements DataProvider<DataObject> {
    private HashMap<String, DataObject> cache = new HashMap<String, DataObject>();

    public abstract DataObject load(String id) throws DataProviderException, IOException;

    @Override
    public DataObject get(String id) throws DataProviderException {
        if (!cache.containsKey(id))
            try {
                cache.put(id, load(id));
            } catch (IOException e) {
                throw new DataProviderException("Can't load cacheable data object with id = " + id, e);
            }
        return cache.get(id);
    }

    protected void clearCache() {
        cache = new HashMap<String, DataObject>();
    }

    HashMap<String, DataObject> getCache() throws DataProviderNotInitializedException {
        return cache;
    }

    void setCache(HashMap<String, DataObject> cache) {
        this.cache = cache;
    }
}
