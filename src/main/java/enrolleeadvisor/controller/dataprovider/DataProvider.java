package enrolleeadvisor.controller.dataprovider;

import java.util.Collection;

public interface DataProvider<DataObject> {
    void initialize() throws DataProviderException;

    DataObject get(String id) throws DataProviderException;

    Collection<DataObject> getAll() throws DataProviderException;
}
