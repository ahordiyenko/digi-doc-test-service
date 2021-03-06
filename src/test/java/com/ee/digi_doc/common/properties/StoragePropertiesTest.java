package com.ee.digi_doc.common.properties;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
class StoragePropertiesTest {

    public static final String EXPECTED_FILE_STORAGE_LOCATION = "./var/digi-doc-service/files";
    public static final String EXPECTED_CONTAINER_STORAGE_LOCATION = "./var/digi-doc-service/containers";
    public static final String EXPECTED_SIGNING_DATA_STORAGE_LOCATION = "./var/digi-doc-service/tmp";

    @Autowired
    private StorageProperties storageProperties;

    @Test
    void testFileStorageLocation() {
        assertNotNull(storageProperties);
        assertNotNull(storageProperties.getFile());
        assertNotNull(storageProperties.getFile().getPath());
        assertEquals(EXPECTED_FILE_STORAGE_LOCATION, storageProperties.getFile().getPath());
    }

    @Test
    void testContainerStorageLocation() {
        assertNotNull(storageProperties);
        assertNotNull(storageProperties.getContainer());
        assertNotNull(storageProperties.getContainer().getPath());
        assertEquals(EXPECTED_CONTAINER_STORAGE_LOCATION, storageProperties.getContainer().getPath());
    }

    @Test
    void testSigningDataStorageLocation() {
        assertNotNull(storageProperties);
        assertNotNull(storageProperties.getSigningData());
        assertNotNull(storageProperties.getSigningData().getPath());
        assertEquals(EXPECTED_SIGNING_DATA_STORAGE_LOCATION, storageProperties.getSigningData().getPath());
    }

}