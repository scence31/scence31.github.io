const StateComponent6 = (props) => {

    // 실행할 구문
    // console.log(props);
    // {str : 'Before', setStr : () => {}}
    // 속성에 접근하고싶으면 props.str

    // setStr은 대입은 불가능하지만 호출은 가능함 (값 불러다 쓰겠다는 뜻)

    // props 간단하게
    const {str, setStr} = props; // 구조분해할당
    // const setStr = props.setStr;

    const changeStr = () => {

        setStr('After'); // props.str은 안됨
        // 값 변경 뿐만 아니라 화면에도 반영함
    }

    return (
        <div>
            <p>전달받은 str : {str}</p>
            <button onClick={changeStr}>str 변경</button>
        </div>
    )
}

export default StateComponent6;