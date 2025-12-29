// Student.js 파일 내부에느 오직 Student 클래스 하나만 둘 것

export default class Student {
    // 파일 하나당 한 개, 내보낼 것도 한 개라면, export default 사용 가능함 (클래스 변수 함수 다 가능)

    // 생성자 함수
    constructor(name, age, classRoom) {

        this.name = name;
        this.age = age;
        this.classRoom = classRoom;
    }

    // 메소드 속성
    toString() {

        return `${this.name}씨는 ${this.age}살이며, ${this.classRoom}에서 열공 중입니다.`;
    }

    // export default Student; 이렇게 마지막에 내보내도 됨.
}