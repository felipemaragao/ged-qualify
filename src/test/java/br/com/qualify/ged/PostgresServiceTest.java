//package br.com.qualify.ged;
//
//import br.com.qualify.ged.domain.Documento;
//import br.com.qualify.ged.repository.DocumentoRepository;
//import org.hibernate.validator.internal.engine.ConstraintViolationImpl;
//import org.junit.jupiter.api.AfterAll;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.ActiveProfiles;
//import org.testcontainers.containers.PostgreSQLContainer;
//
//import java.util.List;
//
//import static org.junit.Assert.assertEquals;
//import static org.mockito.Mockito.doThrow;
//
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@ActiveProfiles("test-containers-flyway")
//public class PostgresServiceTest {
//    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>(
//            "postgres:15-alpine"
//    );
//
//    @Autowired
//    DocumentoRepository documentoRepository;
//
//    @BeforeAll
//    static void beforeAll() {
//        postgres.start();
//    }
//
//    @AfterAll
//    static void afterAll() {
//        postgres.stop();
//    }
//
//    @BeforeEach
//    void setUp() {
//        documentoRepository.deleteAll();
//
////        DBConnectionProvider connectionProvider = new DBConnectionProvider(
////                postgres.getJdbcUrl(),
////                postgres.getUsername(),
////                postgres.getPassword()
////        );
////        customerService = new CustomerService(connectionProvider);
//
//    }
//
//    @Test
//    void shouldGetCustomers() {
//        documentoRepository.save(Documento.builder()
//                        .id(1L)
//                        .nome("Documento")
//                        .urlAnexo("/url/anexo.pdf")
//                        .anexo("dados_documento")
//                       .titulo("titulo-documento")
//                .build());
//        List<Documento> documentos = documentoRepository.findAll();
//        assertEquals("Documento", documentos.get(0).getNome());
//        assertEquals(1, documentoRepository.count());
//    }
//
//}
