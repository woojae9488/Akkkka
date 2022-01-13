package com.kwj.akkkka.extension;

import akka.actor.Actor;
import akka.actor.IndirectActorProducer;
import org.springframework.context.ApplicationContext;

/**
 * Spring에서 Akka의 Actor를 생성할 수 있게 도와주는 Actor 제공자
 */

public class SpringActorProducer implements IndirectActorProducer {
    private final ApplicationContext applicationContext;
    private final String actorName;
    private final Object[] args;

    public SpringActorProducer(ApplicationContext applicationContext, String actorName) {
        this.applicationContext = applicationContext;
        this.actorName = actorName;
        this.args = null;
    }

    public SpringActorProducer(ApplicationContext applicationContext, String actorName, Object... args) {
        this.applicationContext = applicationContext;
        this.actorName = actorName;
        this.args = args;
    }


    @Override
    public Actor produce() {
        return args == null ?
                (Actor) applicationContext.getBean(actorName) :
                (Actor) applicationContext.getBean(actorName, args);
    }

    @Override
    @SuppressWarnings("unchecked")
    public Class<? extends Actor> actorClass() {
        return (Class<? extends Actor>) applicationContext.getType(actorName);
    }
}