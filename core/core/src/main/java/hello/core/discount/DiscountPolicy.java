package hello.core.discount;

import hello.core.member.Member;

public interface DiscountPolicy {
    /*
    return 되는 것은 할인대상 금액이다.
     */

    int discount(Member member, int price);
}
