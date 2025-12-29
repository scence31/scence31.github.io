import { useParams } from "react-router-dom";

const TestComponent2 = () => {

    // 실행할 구문
    // Patah Variable 형식으로 전달된 경우 값들을 뽑아내는 방법(주소 노출)

    // 리액트 제공 useParams 훅 사용 (아깐 location)

    // 값 뽑기
    // 변수 = useParams().변수명; // .변수명 : Route 태그 path 속성에 기술한 :변수명과 동일

    let nno = useParams().noticeNo;

    // console.log(nno);

    // 응용: nno를 가재ㅣ고 ajax 요청 다녀오면 됨
    // 공지사항 글 하나의 정보를 다 응답데이터로 받아오기



    return (
        <div>
            <h1>공지사항 상세조회</h1>
            <h3>공지사항 번호: {nno}</h3>
        </div>
    )
}

export default TestComponent2