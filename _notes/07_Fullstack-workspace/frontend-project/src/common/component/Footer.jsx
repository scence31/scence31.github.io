import '../style/Footer.css';
import { Link } from 'react-router-dom';

function Footer() {

    return (
        <div>
            <div className="footer-area">
                <hr/>   
                <div id="footer_1">
                    <Link to="">이용약관</Link> | &nbsp;
                    <Link to="">개인정보취급방침</Link> | &nbsp;
                    <Link to="">인재채용</Link> | &nbsp;
                    <Link to="">오시는길</Link>
                </div>
                <div id="footer_2">
                    강남점 1관 : 서울특별시 강남구 테헤란로14길 6 남도빌딩 2F, 3F, 4F, 5F, 6F <br/>
                    강남점 2관 : 서울특별시 강남구 테헤란로10길 9 그랑프리 빌딩 4F, 5F, 7F <br/>
                    강남점 3관 : 서울특별시 강남구 테헤란로 130 호산빌딩 5F, 6F <br/>
                    종로지원 : 서울특별시 중구 남대문로 120 그레이츠 청계(구 대일빌딩) 2F, 3F <br/>
                    당산지원 : 서울특별시 영등포구 선유동2로 57 이레빌딩(구관) 19F, 20F <br/>
                    논현점 : 서울특별시 강남구 논현로 132길 9 마루빌딩 1F,2F,3F <br/>
                    부산점 : 부산 부산진구 중앙대로 627 삼비빌딩 2F,12F <br/>

                </div>
                <div id="footer_3">
                    Copyright © 1998-2025 KH Information Educational Institute All Right Reserved
                </div>
            </div>
        </div>
    );
}

export default Footer;