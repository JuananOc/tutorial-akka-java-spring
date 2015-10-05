package org.autentia.actor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class MineroService {
    private static final long TIEMPO_OBTENCION_MATERIALES = 2000;
    private static final Logger log = LoggerFactory.getLogger(MineroService.class);

    public void obtenerMinerales() {
        try {
            Thread.sleep(TIEMPO_OBTENCION_MATERIALES);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}