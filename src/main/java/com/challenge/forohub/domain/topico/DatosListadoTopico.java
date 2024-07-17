package com.challenge.forohub.domain.topico;

import java.time.LocalDateTime;

public record DatosListadoTopico(Long id, String titulo, String mensaje, LocalDateTime fechaDeCreacion, Boolean status, String autor, String curso) {
    public DatosListadoTopico(Topico topico) {
        this(topico.getId(), topico.getTitulo(), topico.getMensaje(), topico.getFechaDeCreacion(), topico.getStatus(), topico.getAutor(), topico.getCurso());
    }
}
