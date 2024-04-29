//package br.com.qualify.ged.service;
//
//import br.com.qualify.ged.repository.DocumentoAnexoRepository;
//import br.com.qualify.ged.repository.UsuarioRepository;
//import br.com.qualify.ged.storage.S3;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.BeanUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.util.StringUtils;
//
//@Service
//public class DocumentoAnexoService {
//
//    private static final String DESTINATARIOS = "ROLE_PESQUISAR_Documento";
//
//    private static final Logger logger = LoggerFactory.getLogger(DocumentoAnexoService.class);
//
//
//    @Autowired
//    private DocumentoAnexoRepository documentoAnexoRepository;
//
//    @Autowired
//    private UsuarioRepository usuarioRepository;
//
////    @Autowired
////    private Mailer mailer;
//
//@Autowired
//private S3 s3;
//
//    public DocumentoAnexo salvar(DocumentoAnexo documentoAnexo) {
////        validarPessoa(Documento);
//
//        if (StringUtils.hasText(documentoAnexo.getAnexo())) {
//            s3.salvar(documentoAnexo.getAnexo());
//            }
//
//            return documentoAnexoRepository.save(documentoAnexo);
//        }
//
//    public DocumentoAnexo atualizar(Long codigo, DocumentoAnexo documentoAnexo) {
//        DocumentoAnexo documentoAnexoSalvo= buscarDocumentoExistente(codigo);
//
//        if (StringUtils.isEmpty(documentoAnexoSalvo.getAnexo())
//                && StringUtils.hasText(documentoAnexoSalvo.getAnexo())) {
//            s3.remover(documentoAnexoSalvo.getAnexo());
//        } else if (StringUtils.hasLength(documentoAnexoSalvo.getAnexo())
//                && !documentoAnexoSalvo.getAnexo().equals(documentoAnexoSalvo.getAnexo())) {
//           s3.substituir(documentoAnexoSalvo.getAnexo(), documentoAnexoSalvo.getAnexo());
//        }
//
//        BeanUtils.copyProperties(documentoAnexo, documentoAnexoSalvo, "codigo");
//
//        return documentoAnexoRepository.save(documentoAnexoSalvo);
//    }
//
//
//    private DocumentoAnexo buscarDocumentoExistente(Long codigo) {
//        return documentoAnexoRepository.findById(codigo).orElseThrow(() -> new IllegalArgumentException());
//    }
//
//}
