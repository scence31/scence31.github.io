import './App.css'
import EventComponent1 from './EventComponent1.jsx';
import EventComponent2 from './EventComponent2.jsx';
import EventComponent3 from './EventComponent3.jsx';
import EventComponent4 from './EventComponent4';
import MemberEnrollFormComponent from './MemberEnrollFormComponent.jsx';
// .jsx 생략 가능

// 함수형 컴포넌트
function App() {

  // 실행할 구문
  

  // 리턴구문 - JSX 형식으로 구성
  return (
    <div>
      <h1>이벤트</h1>
      <p>
        이벤트 3요소 <br />
        1. target : 이벤트를 당하는 요소 <br />
        2. type : 이벤트 종류 <br />
        3. handler : 이벤트가 발생했을 때 실행할 코드(함수단위) <br /><br />

        이벤트 타입에 맞게 이벤트를 걸고자 하는 타겟을 선택해서 해당 이벤트가 발생했을 대 실행할 핸들러를 연결해 주는 것 <br />
        - 이벤트 속성명 카멜케이스 적용하고, 핸들러함수는 중괄호 내부에 함수로 정의하면 됨
      </p>

      <br /><hr />

      <h3>1. 이벤트를 정의한 기본적인 컴포넌트 만들기</h3>

      <EventComponent1 />

      <br /><hr />

      <h3>2. 이벤트 타겟을 얻어내는 방법</h3>

      <EventComponent2 />

      <br /><hr />

      <EventComponent3 test={'ㅂ2'}/>

      <br /><hr />

      <h3>★3. 기본 이벤트를 제거하는 방법</h3>

      <EventComponent4 />

      <br /><hr />

      <h3>4. 컴포넌트, 이벤트 활용 연습문제 - 회원가입 폼 만들기</h3>

      <MemberEnrollFormComponent />
    </div>
  )
}

// 컴포넌트 내보내기
export default App
