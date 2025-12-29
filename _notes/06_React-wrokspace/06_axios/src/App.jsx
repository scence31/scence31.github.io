import './App.css'
import AxiosComponent1 from './AxiosComponent1'
import AxiosComponent2 from './AxiosComponent2'
import AxiosComponent3 from './AxiosComponent3';
import AxiosComponent4 from './AxiosComponent4';


function App() {

  return (
    <div>
      <h1>Axios</h1>
      <p>
        - Ajax 기능 구현을 위한 자바스크립트 라이브러리 <br />

        * axios 연동하기
        1. ctrl + c 종료
        2. ..cd 빠져나갈거잇으면 빠져나가기
        3. npm create vite
        4. cd 추가
        5. npm run dev
        7. npm istall axios

        순서는 잘 알아서
      </p>

      <h3>1-1. 간단한 형식 JSON 샘플 데이터 추가</h3>

      <AxiosComponent1 />

      <h3>1-2. 한 번 더 연습</h3>

      <AxiosComponent2 /> <br /><hr />

      <h3>2. 공공데이터 조회</h3>

      <AxiosComponent3 /> <br /><hr />

      <h3>★3. 내가 만든 서버로부터 데이터 조회하기</h3>
      {/* Spring Boot Controller 요청 (백엔드 연동)
      
        리액트 --> 스프링 요청Request
        <-- 응답Response

        스프링 컨트롤러는 @ResponseBody 형식으로(ajax, json)


      
      */}

      <AxiosComponent4 />


      
    </div>
  )
}

export default App
