import {useState} from 'react';

const StateComponent1 = () => {

    // 실행할 구문
    let [count, setCount] = useState(0);

    const plusCount = () => {

        // count++;
        // 일반 변수는 값이 변경되더라도 출력에 반영되지 않음. 1번만 댐.

        // 클릭회수를 담을 변수 세팅 State
        
        // 아래와 같이 됨
        // useState(0) 호출결과 리턴값이 하나 배열로 돌아옴 [0, function() {}]
        // let count = 0;
        // let setCount = function(value) {count = value;};
        // 즉 setCount는 count 변수에 대한 setter 역할을 수행함

        // State 사용 후 
        // State 변수를 세팅했고 그 변수인 count 값을 1씩 증가시킬 것
        // 단 변경할 값을 직접 대입하지 않고 setCOunt 함수를 통해 간접적으로 대입할 것
        setCount(count + 1);
        // setCount 함수는 내부적으로 count 값을 변경시키고 변경된 count 값을 화면에서 찾아서
        // 다시 렌더링으로 출력해주는 역할을 하기 때문
        console.log(count +1)

    }

    // return 구문 - JSX 화면담당
    return (
        <div>
            <p>총 {count}번 클릭했습니다.</p>
            <button onClick={plusCount}>클릭</button>
        </div>

    )
}

export default StateComponent1