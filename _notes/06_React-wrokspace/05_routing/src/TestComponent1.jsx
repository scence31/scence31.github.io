import { useLocation } from "react-router-dom";

const TestComponent1 = () => {

    // 실행할 구문
    /*
        전달값이 {state : {객체}} 형식으로 전달 된 경우, 그 값을 뽑아내는 방법

        - navigate 함수를 통해 전달된 값들을 리액트에서 제공하는 location 객체를 사용하면 됨
        (자바스크립트의 location과 완전 다른 개념)

        [표현법]

        우선 location 객체를 얻어내야 함
        마찬가지로 useLocation이라는 리액트 내장함수(훅)를 이용해서 얻어내기
        (= navigation 훅 방법 동일)

        useLocation(); 호출하면 객체가 하나 리턴 됨
    */

    // location 객체를 지역변수로 얻어내기
    let location = useLocation();

    // console.log(location);
    // pathname : '/test1' - 현재 이 컴포넌트가 보일 때 url 주소
    // state : {키:밸류, ...} - 요청시 전달값들

    // console.log(location.state.userId)

    const {userId, userName, age} = location.state;
    // 구조분해할당 - 가독성, 유지보수, 편리

    return(
        <div>
            <h1>회원정보 상세조회</h1>

            <h3>아이디: {userId}</h3>
            <h3>아름: {userName}</h3>
            <h3>나이: {age}</h3>
        </div>
    )
}

export default TestComponent1