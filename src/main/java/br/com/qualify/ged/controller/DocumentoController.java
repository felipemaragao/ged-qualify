package br.com.qualify.ged.controller;

import br.com.qualify.ged.domain.Documento;
import br.com.qualify.ged.domain.DocumentoRequest;

import br.com.qualify.ged.repository.DocumentoRepository;

import br.com.qualify.ged.service.DocumentoService;


import br.com.qualify.ged.storage.S3;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/documentos")
public class DocumentoController {
    @Autowired
    private DocumentoRepository documentoRepository;

    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    private DocumentoService documentoService;

    @Autowired
    private S3 s3;

//    @Autowired
//    private SqsMessageService sqsMessageService;


    @GetMapping
    public List<Documento> listar() {
        return documentoRepository.findAll();
    }

//    @PostMapping
//    public ResponseEntity<Documento> criar(@Valid @RequestBody DocumentoRequest documento, HttpServletResponse response) {
//
//        Documento documentoEntity = Documento.builder().titulo(documento.getTitulo())
//                .nome(documento.getNome()).build();
//        Documento documentoSalvo = documentoRepository.save(documentoEntity);
//
////        publisher.publishEvent(new RecursoCriadoEvent(this, response, documentoSalvo.getId()));
//
//        return ResponseEntity.status(HttpStatus.CREATED).body(documentoSalvo);
//    }

//    @PostMapping("/anexo")
//    public ResponseEntity<Object> uploadAnexo(@RequestParam MultipartFile anexo, Long id) throws IOException {
//        String fileName = anexo.getOriginalFilename();
//
//        System.out.println("filename: " + fileName);
//
//        Optional<Documento> documento = documentoRepository.findById(id);
//
//        try {
//            String checksum = s3.uploadFileNew(fileName, anexo);
////            sqsMessageService.sendMessage(fileName);
//            documento.get().setAnexo(checksum);
//            documentoRepository.save(documento.get());
//        } catch (Exception ex) {
////            message = "Error uploading file: " + ex.getMessage();
//        }
//
//        return ResponseEntity.status(HttpStatus.OK).body(null);
//    }
//
//    @GetMapping("/{download}")
//    public ResponseEntity<String> downloadFile(@RequestParam String keyName) {
//        String uploadLink = s3.createPresignedGetUrl(keyName, 10);
//        return ResponseEntity.status(HttpStatus.OK).body(uploadLink);
//    }
//
//
//    @PostMapping("/documento")
//    public ResponseEntity<Documento> criaDocumento(@Valid @RequestBody Documento documento, HttpServletResponse response) throws IOException {
//        Documento documentoSalvo = documentoService.salvar(documento);
////        publisher.publishEvent(new RecursoCriadoEvent(this, response, documentoSalvo.getId()));
//        return ResponseEntity.status(HttpStatus.CREATED).body(documentoSalvo);
//    }
//
//    @PutMapping("/{codigo}")
//    public ResponseEntity<Documento> atualizar(@PathVariable Long codigo, @Valid @RequestBody Documento documento) {
//        try {
//            Documento documentoSalvo = documentoService.atualizar(codigo, documento);
//            return ResponseEntity.ok(documentoSalvo);
//        } catch (IllegalArgumentException e) {
//            return ResponseEntity.notFound().build();
//        }
//    }
//
//    @DeleteMapping("/{codigo}")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void remover(@PathVariable Long codigo) {
//        documentoRepository.deleteById(codigo);
//    }

//    @GetMapping("/{codigo}")
//    public ResponseEntity<Documento> buscarPeloCodigo(@PathVariable Long codigo) {
//        Optional<Documento> categoria = documentoRepository.findById(codigo);
//        return categoria.isPresent() ? ResponseEntity.ok(categoria.get()) : ResponseEntity.notFound().build();
//    }



}
