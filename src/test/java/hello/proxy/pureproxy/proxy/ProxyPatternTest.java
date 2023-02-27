package hello.proxy.pureproxy.proxy;

import hello.proxy.pureproxy.proxy.code.CacheProxy;
import hello.proxy.pureproxy.proxy.code.ProxyPatternClient;
import hello.proxy.pureproxy.proxy.code.RealSubject;
import org.junit.jupiter.api.Test;

public class ProxyPatternTest {

    /**
     * client.execute() 결과값이 한번 조회하면 변하지 않는 데이터라면
     * 어딘가에 보관해두고 이미 조회한 데이터를 사용하는 것이 성능상 좋음 -> 캐시!(프록시 패턴의 주요 기능 중 하나)
     * */
    @Test
    void noProxyTest(){
        RealSubject realSubject = new RealSubject();
        ProxyPatternClient client = new ProxyPatternClient(realSubject);
        client.execute();
        client.execute();
        client.execute();
    }


    /**
     * realSubject 와 cacheProxy를 생성하고 둘을 연결.
     * 결과적으로 cacheProxy 가 realSubject를 참조하는 런타임 연결 객체 의존관계가 완성.
     * 그리고 마지막으로 Clien에 realSubject가 아닌 cacheProxy를 주입.
     * Client -> cacheProxy -> realSubject 런타임 객체 의존관계 완성.
     * */
    @Test
    void cacheProxyTest(){
        RealSubject realSubject = new RealSubject();
        CacheProxy cacheProxy = new CacheProxy(realSubject);
        ProxyPatternClient client = new ProxyPatternClient(cacheProxy);
        client.execute();
        client.execute();
        client.execute();
    }
}
