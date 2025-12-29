import './EventComponent2.css';

const EventComponent2 = () => {
    // 함수형 컴포넌트를 위처럼 나타낼 수 있음

    // 실행할 구문
    // 아래 버튼이 클릭되는 순간 실행할 함수를 변수로 정의
    const clickTest = (e) => {

        // console.log('?')

        // 자바스크립트에서 이벤트타겟을 얻어내는 방법
        // 1. console.log(window.event.target)

        // 2. 익명 이벤트핸들러 함수의 경우, 매개변수로 e를 정의한 후 얻어내는 방법
        // console.log(e.target) // (=window.event.target)
        // 권장

        // 3. console.log(this) (불가능)
        // 4. console.log(document.getElementBy("#btn2"))
        // 5. console.log(document.querySelector("#btn2"))

        // 버튼 클릭할 때마다 타겟에 스타일 다르게 부여하기
        if(e.target.className == 'red') {
            e.target.className = 'orange'
        }
    }

    // 리턴구문 - JSX 형식 화면 구성
    return (
        <button id='btn2' className='red' onClick={clickTest}>
            클릭해
        </button>

    )


}

export default EventComponent2