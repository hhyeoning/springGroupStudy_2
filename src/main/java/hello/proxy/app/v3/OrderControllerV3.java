package hello.proxy.app.v3;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ProxyApplication에서 @SpringBootApplication(scanBasePackages = "hello.proxy.app")을 사용했고
 * 각각 @RestController, @Service, @Repository 어노테이션을 가지고 있기 때문에
 * 자동 컴포넌트 스캔의 대상이 됨
 * */
@Slf4j
@RestController
public class OrderControllerV3 {

    private final OrderServiceV3 orderService;

    public OrderControllerV3(OrderServiceV3 orderService){
        this.orderService = orderService;
    }

    @GetMapping("/v3/request")
    public String request(String itemId) {
        orderService.orderItem(itemId);
        return "ok";
    }

    @GetMapping("/v3/no-log")
    public String noLog() {
        return "ok";
    }
}
