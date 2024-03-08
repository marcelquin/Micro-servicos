package App.ApiRest.Bussness.File;



import App.ApiRest.Infra.Exceptions.NullargumentsException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.zip.ZipOutputStream;

import static org.apache.tomcat.util.http.fileupload.FileUploadBase.CONTENT_DISPOSITION;

@Service
public class FileServerService {


    @Value("#{environment['App.caminhoImagem']}")
    //@Value("${App.caminhoImagem}")
    private String caminhoImagem;

    @Value("#{environment['App.caminhozip']}")
    //@Value("${App.caminhozip}")
    private String caminhozip;

    private UtilService utilService;

    public void Upload(String codigo, MultipartFile[] files)
    {
        try
        {
            if(codigo != null && files != null)
            {
                boolean pasta = new File(caminhoImagem +codigo).mkdir();
                for(MultipartFile file : files)
                {
                    byte[] bytes = file.getBytes();
                    Path caminho = Paths.get(caminhoImagem+codigo+"//"+file.getOriginalFilename());
                    Files.write(caminho, bytes);
                }
                ziparDirectory(codigo);
            }
            else
            { throw new NullargumentsException();}
        }
        catch (Exception e)
        {
            e.getMessage();
        }
    }


    public void Update(String codigo, List<String> arquivos, MultipartFile[] files)
    {
        try
        {
            if(codigo != null && files != null)
            {
                removeArquivo(codigo,arquivos);
                for(MultipartFile file : files)
                {
                    byte[] bytes = file.getBytes();
                    Path caminho = Paths.get(caminhoImagem+codigo+"//"+file.getOriginalFilename());
                    Files.write(caminho, bytes);
                }
                removeArquivoZip(codigo);
                ziparDirectory(codigo);
            }
            else
            { throw new NullargumentsException();}
        }
        catch (Exception e)
        {
            e.getMessage();
        }
    }

    public void AddFile(String codigo, MultipartFile[] files)
    {
        try
        {
            if(codigo != null && files != null)
            {
                for(MultipartFile file : files)
                {
                    byte[] bytes = file.getBytes();
                    Path caminho = Paths.get(caminhoImagem+codigo+"//"+file.getOriginalFilename());
                    Files.write(caminho, bytes);
                }
                removeArquivoZip(codigo);
                ziparDirectory(codigo);
            }
            else
            {throw new NullargumentsException(); }
        }
        catch (Exception e)
        {
            e.getMessage();
        }
    }

    public ResponseEntity<Resource> downloadFiles(String codigo) throws IOException
    {
        try
        {
            String filename = codigo+".zip";
            Path filePath  = Path.of(caminhozip+filename);
            if (!Files.exists(filePath)) {
                throw new FileNotFoundException(filename + " was not found on the server");
            }
            Resource resource = new UrlResource(filePath.toUri());
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add("File-Name", filename);
            httpHeaders.add(CONTENT_DISPOSITION, "attachment;File-Name=" + resource.getFilename());
            return ResponseEntity.ok().contentType(MediaType.parseMediaType(Files.probeContentType(filePath)))
                    .headers(httpHeaders).body(resource);
        }
        catch (Exception e)
        {
            e.getMessage();
        }
        return null;
    }

    public void removeArquivo (String codigo, List<String> files)
    {
        String caminho = caminhoImagem+codigo+"//";
        for(String arquivo : files)
        {
            File file = new File(caminho+arquivo);
            file.delete();
        }
    }

    public void removeArquivoZip (String codigo)
    {
        String caminho = caminhozip+codigo+".zip";
        File file = new File(caminho);
        file.delete();
    }

    public void ziparDirectory(String codigo)throws IOException
    {
        try {
            String sourceFile = caminhoImagem + codigo+ "//";
            FileOutputStream fos = new FileOutputStream(caminhozip +codigo+".zip");
            ZipOutputStream zipOut = new ZipOutputStream(fos);
            File fileToZip = new File(sourceFile);
            String fileName = fileToZip.getName();
            UtilService.zipFile(fileToZip, fileToZip.getName(), zipOut);
            zipOut.close();
            fos.close();
        }catch (Exception e)
        {
            System.out.println("Ops algo deu errado zip!");
            e.getMessage();
        }
    }

}
