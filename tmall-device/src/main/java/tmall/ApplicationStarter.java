package tmall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.concurrent.CountDownLatch;

@SpringBootApplication
public class ApplicationStarter {

    public static void main(String[] args) throws InterruptedException {
//        ApplicationContext ctx = new SpringApplicationBuilder()
//                .sources(ApplicationStarter.class)
//                .run(args);
        SpringApplication.run(ApplicationStarter.class, args);
//        CountDownLatch closeLatch = ctx.getBean(CountDownLatch.class);
//        closeLatch.await();
    }

    @Bean
    public CountDownLatch closeLatch() {
        return new CountDownLatch(1);
    }
}
