package org.kodluyoruz.warehouseapi.base;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

/**
 * Data Container
 */

/**
 * Bütün işlemlerde hep bunu döneceğiz. Bu bizim için bir Data container olacak
 * Biz bir container içerisinde her şeyi taşıyabiliriz. Yani bütün response' larımızı taşıayacak ana bir
 * container olarak düşünebiliriz. CRUD işlemlerinin service katmanından controller katmanına döneceği obje.
 * normalde response entity dönüyorduk ama burada kendi objemizi yarattık
 */

/**
 * Bu yapı üzerinde bir data taşıyacak ama biz ne data taşıyacağını bilmiyoruz. Bir data tipi olarak
 * object, map, string vs her şey olabilir. Bilmediğimiz veri tipleri için GENERIC yapıyı kullanmalıyız.
 * Runtime' daki bu bilinmezliği aşmanın yolu JVM' e generic yapı ile yaklaşmaktır.
 *
 * Burada dönülecek değerler olarak bir response datası, bir http status ve varsa bir error dönülecek
 */

/**
 * constructor' ları overload ediyoruz. durumdan duruma göre
 */


@Getter
@Setter
public class WarehouseAPIResponseHolder<T> {

    //@JsonInclude(JsonInclude.Include.NON_NULL)
    private T responseData;
    //@JsonInclude(JsonInclude.Include.NON_NULL)
    private HttpStatus httpStatus;
    //@JsonInclude(JsonInclude.Include.NON_NULL)
    private WarehouseAPIResponseError error;

    public WarehouseAPIResponseHolder(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public WarehouseAPIResponseHolder(T responseData, HttpStatus httpStatus) {
        this.responseData = responseData;
        this.httpStatus = httpStatus;
    }

    public WarehouseAPIResponseHolder(HttpStatus httpStatus,
                                      WarehouseAPIResponseError error) {
        this.httpStatus = httpStatus;
        this.error = error;
    }

}
