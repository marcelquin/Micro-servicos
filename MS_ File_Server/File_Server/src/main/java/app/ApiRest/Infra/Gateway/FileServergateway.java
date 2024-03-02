package app.ApiRest.Infra.Gateway;

import app.ApiRest.Domain.Arquivo;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface FileServergateway {

    public ResponseEntity<Arquivo> SalvarArquivo(@RequestParam String nome, @RequestPart MultipartFile[] files, @RequestParam String codigo) throws IOException;

    public ResponseEntity<Arquivo> AlterarArquivo(@RequestParam String nome, @RequestPart List<String> arquivos, @RequestPart MultipartFile[] files, @RequestParam String codigo) throws IOException;

    public ResponseEntity<Arquivo> AdicionarArquivo(@RequestParam String nome, @RequestPart MultipartFile[] files, @RequestParam String codigo) throws IOException;

    public ResponseEntity<Resource> downloadFiles(@RequestParam String nome, @RequestParam String codigo) throws IOException;
}
