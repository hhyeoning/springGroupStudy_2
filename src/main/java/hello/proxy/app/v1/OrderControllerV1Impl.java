package hello.proxy.app.v1;

public class OrderControllerV1Impl implements OrderControllerV1{

    private final OrderServiceV1 orderService;

    public OrderControllerV1Impl(OrderServiceV1 orderService) {
        this.orderService = orderService;
    }

    /**
     * LogTrace 적용 대상
     * */
    @Override
    public String request(String itemId) {
        orderService.orderItem(itemId);
        return "ok";
    }

    /**
     * LogTrace 적용 미대상
     * */
    @Override
    public String noLog() {
        return "ok";
    }
}
