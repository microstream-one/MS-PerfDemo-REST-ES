package com.microstream.storage;

import org.eclipse.store.storage.embedded.types.EmbeddedStorageManager;

public interface IStorageService
{
    EmbeddedStorageManager provideStorageManager();
    Root provideRoot();
}
