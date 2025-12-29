import './App.css'
import StateComponent1 from "./StateComponent1";
import StateComponent2 from './StateComponent2';
import StateComponent3 from './StateComponent3';
import CountComponent from './CountComponent';
import StateComponent4 from './StateComponent4';
import StateComponent5 from './StateComponent5';
import StateComponent6 from './StateComponent6';
import ReplyInsertComponent from './ReplyInsertComponent';
import ReplyListComponent from './ReplyListComponent';


import {useState} from 'react';

function App() {

  // 실행할구문
  // 댓글예제용 State 변수 세팅(DB에서 Ajax 요청을 통해 가져왔다고 가정)

  // 1. DB 조회 가정
  const arr = [];

  arr.push({replyWriter : '홍길동', replyContent : '꿀잼'}); // [0]
  arr.push({replyWriter : '김말순', replyContent : '하이요~'}); // [1]
  arr.push({replyWriter : '박개똥', replyContent : '즐거운 리액트 공부 ㅇㅅㅇ'}); // [2]

  // console.log(arr);
  // [{}, {}, {}]

  // 2. 조회한 json 형식 데이터를 state 형식 변수로 옮겨담기
  const [replyList, setReplyList] = useState(arr);


  // 하위컴포넌트에 넘길 State형 변수 세팅
  const [str, setStr] = useState('Before');

  // 리턴구문
  return (
    <div>
      <h1>State</h1>
      <p>
        - 컴포넌트 상에 값을 출력하고자 할 때 props 대신 사용할 수 있는 일종의 변수개념 <br />
        따라서 값을 매번 변경해서 담을 수 있다. <br />

        - props : 전달된 값을 받는 매개변수(읽기전용, 수정불가, 단순화면출력) <br />
        - state : 컴포넌트 내부에서 출력시 필요한 값을 저장할 수 있는 변수개념(값 수정 가능)
      </p>

      <br /><hr />

      <h3>1. State 필요성</h3>

      <StateComponent1 /> <br /><hr />

      <h3>2. State 사용법 및 주의사항</h3>

      <StateComponent2 /> <br /><hr />

      <h3>간단연습 1</h3>

      <StateComponent3 /> <br /><hr />

      <h3>간단연습 2</h3>

      <p>쇼핑몰 장바구니 예제(수량버튼 조절)</p>

      <CountComponent /> <br /><hr />

      <h3>3. State형 변수를 배열을 담는 용도로 선언</h3>

      <StateComponent4 /> <br /><hr />

      <h3>4. 객체 담는 용도로 선언</h3>

      <StateComponent5 /> <br /><hr />

      <h3>★5. State 변수를 자식컴포넌트의 전달값으로 넘겨보기</h3>
      {/*State형 변수를 props로 넘김 - 읽기전용 제약을 해결함*/}
      
      {/*위에서 세팅한 str, setStr 넘길 것*/}
      <StateComponent6 str={str} setStr={setStr} /> <br /><br />

      <h4>원래 str : {str}</h4>
      {/* State는 전역변수(static) 느낌이라 상위/하위컴포넌트에서 값 수정해도
      모두 변경됨 => 상태관리(state) */}

      <button onClick={() => {setStr('변경')}}>클릭</button>

      <br /><hr />

      <h3>연습문제 - 댓글기능 구현(State)</h3>
      {/* 댓글작성창 컴포넌트(replyList, setReplyList 다 넘기기) */}
      <ReplyInsertComponent replyList={replyList} setReplyList={setReplyList} />

      {/* 댓글목록창 컴포넌트(단순 댓글목록 데이터 출력용) */}
      <ReplyListComponent replyList={replyList} />


    </div>
   
  )
}

export default App
