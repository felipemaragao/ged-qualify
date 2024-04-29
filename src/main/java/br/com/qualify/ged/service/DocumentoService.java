package br.com.qualify.ged.service;
//
//import br.com.qualify.ged.domain.Documento;
//import br.com.qualify.ged.repository.DocumentoRepository;
//import br.com.qualify.ged.repository.UsuarioRepository;
//
import br.com.qualify.ged.domain.Documento;
import br.com.qualify.ged.repository.DocumentoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.beans.BeanUtils;
import org.springframework.util.StringUtils;

import java.util.Optional;

@Service
public class DocumentoService {
    //
//    private static final String DESTINATARIOS = "ROLE_PESQUISAR_Documento";
//
    private static final Logger logger = LoggerFactory.getLogger(DocumentoService.class);

//
    @Autowired
    private DocumentoRepository documentoRepository;

    //
//    @Autowired
//    private UsuarioRepository usuarioRepository;
////
////    @Autowired
////    private Mailer mailer;
//
//    @Autowired
//    private S3 s3;
//
    public Documento salvar(Documento documento) {
//        validarPessoa(documento);

//        if (StringUtils.hasText(documento.getAnexo())) {
//            s3.salvar(documento.getAnexo());
//        }

        return documentoRepository.save(documento);
    }


    public Documento atualizar(Long codigo, Documento documento) {
        Documento documentoSalvo = buscarDocumentoExistente(codigo);
        if (!documento.getId().equals(documentoSalvo.getId())) {
//            validarPessoa(lancamento);
        }

//        if (!StringUtils.hasText(documento.getAnexo())
//                && StringUtils.hasText(documentoSalvo.getAnexo())) {
//            s3.remover(documentoSalvo.getAnexo());
//        } else if (StringUtils.hasText(documento.getAnexo())
//                && !documento.getAnexo().equals(documentoSalvo.getAnexo())) {
//            s3.substituir(documentoSalvo.getAnexo(), documento.getAnexo());
//        }

        BeanUtils.copyProperties(documento, documentoSalvo, "codigo");

        return documentoRepository.save(documentoSalvo);
    }


    private Documento buscarDocumentoExistente(Long codigo) {
/* 		Optional<Lancamento> lancamentoSalvo = lancamentoRepository.findById(codigo);
		if (lancamentoSalvo.isEmpty()) {
			throw new IllegalArgumentException();
		} */
        return documentoRepository.findById(codigo).orElseThrow(() -> new IllegalArgumentException());
    }

    private void validarDocumento(Documento documento) {
//        Optional<Documento> pessoa = null;
//        if (documento.getPessoa().getCodigo() != null) {
//            pessoa = pessoaRepository.findById(lancamento.getPessoa().getCodigo());
//        }
//
//        if (pessoa.isEmpty() || pessoa.get().isInativo()) {
//            throw new PessoaInexistenteOuInativaException();
//        }
    }
}
