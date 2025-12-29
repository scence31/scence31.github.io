import { createRoot } from 'react-dom/client'
import './index.css'
import App from './App.jsx'
import {BrowserRouter} from "react-router-dom";

createRoot(document.getElementById('root')).render(
  <BrowserRouter>
    <App />
  </BrowserRouter>
)

/*
  * 리액트와 스프링부트를 연동해서 관리자 페이지 만들기

  1. 회원관리기능
  회원목록 조회 - R read SELECT
  회원상세 조회 - R read SELECT
  회원정보 수정 - U update UPDATE
  회원탈퇴 - D delete UPDATE

  2. 공지사항관리기능
  공지사항목록 조회 - R read SELECT
  상세 조회 - R read SELECT
  작성 - C create INSERT
  수정 - U update UPDATE
  삭제 - D delete UPDATE

  3. 일반게시판관리기능
  목록조회 + 페이징처리 -
  검색 + 페이징처리 -
  작성 + 첨부파일 1개 업로드 -
  상세조회 - 
  수정 - 
  삭제 - 

  CRUD 기능 자체는 제대로 잘 작동함

*/
