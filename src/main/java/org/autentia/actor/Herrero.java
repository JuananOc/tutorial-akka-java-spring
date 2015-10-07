package org.autentia.actor;

import akka.actor.ActorRef;
import akka.actor.UntypedActor;
import org.autentia.Application;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;

@Named("herrero")
@Scope("prototype")
public class Herrero extends UntypedActor {
    public enum Mensaje {
        CREAR_ESPADA,
        MATERIALES
    }

    private static final Logger log = LoggerFactory.getLogger(Herrero.class);
    private ArrayList<ActorRef> espadachines;
    private final HerreroService herreroService;

    @Inject
    public Herrero(HerreroService herreroService) {
        this.herreroService = herreroService;
    }

    @Override
    public void preStart() {
        espadachines = new ArrayList<>();
    }

    @Override
    public void onReceive(Object o) throws InterruptedException {
        log.info("[Herrero] ha recibido el mensaje: \"{}\".", o);

        if (o == Mensaje.CREAR_ESPADA) {
            espadachines.add(getSender());
            Application.minero.tell(Minero.Mensaje.OBTENER_MATERIALES, getSelf());
        } else if (o == Mensaje.MATERIALES) {
            log.info("[Herrero] está creando espada...");
            herreroService.crearEspada();
            log.info("[Herrero] ha creado espada.");
            if (!espadachines.isEmpty()) {
                espadachines.get(0).tell(Espadachin.Mensaje.ESPADA_NUEVA, getSelf());
                espadachines.remove(0);
            }
        } else {
            unhandled(o);
        }
    }
}
