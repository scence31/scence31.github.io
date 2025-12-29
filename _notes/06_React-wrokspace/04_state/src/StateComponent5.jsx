import { useState } from "react";

const StateComponent5 = () => {

    // 객체형식으로 지정
    const [obj, setObj] = useState({name: '홍길동', age : 20, addr : '서울시'});

    // 객체는 배열과 달리 인덱스가 없어서 for in문 돌려야 함
    let liArr = [];
    for(let key in obj) {

        // console.log(onj[key]);

        liArr.push(<li key={key}>{obj[key]}</li>);
    }

    // 버튼클릭시 실행할 함수
    const changeObj = () => {

        // 버튼이 클릭될 때마다 age 값을 1씩 증가하게
        // setObj({name: '홍길동', age : obj.age + 1, addr : '서울시'});
        let newObj = {...obj};
        newObj.age = newObj.age + 1;

        setObj(newObj);


    }

    return (
        <div>
            <h4>회원정보 상세보기</h4>
            <ul>
                {liArr}
            </ul>
            <br />
            <button onClick={changeObj}>클릭</button>
        </div>
    )
}

export default StateComponent5