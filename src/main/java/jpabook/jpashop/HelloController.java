package jpabook.jpashop;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "hello!");
        return "hello"; // 리턴 화면 이름, 타임리프 디펜던시에 기본 세팅 다 잡혀서 가능 .html 뒤에 붙어서 templates에 매핑됨
    }

    // 엔티티
    // Member                   Category
    // 1                         *
    // *                         *
    // Order   1 * OrderItem * 1 Item
    // 1                         (상속관계)
    // 1                         Album, Book, Movie
    // Delivery

    // Member (id: Long, name: string, address: Address, orders:List)
    // Order (id: Long, member: Member, orderItems: List<OrderItem>, delivery: Delivery, status: OrderStatus, orderDate: Date)
    // Delivery (id: Long, order: Order, address: Address, deliveryStatus: DeliveryStatus)
    // OrderItem (id: Long, order: Order, item: Item, orderPrice: int, Count: int)
    // Item (id: Long, name: string, price: int, stockQuantity: int, categories: List<Category>)
    // Category (id: Long, name: string, parent: Category, child: List<Category>, items: List<Items>)

    // Album (artist: string, etc: string)
    // Book (author: string, isbn: string)
    // Movie (director: string, actor: string)

    // Address (city: string, street: string, zipCode: string)
    // (Address는 임베디드 타입)

    // 실무에 적합하지 않은 포인트가 있으나, 학습을 위해 위와 같이 구성
    // 1. category - item 다대다 (ManyToMany) 관계인데 주문상품 처럼 테이블 하나 더 두는게 나음
    // 2. 멤버와 주문이 양방향 연관관계로 되어있는데 Order 만 가지는 형태가 좋다

    // ===========================================================================

    // 테이블
    // Member (id: Long, name: string, city: string, street: string, zipCode: string)
    // Order (id: Long, member_id: id, delivery_id: id, status: OrderStatus, orderDate: Date)
    // Delivery (id: Long, order_id: id, city: string, street: string, zipCode: string, deliveryStatus: DeliveryStatus)
    // OrderItem (id: Long, order_id: id, item_id: id, orderPrice: int, Count: int)
    // Item (id: Long, name: string, price: int, stockQuantity: int, Dtype: string, artist: string, etc: string, author: string, isbn: string, director: string, actor: string)
    // Category (id: Long, name: string, parent_id: id)
    // CategoryItem (item_id, id, cateogry_id: id)
    // 테이블에서는 엔티티 처럼 불가능한 구조로 중간 테이블 하나 만들어야됨

    // ===========================================================================

    // 연관관계 매핑 분석
    // 회원과 주문 : 일대다, 다대일 양방향 연관관계 양방향이므로 연관관계 주인 설정이 필요, 연관관계 주인은 테이블 기준 FK를 가지는 테이블임
    // 여기서는 주문이 연관관계의 주인이 됨
    // 주문 상품과 주문 : 다대일 양방향 관계 외래 키가 주문 상품에 있으므로 주문상품이 연관관계의 주인
    // 주문상품과 상품 : 다대일 단방향 관계 연관관계 주인은 주문상품
    // 주문과 배송 : 일대일 단방향 관계 연관관계 주인은 주문
    // 카테고리와 상품 : @ManyToMany (실무에서는 사용 X)


}
