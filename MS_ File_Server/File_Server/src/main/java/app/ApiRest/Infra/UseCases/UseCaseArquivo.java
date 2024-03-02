package app.ApiRest.Infra.UseCases;

import app.ApiRest.Domain.Arquivo;
import app.ApiRest.Infra.Gateway.FileServergateway;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public class UseCaseArquivo {
    private final FileServergateway fileServergateway;

    public UseCaseArquivo(FileServergateway fileServergateway) {
        this.fileServergateway = fileServergateway;
    }

    public ResponseEntity<Arquivo> SalvarArquivo(String nome, MultipartFile[] files, String codigo) throws IOException { return fileServergateway.SalvarArquivo(nome, files,codigo);}


    public ResponseEntity<Arquivo> AlterarArquivo(String nome, List<String> arquivos, MultipartFile[] files, String codigo) throws IOException
    { return fileServergateway.AlterarArquivo(nome, arquivos, files, codigo);}

    public ResponseEntity<Arquivo> AdicionarArquivo(String nome, MultipartFile[] files,String codigo) throws IOException
    {return fileServergateway.AdicionarArquivo(nome, files, codigo);}


    public ResponseEntity<Resource> downloadFiles(String nome,String codigo) throws IOException
    { return fileServergateway.downloadFiles(nome,codigo);}
}
