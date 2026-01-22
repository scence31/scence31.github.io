---
title: "[JSP] EL(Expression Language)과 JSTL 태그 라이브러리"
date: 2026-01-22 11:00:00 +0900
categories: [Java, Frontend]
tags: [jsp, el, jstl, java, backend]
---

### EL (Expression Language)
데이터를 화면에 출력할 때 스크립틀릿(`<%= %>`) 대신 사용하는 표현 언어

* **형식**: `${data}`
* **특징**
    * `request.getAttribute()` 등 복잡한 코드 없이 변수명만으로 출력 가능
    * 값이 `null`일 경우 에러 대신 빈 문자열 출력
    * 객체의 필드에 접근 시 자동으로 해당 필드의 `Getter` 호출


---

### JSTL (JSP Standard Tag Library)
JSP에서 자주 사용하는 조건문, 반복문 등을 HTML 태그 형태로 제공하는 라이브러리

#### 설정
JSP 상단에 `taglib` 지시자를 추가해야 사용 가능
```jsp
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
```

#### taglib 예시

| 태그 | 용도 | 사용 예시 |
| :--- | :--- | :--- |
| **`<c:set>`** | 변수 선언 및 값 할당 | `<c:set var="name" value="xxx" />` |
| **`<c:if>`** | 단일 조건문 | `<c:if test="${age > 20}">성인</c:if>` |
| **`<c:choose>`** | 다중 조건문 | `<c:when>`, `<c:otherwise>`와 함께 사용 |
| **`<c:forEach>`** | 리스트/배열 반복 | `<c:forEach var="item" items="${list}">` |

특히 `<c:forEach>`는 서버에서 넘어온 List 데이터를 화면에 출력할 때 많이 사용됨