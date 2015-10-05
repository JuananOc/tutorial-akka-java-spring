package org.autentia.actor;

import org.springframework.stereotype.Service;

@Service
public class HerreroService {
    private static final long TIEMPO_CREACION_ESPADA = 2000;

    public void crearEspada() throws InterruptedException {
        Thread.sleep(TIEMPO_CREACION_ESPADA);
    }
}
