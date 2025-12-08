package com.microstream.storage;

import jakarta.inject.Singleton;
import org.eclipse.store.storage.embedded.configuration.types.EmbeddedStorageConfiguration;
import org.eclipse.store.storage.embedded.types.EmbeddedStorageManager;

@Singleton
public class StorageServiceImpl implements IStorageService
{
    private EmbeddedStorageManager storageManager;
    private Root root;

    @Override
    public EmbeddedStorageManager provideStorageManager() {

        if (storageManager == null)
        {
            initDatabase();
        }

        return storageManager;
    }

    @Override
    public Root provideRoot()
    {
        if (storageManager == null)
        {
            initDatabase();
        }
        return root;
    }

    private void initDatabase()
    {
        this.root = new Root();
        this.storageManager = EmbeddedStorageConfiguration.Builder()
                .setChannelCount(2)
                .setStorageDirectory("E:/StoragePerformanceDemo")
                .createEmbeddedStorageFoundation().createEmbeddedStorageManager(root)
                .start();
    }
}
