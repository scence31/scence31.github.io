const EventComponent3 = (props) => {

    // h2 요소가 클릭되었을 때 실행할 함수 (clickTest)
    // ㅎ2 문구를 ㅂ2 분구로 변경
    // console.log(props)

    const clickTest = (e) => {

        e.target.innerText = props.test;
    }

    return (

        <h2 onClick={clickTest}>
            ㅎ2
        </h2>

    )
}

export default EventComponent3