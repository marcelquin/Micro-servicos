package app.ApiRest.Bussness;

import app.ApiRest.Bussness.Util.UtilService;
import app.ApiRest.Domain.Arquivo;
import app.ApiRest.Exceotions.NullargumentsException;
import app.ApiRest.Infra.Gateway.FileServergateway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.DelegatingServerHttpResponse;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import static java.nio.file.Paths.get;
import static org.apache.tomcat.util.http.fileupload.FileUploadBase.CONTENT_DISPOSITION;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipOutputStream;

@Service
public class FileServerService implements FileServergateway {

    private final UtilService utilService;

    public FileServerService(UtilService utilService) {
        this.utilService = utilService;
    }

    @Value("#{environment['app.caminhoImagem']}")
    private String caminhoImagem;
    @Value("#{environment['app.caminhoImagemZip']}")
    private String caminhoImagemZip;

    static final int TAMANHO_BUFFER = 4096;

    @Override
    public ResponseEntity<Arquivo> SalvarArquivo(String nome, MultipartFile[] files, String codigo) throws IOException
    {
        try
        {
            if (nome != null && files != null)
            {
                List<String> arquivos = new ArrayList<>();
                boolean pasta = new File(caminhoImagem + nome).mkdir();
                if (pasta)
                {
                    for (MultipartFile file : files) {
                        byte[] bytes = file.getBytes();
                        Path caminho = get(caminhoImagem + nome + "//" + file.getOriginalFilename());
                        Files.write(caminho, bytes);
                        arquivos.add(file.getOriginalFilename());
                    }
                    ziparArquivos(nome, codigo);
                    Arquivo response = new Arquivo(nome,codigo,arquivos);
                    return  new ResponseEntity<>(response, HttpStatus.CREATED);
                } else {
                    throw new NullargumentsException();
                }
            }
        }catch (Exception e)
        {
            e.getMessage();
        }
        return null;
    }

    @Override
    public ResponseEntity<Arquivo> AlterarArquivo(String nome, List<String>arquivos, MultipartFile[] files, String codigo) throws IOException
    {
        try
        {
            if(nome != null&& files != null && codigo != null)
            {
                List<String> listarquivos = new ArrayList<>();
                String caminho = caminhoImagem+nome+"//";
                arquivos.forEach(item -> RemoverArquivo(caminho+item));
                for (MultipartFile file : files)
                {
                    byte[] bytes = file.getBytes();
                    Path arquivo = get(caminho + file.getOriginalFilename());
                    Files.write(arquivo, bytes);
                    listarquivos.add(file.getOriginalFilename());
                }
                RemoverArquivo(caminhoImagemZip+ nome+"_"+codigo+".zip");
                ziparArquivos(nome,codigo);
                Arquivo response = new Arquivo(nome,codigo,listarquivos);
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
            else
            { throw new NullargumentsException();}
        }catch (Exception e)
        {
            e.getMessage();
        }
        return null;

    }
    @Override
    public ResponseEntity<Arquivo> AdicionarArquivo(String nome, MultipartFile[] files,String codigo) throws IOException
    {
        try{
            if(nome != null&& files != null && codigo != null)
            {
                String caminho = caminhoImagem+nome+"//";
                List<String> listarquivos = new ArrayList<>();
                for (MultipartFile file : files)
                {
                    byte[] bytes = file.getBytes();
                    Path arquivo = get(caminho + file.getOriginalFilename());
                    Files.write(arquivo, bytes);
                    listarquivos.add(file.getOriginalFilename());
                }
                Arquivo response = new Arquivo(nome,codigo,listarquivos);
                ziparArquivos(nome,codigo);
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
            else
            { throw new NullargumentsException();}
        }catch (Exception e)
        {
            e.getMessage();
        }
        return null;
    }

    @Override
    public ResponseEntity<Resource> downloadFiles(String nome,String codigo) throws IOException
    {
        try
        {
            Path filePath = Path.of(caminhoImagemZip+nome+"_"+codigo+".zip");
            if(!Files.exists(filePath)) {
                throw new FileNotFoundException(codigo + " Arquivo não encontrado");
            }
            Resource resource = new UrlResource(filePath.toUri());
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add("File-Name", codigo);
            httpHeaders.add(CONTENT_DISPOSITION, "attachment;File-Name=" + resource.getFilename());
            return ResponseEntity.ok().contentType(MediaType.parseMediaType(Files.probeContentType(filePath)))
                    .headers(httpHeaders).body(resource);
        }catch (Exception e)
        {
            e.getMessage();
        }
        return null;
    }

    public void RemoverArquivo(String fileName)
    {
        File file = new File(fileName);
        file.delete();
    }

    public void ziparArquivos(String nomePacote, String codigo)throws IOException
    {
        String sourceFile = caminhoImagem +nomePacote+"//";
        FileOutputStream fos = new FileOutputStream(caminhoImagemZip + nomePacote+"_"+codigo+".zip");
        ZipOutputStream zipOut = new ZipOutputStream(fos);
        File fileToZip = new File(sourceFile);
        String fileName = fileToZip.getName();
        UtilService.zipFile(fileToZip, fileToZip.getName(), zipOut);
        zipOut.close();
        fos.close();
    }
}
