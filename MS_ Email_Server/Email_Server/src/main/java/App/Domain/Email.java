package App.Domain;

import java.time.LocalDateTime;

public record Email(String de,String para, String assunto, String mensagem, LocalDateTime timeStamp) {
}
