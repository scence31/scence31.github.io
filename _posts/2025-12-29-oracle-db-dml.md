---
title: "[Oracle] DML(CRUD), TCL"
date: 2025-12-29 18:00:00 +0900
categories: [Database, Oracle]
tags: [sql, oracle, dml, crud, tcl]
---

## DML이란?
**DML(Data Manipulation Language)**은 데이터베이스 내부의 데이터를 조회, 삽입, 수정, 삭제할 때 사용하는 언어다. 백엔드 개발자가 가장 많이 사용하며, CRUD 기능을 구현하는 핵심 구문

* **C**(Create): `INSERT` (데이터 삽입)
* **R**(Read): `SELECT` (데이터 조회)
* **U**(Update): `UPDATE` (데이터 수정)
* **D**(Delete): `DELETE` (데이터 삭제)

---

## DML 구문
### SELECT
```sql
SELECT 컬럼명
  FROM 테이블명
 WHERE 조건절
 ORDER BY 정렬기준;
```

### CREATE
```sql
INSERT INTO 테이블명 (컬럼1, 컬럼2, ...)
VALUES (값1, 값2, ...);
```

### UPDATE
```sql
UPDATE 테이블명 
   SET 컬럼명 = 변경값
 WHERE 조건절;
```

### DELETE
```sql
DELETE
  FROM 테이블명
 WHERE 조건절;
```

## 적용 순서 - 프 웨 그 해 셀 오
FROM -> WHERE - GROUP BY -> HAVING -> SELECT -> ORDER BY

## TCL
* COMMIT: 변경된 사항을 DB에 확정
* ROLLBACK: 마지막 COMMIT 시점으로 되돌림