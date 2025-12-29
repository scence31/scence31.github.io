import { createRoot } from 'react-dom/client'
import './index.css'
import App from './App.jsx'
import {BrowserRouter} from 'react-router-dom';

createRoot(document.getElementById('root')).render(
  <BrowserRouter>
    <App />
  </BrowserRouter>
)
// 라우팅 기능 수행 BrowserRouter 컴포넌트로 App 호출 구문 감싸기
// => 웹사이트 전반적인 URL 주소 변화를 탐지하고 화면 변환

