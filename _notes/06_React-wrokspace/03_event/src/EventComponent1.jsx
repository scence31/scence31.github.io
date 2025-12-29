// 함수형 컴포넌트 만들기
function EventComponent1() {

    // 실행할 구문
    // 버튼 클릭, 마우스 빠져나간순간, 들어온순간 실행할 함수 변수로 세팅
    const clickTest = () => {

        alert('클릭')
    }
    // 함수 안에 함수 가능 - 함수도 변수로 취급되기 때문
    const mouseOutTest = () => {console.log('마우스아웃발생')}
    const mouseEnterTest = () => {console.log('마우스인발생')}

    // 리턴구문 - JSX 형식으로 화면 구성
    // 힌 개 요소에 여러 이벤트 부여 가능
    return (
        <button onClick={clickTest} onMouseEnter={mouseEnterTest} onMouseOut={mouseOutTest}>
            버튼클릭
        </button>
    )
}

// 컴포넌트 내보내기
export default EventComponent1
// = export {EventComponent1}