import './App.css'
import { Routes, Route, Link, useNavigate } from 'react-router-dom';
import MemberEnrollFormComponent from './MemberEnrollFormComponent';
import LoginFormComponent from './LoginFormComponent';
import TestComponent1 from './TestComponent1';
import TestComponent2 from './TestComponent2';


function App() {

  // 실행할 굼누
  // useNavigate 훅(함수)를 이용해서 navigate 함수를 얻어내야 함
  let navigate = useNavigate();
  // useNavigate(); 훅을 호출하는 순간 함수가 리턴됨(리턴값이 함수라는 뜻)
  // => let navigate = function() {}; 이런 식으로 내부적으로 발생. 이걸 호출해서 써야 함!
  
  // (가독성 up)회원가입페이지로 이동버튼 클릭시 실행할 함수
  const memberEnrollForm = () => {

    navigate('/member/enrollForm');
  }

  return (
    <div>
      <h1>라우팅 Routing</h1>

      <p>
        - 사용자가 주소창에 요청한 URL 주소에 맞는 페이지를 보여주는 개념 <br />
        - SPA(Single Page Application) 상에서 페이지 이동을 제어하고 URL 주소에 맞는
        페이지를 전환해줌(깜빡임 없이) <br />

        - 폴더 내부에서 React Router 모듈 설치해야 함(기본 라이브러리 아님) <br />
        npm install react-route-dom@6 (= 설치할모듈명)

        - 그리고 main.jsx 파일(최상위파일)에 모든 하위 컴포넌트들에 대한 라우팅 수행 선언하면 됨 <br />
      </p>
      
      <br /><hr />

      <h3>1. 간단한 라우터(라우팅 수행해주는 놈) 사용방법</h3>

      <div>test1</div>
      <div>test2</div>

      {/* URL 주소마다 다른 div 찍기
      
        ex)
        URL 주소가 슬래시일 경우 : <div>난 메인페이지</div>
        URL 주소가 /detail1 : 난 상세페이지야
        /detail2 : 난 상세페이지야2, 3이면 3

        - 마치 조건문인 것마냥
        Routes 복수형 태그로 어느 URL 주소로 요청시 어느 요소를 출력할 것인지
        매칭 내용을 감싸줄 것(1대1 매칭) Routes 내부에 Route 단수태그 1:1 매칭도 함

        path : URL 주소, element : 보일 화면 정보

        - Link 태그로 하면 더 안깜빡임(a태그 대체)
        근데 실제 브라우저 렌더링은 Link지만 a 요소로 그려짐

        - 버튼은 원래 a태그 안돼서 onclick='location.href= ;' 햇어야댐


      */}

      <Routes>
        <Route path="/" element={<div>난 메인페이지</div>}></Route>
        <Route path="/detail1" element={<div>난 상세1</div>} />
        <Route path="/detail2" element={<div>난 상세2</div>} />
        <Route path="/detail3" element={<div>난 상세3</div>} />
      </Routes>

      <Link to='/'>메인페이지로 이동</Link> <br />
      <Link to='/detail1'>상세페이지1로</Link> <br />
      <Link to='/detail2'>상세페이지2로</Link> <br />

      <a href="/detail3">상세페이지3로</a>
      
      <br /> <hr />

      <h3>2. Link 컴포넌트 없이 URL 주소 요청하기</h3>

      {/*
        button
      */}

      <p>
        - Link 컴포넌트 없이 URL 가능한 navigate 함수 사용하면 됨(location.href 대신)

        - useNavigate 리액트 내장함수를 사용해야 함(훅 Hook)
      </p>
      
      <br />

      <button onClick={memberEnrollForm}>회원가입페이지 열기</button>
      
      <button onClick={() => {navigate('/member/loginForm')}}>로그인페이지 열기</button>

      <br /><br />

      <Routes>
        <Route path='/member/enrollForm' element={<MemberEnrollFormComponent />}></Route>
        <Route path='/member/loginForm' element={<LoginFormComponent />}></Route>
      </Routes>

      <br /><hr />

      <h3>3. 화면 깜빡임 없이 요청시 전달값 넘기고 받기</h3>

      <p>
        - navigate 함수를 통해 요청시 전달값을 다른 컴포넌트로 넘길 수 있음 <br />
        (해당 url 주소에서 보여야하는 페이지 컴포넌트로 전달값을 넘기겠다는 의미) <br />
        {/* 
          navigate 함수

          [표현법]

          1. 요청시 전달값이 있을 경우 (1)
          navigate('url주소', {state : {키:밸류, 키:밸류, ...}});
          (노출하기 싫을 때)

          2. 있을경우 (2)
          navigate('url주소/전달값')
          Path Variable 방식으로 은근 같이 전달(url 주소 상에 값이 노출)
          
        */}
      </p>

      <br />

      <button onClick={() => {navigate('/test1', {state : {userId : 'user01', userName : '홍길동', age : 20}});}}>요청시 전달 값 넘기기 1</button>

      <button onClick={() => {navigate('/test2/1');}}>전달값 넘기기 2</button>

      <br /><br />

      <Routes>
        <Route path='/test1' element={<TestComponent1 />}></Route>
        <Route path='/test2/:noticeNo' element={<TestComponent2 />}></Route>
      </Routes>


    </div>
  )
}

export default App
