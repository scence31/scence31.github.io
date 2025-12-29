import {Link} from 'react-router-dom';
import '../style/Header.css';

const Header = () => {
  return (
    <div>
      <h1>Welcome to React Manager</h1>
      <br /><br />
      <div className="navi">
        <div><Link to="/">HOME</Link></div>
        <div><Link to="/member/list">회원관리</Link></div>
        <div><Link to="/notice/list">공지사항관리</Link></div>
        <div><Link to="/board/list">일반게시판관리</Link></div>
      </div>
    </div>
  );
}

export default Header;