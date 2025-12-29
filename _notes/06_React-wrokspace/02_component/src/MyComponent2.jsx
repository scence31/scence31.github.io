// 함수형 컴포넌트로 만들기
import "./MyComponent2.css"

function MyComponent2() {

    // 이 함수가 호출될 때 실행할 구문
    // 인라인 스타일 방식이지만 가독성 높이기: 별도의 변수로 자바스크립트 객체 생성
    let styleObj = {
        color : 'pink',
        border : '3px dotted pink',
        backgroundColor : 'purple'
    };

    // 리턴구문 - 화면을 구성하는 JSX 태그
    return (

        <div style={styleObj}>
            나의 <span className='second'>두 번째</span> 컴포넌트
            {/* 기존 html 태그에는 class 속성을 기술할 수 있었지만 현재는 JSX 태그
                class 속성 대신 className 속성으로 적으면 됨
            */}
        </div>

    )
}

// 내보내기
export default MyComponent2