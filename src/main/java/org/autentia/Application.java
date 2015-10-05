package org.autentia;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import org.autentia.actor.Espadachin;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Application {
    public static ActorRef espadachin;
    public static ActorRef herrero;
    public static ActorRef minero;

    public static void main(String[] args) {
        final ConfigurableApplicationContext applicationContext = SpringApplication.run(Application.class, args);
        ActorSystem actorSystem = applicationContext.getBean(ActorSystem.class);

        espadachin = actorSystem.actorOf(SpringExtension.SpringExtProvider.get(actorSystem).props("espadachin"), "espadachin");
        herrero = actorSystem.actorOf(SpringExtension.SpringExtProvider.get(actorSystem).props("herrero"), "herrero");
        minero = actorSystem.actorOf(SpringExtension.SpringExtProvider.get(actorSystem).props("minero"), "minero");

        espadachin.tell(Espadachin.Mensaje.ESPADA_ROTA, ActorRef.noSender());
    }
}