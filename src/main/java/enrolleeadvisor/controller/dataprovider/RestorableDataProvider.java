package enrolleeadvisor.controller.dataprovider;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collection;
import java.util.HashMap;

public abstract class RestorableDataProvider<DataObject> extends CacheableDataProvider<DataObject> {
    private String filename;
    private boolean isInitialized = false;

    public RestorableDataProvider(String filename) {
        this.filename = filename;
    }

    @Override
    public void initialize() throws DataProviderException {
        try {
            restore();
            isInitialized = true;
        } catch (ClassNotFoundException | IOException e) {
            throw new DataProviderException("Can't restore cache from " + filename, e);
        }
    }

    protected void store() throws IOException, DataProviderException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(getCache());
        }
    }

    @SuppressWarnings("unchecked")
    private void restore() throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(getClass().getResourceAsStream(filename))) {
            setCache((HashMap<String, DataObject>) ois.readObject());
        }
    }

    @Override
    public Collection<DataObject> getAll() throws DataProviderNotInitializedException {
        if (!isInitialized)
            throw new DataProviderNotInitializedException("You should complete initialize() before using getAll()");
        return getCache().values();
    }
}
