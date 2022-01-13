package com.kwj.akkkka.extension;

import akka.actor.Extension;
import akka.actor.Props;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * Spring을 통해 Akka를 사용할 수 있게 도와주는 확장
 */
@Component
@RequiredArgsConstructor
public class SpringExtension implements Extension {
    private final ApplicationContext applicationContext;

    /**
     * Create a Props for the specified actorName using the SpringActorProducer class.
     */
    public Props props(String actorName, Object... args) {
        return ArrayUtils.isNotEmpty(args) ?
                Props.create(SpringActorProducer.class, applicationContext, actorName, args) :
                Props.create(SpringActorProducer.class, applicationContext, actorName);
    }
}