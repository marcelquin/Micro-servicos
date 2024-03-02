package app.ApiRest.Domain;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public record Arquivo(String nome, String codigo, List<String> arquivos) {
}
