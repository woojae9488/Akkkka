package com.kwj.akkkka.config.akka;

import akka.actor.ActorSystem;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import com.typesafe.config.ConfigValueFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
@RequiredArgsConstructor
public class AkkaConfig {
    private final Environment env;

    @Bean
    public ActorSystem actorSystem() {
        return ActorSystem.create("AkkkkaApp", akkaConfiguration());
    }

    @Bean
    public Config akkaConfiguration() {
        String ip = env.getProperty("akka.remote.netty.tcp.ip");
        String port = env.getProperty("akka.remote.netty.tcp.port");

        // 기존 Akka설정과(application.conf) + 최종계산된 Spring의 설정(appplication.properties)를 머징시키는방법
        // AKKA에서 필요한 기능들 설정은 충분히한후, 변경요소를 Spring설정을 노출하여 변경하는 방식
        return ConfigFactory.load()
                .withValue("akka.remote.netty.tcp.ip", ConfigValueFactory.fromAnyRef(ip))
                .withValue("akka.remote.netty.tcp.port", ConfigValueFactory.fromAnyRef(port));
    }
}
