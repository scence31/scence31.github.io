import { useState } from "react";
import "./StateComponent2.css";

const StateComponent2 = () => {

    // 변수 선언 후 내가 원하는 값으로 변수값을 변경
    // let color = 'Red';

    // color = "Orange";

    /*
        State 사용법

        - useState 리액트 내장함수(Hook)를 이용해서 구조분해할당 문법형식으로 변수를 선언 및 초기화해서 쓴다
        - 단순 변수 뿐만 아니라 그 변수에 대한 setter 함수까지 같이 세팅됨

        [표현법]
        우선 컴포넌트 함수 상단에 import {useState} from 'react';

        컴포넌트 함수 내부에서
        구조분해할당 문법 형식으로 State 변수 및 setter 함수 선언, 초기화
        const [변수명, set변수명] = useState(초기값);

        내부적으로
        useState(초기값) 호출결과로 배열이 하나 리턴됨
        [초기값, function() {}]

        => const [변수명, set변수명] = [초기값, function() {}]

        => const 변수명 = 초기값;
           const set변수명 = function() {};
        
        이 때 set변수명 함수에는 변수에 대한 setter 코드(간접적으로 값을 받아서 대입)
        그 변수값이 변경되었으므로 변수값을 화면에 다시 렌더링해주는 코드가 들어있음
    */

    const [color, setColor] = useState("Red");

    // console.log(color);
    // console.log(setColor);

    // setColor("Orange");
    // console.log(color);
    // 무한반복 Too many re-renders 오류
    // 핵심은 setColor(setter) 함수! setter 함수 내부에선 변수값을 변경하고,
    // 변경된 변수값을 다시 화면에 그림그져주는 코드까지 포함되어있음.
    // setter 함수가 계속 호출됨 어쨋든
    // 제대로된 사용법은 맞음(적재적소에 쓰지 않았기 때문에 오류가 발생한 것 뿐)

    /*
        * State 변수의 setter 함수는 일반 함수형 컴포넌트의 실행할 구문 안에서 바로 호출하면 안 됨
        => 해당 컴포넌트가 다시 렌더링되는 과정에서 무한반복될 것이기 때문

        그렇다면?
        딱 한 번만 실행되고 말 부분 안에서 호출하면 됨 => 반복 차단
        => 이벤트핸들러 함수 : 이벤트가 발생했을 때 딱 한 번만 실행되고 끝남
    */

    // 나름 State를 변경해보기 위해 이벤트 걸어보자
    const mouseEnterTest = () => {

        setColor("Orange");
    }

    const mouseOutTest = () => {

        setColor("Red");
    }


    return (

        <h3 className={color} onMouseEnter={mouseEnterTest} onMouseOut={mouseOutTest}>
            ㅎ
        </h3>
    )
}

export default StateComponent2