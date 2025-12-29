import './App.css'
import Header from "./common/component/Header";
import Footer from "./common/component/Footer";
import {Routes, Route} from 'react-router-dom';
import Index from './Index';

// 회원관련 컴포넌트
import MemberListComponent from './member/component/MemberListComponent';
import MemberDetailComponent from './member/component/MemberDetailComponent';

import "react-toastify/dist/ReactToastify.css";
import {ToastContainer} from "react-toastify";

// 공지사항 관련 컴포넌트
import NoticeListComponent from './notice/component/NoticeListComponent';
import NoticeDetailComponent from './notice/component/NoticeDetailComponent';
import NoticeEnrollFormComponent from './notice/component/NoticeEnrollFormComponent';
import NoticeUpdateFormComponent from './notice/component/NoticeUpdateFormComponent';

// 일반게시판 관련
import BoardListComponent from './board/component/BoardListComponent';
import BoardEnrollFormComponent from './board/component/BoardEnrollFormComponent';
import BoardDetailComponent from './board/component/BoardDetailComponent';
import BoardUpdateFormComponent from './board/component/BoardUpdateFormComponent';

import { useState } from 'react';

function App() {

  // sessionStorage에 있던 값을 state형 변수에 담기
  const [loginUser, setLoginUser] = useState(sessionStorage.getItem("loginUser"));
  // sessionStorage.getItem("키값") : "밸류값"으로 State형 변수에 초기화

  if(loginUser != null) {

      // 로그인 후 보일 화면(모든 컴포넌트가 한 눈에)
    return (
      <div>

        <ToastContainer position='top-right' autoClose={3000} />

        {/* SPA 특성상 이 App 함수의 리턴구문 안 쪽에 모든 화면을 다 그려줘야 함 */}

        {/* 메뉴바(헤더) 배치 */}
        <Header />

        {/* 화면 보이 부분(content 영역)
          - 각 화면을 컴포넌트 단위로 만들고 (page component)
          각 url 주소에 따라 화면이 깜빡거리지 않게 이 content 영역 안에 매번 다르게 보이게 처리
          - 이 때, Route, Routes 컴포넌트를 이용해서 url 주소와 보여야 하는 페이지 컴포넌트를 1:1로 매칭해서 작성하기
        */}
        <div className='content'>

          <Routes>
            <Route path="/" element={ <Index loginUser={loginUser} setLoginUser={setLoginUser} /> } />

            <Route path="/member/list" element={<MemberListComponent />}></Route>
            <Route path="/member/detail/:userId" element={<MemberDetailComponent />}></Route>


            <Route path="/notice/list" element={<NoticeListComponent />}></Route>
            <Route path="/notice/detail/:noticeNo" element={<NoticeDetailComponent />}></Route>
            <Route path="/notice/enrollForm/" element={<NoticeEnrollFormComponent />}></Route>
            <Route path="/notice/updateForm" element={<NoticeUpdateFormComponent />}></Route>


            <Route path="/board/list" element={<BoardListComponent />}></Route>
            <Route path="/board/enrollForm" element={<BoardEnrollFormComponent />}></Route>
            <Route path="/board/detail/:boardNo" element={<BoardDetailComponent />}></Route>
            <Route path="/board/updateForm" element={<BoardUpdateFormComponent />}></Route>
          </Routes>

        </div>
        
        {/* 푸터 배치 */}
        <Footer />

      </div>
    )


  } else {

    // 제대로 인가 기능을 구현하려면 백엔드도 건드려야 함

    // 로그인 전 화면
    return (

      <div className='content'>
        <ToastContainer position='top-right' autoClose={3000} />
        <Index loginUser={loginUser} setLoginUser={setLoginUser} />
      </div>
    )

  }
  

}

export default App
