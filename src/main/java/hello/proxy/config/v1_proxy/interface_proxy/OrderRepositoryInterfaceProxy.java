package hello.proxy.config.v1_proxy.interface_proxy;

import hello.proxy.app.v1.OrderRepositoryV1;
import hello.proxy.trace.TraceStatus;
import hello.proxy.trace.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class OrderRepositoryInterfaceProxy implements OrderRepositoryV1 {

    /**
     * 프록시가 실제 호출할 원본 리포지토리의 참조를 가지고 있어야 함!
     * */
    private final OrderRepositoryV1 target;
    private final LogTrace trace;

    /**
     * 지금까지는 OrderRepositoryImpl에 LogTrace 구현 로직을 다 적어야 했지만
     * 이렇게 프록시를 만들어 사용한 덕분에 프록시에 해당 로직을 대신 구현 -> OrderRepositoryImpl 코드 변경하지 않아도 됨
     * */
    @Override
    public void save(String itemId) {

        TraceStatus status = null;

        try {
            status = trace.begin("OrderRepository.save()");

            //target 호출
            target.save(itemId);

            trace.end(status);
        }catch(Exception e){
            trace.exception(status, e);
            throw e;
        }
    }
}
