---
title: "[Java] 객체(Object)"
date: 2025-12-29 10:00:00 +0900
categories: [Backend, Java]
tags: [java, oop]
---

## 객체지향(OOP) - 3대 요소: 추상화, 캡슐화, 다형성
현실의 사물을 코드로 옮기는 과정으로 설계가 중요함
**추상화**: 목적에 맞게 핵심을 추려 스케치하는 작업
**캡슐화**: 보안 관련으로 `private` 접근제한자로 막고, `setter/getter` 메소드로 대화하기

---

## VO(Value Object)로 클래스 만들기

1. 필드부: `private` 변수 선언
2. 생성자부: 기본생성자 및 매개변수 생성자
3. 메소드부: `setter/getter` 및 `toString`

```java
public class Student {
    private String name; // 1. 필드부

    public Student() {} // 2. 기본 생성자

    public Student(String name) { // 3. 매개변수 생성자
        this.name = name;
    }

    // 4. 메소드부
    public void setName(String name) { this.name = name; }
    public String getName() { return name; }
    public String toString() { return "이름: " + name; }
}
```

## Static, Final
1. static: 공동으로 사용 가능하지만, 많이 사용하면 컴퓨터 성능이 저하됨
2. final: 한 번 정하면 바꿀 수 없는 상수로 보통 `public static final` 형태로 사용

## 오버로딩
한 클래스 안에 이름이 같은 메소드를 여러 개 생성함(단, 접근제한자, 반환형은 영향을 주지 않음)


