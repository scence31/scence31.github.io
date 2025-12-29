import { useState } from "react";

const StateComponent4 = () => {

    // 화면에 들어갈 배열 변수로 세팅
    const [arr, setArr] = useState(['html', 'css', 'javascript', 'jQuery']);


    // 버튼클릭시 실행할 함수 세팅
    const changeArr = () => {

        // jQuery를 react로 변경하기
        // setArr(['html', 'css', 'javascript', 'react']);

        // 간편하게 - 전개연산자 활용
        const newArr = [...arr];
        newArr[3] = 'react';
        // console.log(newArr);
        setArr(newArr);

    }


    return (

        <div>
            <h4>프론트엔드 기술들</h4>

            <ul>
                {
                    arr.map((item, index) => {

                        return (

                            <li key={index}>{item}</li>
                        )
                    })
                    // = [<li>html</li> ...]
                }
            </ul>

            <br />

            <button onClick={changeArr}>클릭</button>
        </div>

    )
}

export default StateComponent4