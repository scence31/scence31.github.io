import {useState} from 'react';

const StateComponent3 = () => {

    // 실행할 구문
    // h4 요소에 출력할 문구를 담을 State 변수를 세팅
    const[message, setMessage] = useState('안녕');

    // 버튼클릭시 실행할 함수
    const changeMsg = () => {

        if(message == '안녕') {

            setMessage('잘가');

        } else {

            setMessage('안녕');
        }
        

    }

    return (

        <div>
            <button onClick={changeMsg}>클릭해</button>
            <h4>{message}</h4>
        </div>
    )
}

export default StateComponent3