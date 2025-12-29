import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import './index.css'
import App from './App.jsx'

createRoot(document.getElementById('root')).render(
  <StrictMode>
    <App />
  </StrictMode>,
)
// index.jtml로부터 id가 root인 요소(div)를 선택해서 div 요소 안에
// React에서 제공하는 방식(render 함수)으로 자바스크립트 언어로 화면에 동적 그림 그림.
// 이 때, App.jsx 파일로부터 App이라는 함수를 가져옴(import), 함수를 <App /> 태그 형식으로 호출
