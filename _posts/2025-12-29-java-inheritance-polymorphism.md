---
title: "[Java] 상속과 다형성"
date: 2025-12-29 16:00:00 +09:00
categories: [Backend, Java]
tags: [java, oop, inheritance, polymorphism, interface]
---

## 상속(Inheritance)
여러 클래스의 중복된 코드(필드, 메소드)를 하나의 부모 클래스로 묶고, 자식 클래스가 물려받는 개념 - 코드의 재사용성 및 확장

* **표현법**: `public class 자식 extends 부모 {}`
* **특징**: 
    - 단일 상속만 가능(부모는 하나만!)
    - 사실 모든 클래스는 최상위 클래스인 `Object`를 자동으로 상속받고 있음
* **흐름**: 자식 객체를 생성하면 Heap 영역에 부모의 필드와 자식의 필드가 함께 생성됨

---

## @Override와 toString()
부모에게 물려받은 능력을 자식 입맛에 맞게 바꾸는 것을 오버라이딩(재정의)이라고 함

* **@Override**: 실수를 방지하기 위한 어노테이션으로, 부모의 메소드와 이름, 매개변수 및 반환형이 같아야 함
* **toString()**: `information()` 대신 사용. `Object` 클래스의 메소드를 재정의하는 것이며, 객체를 출력할 때 주소값 대신 내용이 나오게 해줌

---

## 다형성(Polymorphism)
다형성은 부모 타입의 참조변수로 자식 객체를 다루는 기술로, 코드의 유연함이 극대화됨

* **UpCasting**: 자식 객체를 부모 타입으로 형변환 (자동)
* **DownCasting**: 부모 타입을 다시 자식 타입으로 형변환 (강제)
* **장점**: 객체 배열 하나에 여러 종류의 자식 객체를 담을 수 있어 관리 효율성이 엄청나게 올라갑니다.

> **💡 확인 팁**: `instanceof` 연산자를 쓰면 현재 참조변수가 실제로 어떤 객체를 가리키고 있는지 확인할 수 있어요!

---

## 추상 클래스와 인터페이스
미완성된 설계도를 통해 하위 클래스들에게 이 규칙은 꼭 지켜서 구현하라고 강제하는 역할

| 구분 | 추상 클래스 (abstract) | 인터페이스 (interface) |
| :--- | :--- | :--- |
| **목적** | 표준화된 틀 제공, 상속 관계 | 모든 메소드 구현 강제, 다중 상속 |
| **필드** | 일반 필드 가질 수 있음 | 오직 상수(`public static final`)만 가능 |
| **메소드** | 추상 + 일반 메소드 가능 | 오직 추상 메소드만 가능 |
| **키워드** | `extends` (확장) | `implements` (구현) |

---

## 객체 배열과 다형성의 결합
실무에서는 다형성을 배열과 결합하여 관리한다고 함

```java
Person[] personArr = new Person[2];
personArr[0] = new Student("홍길동"); // UpCasting
personArr[1] = new Teacher("김철수"); // UpCasting

for(int i=0; i < personArr.length; i++) {
    // 동적 바인딩에 의해 각자의 toString()이 호출됨! - 생략가능
    System.out.println(personArr[i].toString()); 
}
```