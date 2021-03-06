package com.ee.digi_doc.job;

import com.ee.digi_doc.common.properties.StorageProperties;
import com.ee.digi_doc.persistance.model.SigningData;
import com.ee.digi_doc.service.FileService;
import com.ee.digi_doc.service.SigningDataService;
import com.ee.digi_doc.util.FileGenerator;
import com.ee.digi_doc.util.FileUtils;
import com.ee.digi_doc.util.TestSigningData;
import com.ee.digi_doc.web.request.CreateSigningDataRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.List;

import static org.awaitility.Awaitility.await;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class SigningDataPurgeTaskTest {

    @Autowired
    private FileService fileService;

    @Autowired
    private SigningDataService signingDataService;

    @SpyBean
    private SigningDataPurgeTask task;

    @Autowired
    private StorageProperties storageProperties;

    @BeforeEach
    public void before() {
        CreateSigningDataRequest request = new CreateSigningDataRequest();
        request.setFileIds(List.of(fileService.create(FileGenerator.randomTxtFile()).getId()));
        request.setCertificateInHex(TestSigningData.getRSASigningCertificateInHex());
        SigningData signingData = signingDataService.create(request);
        doReturn(List.of(signingData)).when(task).findEntitiesToCleanUp();
    }

    @AfterEach
    public void after() throws IOException {
        Path signingDataDirectoryPath = Paths.get(storageProperties.getSigningData().getPath());
        Path filesDirectoryPath = Paths.get(storageProperties.getFile().getPath());

        FileUtils.cleanUp(signingDataDirectoryPath);
        FileUtils.cleanUp(filesDirectoryPath);
    }

    @Test
    void testTaskExecution() {
        await().atMost(Duration.ofMinutes(1))
                .untilAsserted(() -> verify(task, atLeast(1)).cleanUp());
    }

}