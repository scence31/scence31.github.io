// 함수형 컴포넌트 구현

function MyComponent3(props) {
    // 함수형 컴포넌트는 매개변수로 전달값을 받음
    // 대신 전달값이 여러 개면 하나로 묶어야댐 -> 객체로
    // 즉 매개변수 props는 객체타입임.

    // 실행할 구문
    // console.log(props);
    // {name: '', gender: '', age: ''} 객체 형식으로 뭉쳐서 넘어와짐
    // 객체명.속성명 또는 객체명['속성명'] 호출

    // 콘솔에 두 번씩 출력되는 이유
    // 내부적으로 Strict Mode로 실행되기 때문임
    // Strict Mode : 리액트 프로젝트가 구동되는 중에 발생할 수 있는 잠재적인 문제점을 찾아내기 위해
    // 같은 코드를 일부러 두 번씩 돌리는 모드(개발용 모드)
    // 해제하는 방법 - main.jsx 파일에서

    // props 매개변수로 받은 변수에 내가 원하는 값으로 대입하고 싶음
    // props는 값을 변경하거나 새로운 값을 추가할 수 없음. 즉 읽기전용임
    // 근데 전달된 값 주작 가능 => 전개연산자로 객체 깊은복사 후 주작
    /*
    let newProps = {...props};
    newProps.name = '김가현';
    newProps.gender = '여자';
    newProps.age = 21;
    newProps.addr = '서울시';

    console.log(newProps);
    */


    // 리턴구문 - JSX 형식으로 화면을 구현할 수 있음
    return (

        <li>
            {props.name}님은 {props.gender}이며 {props['age']}살입니다.
        </li>

    )
}

// 내보내기
export default MyComponent3