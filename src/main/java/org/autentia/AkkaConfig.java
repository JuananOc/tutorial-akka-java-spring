package org.autentia;

import akka.actor.Actor;
import akka.actor.ActorSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;

import static org.autentia.SpringExtension.SpringExtProvider;

@Configuration
public class AkkaConfig {

    @Autowired
    private ApplicationContext applicationContext;

    @Bean
    public ActorSystem actorSystem() {
        ActorSystem actorSystem = ActorSystem.create("ActorSystem");
        SpringExtProvider.get(actorSystem).initialize(applicationContext);
        return actorSystem;
    }

}