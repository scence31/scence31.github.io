import {useState, useEffect} from 'react';
import axios from 'axios';

const AxiosComponent4 = () => {

    // 실행할 구문
    // 1. 조회된 데이터들을 담은 tr 요소들이 담겨있을 배열을 State형 변수로 세팅
    const [dataList, setDataList] = useState([]);

    // 2. 최초로딩
    useEffect(() => {

        // 요청할 주소
        // http://localhost:8006/spring/member/list
        let url ='http://localhost:8006/spring/member/list';

        // 3. axios => Spring Boot Controller 호출 후 응답데이터 담기
        axios({
            url, // url : url,
            method : 'get',

        }).then((response) => {

            // console.log(response.data)

            /*
                CORS 오류: localhost: 서로 다름 불일치 현상(브라우저가 보안상 이유로 막아버림)

                * 크로스 도메인 이슈(CORS Policy)
                - 웹 브라우저에서 Ajax 등을 통해 다른 서버의 도메인주소로 URL 요청을 할 때 충돌

                - 해결방법 : 해당 컨트롤러 메소드 상단에 @CrossOrigin 어노테이션 기입
            */

            const items = response.data;

            // 4. tr 요소로 묶어서 배열에 담기
            const trArr = items.map((item, index) => {

                return(
                    <tr key={index}>
                        <td>{item.userNo}</td>
                        <td>{item.userId}</td>
                        <td>{item.userName}</td>
                        <td>{item.phone}</td>
                        <td>{item.email}</td>
                        <td>{item.address}</td>
                        <td>{item.enrollDate}</td>
                        <td>{(item.status == 'Y') ? '유효회원' : '탈퇴회원'}</td>
                    </tr>
                )
            })

            setDataList(trArr)

            

        }).catch(() => {

            console.log('ajax 통신 실패');

        });

    }, []);

    return(
        <div>
            <h4>회원목록 조회</h4>
            <table border={1}>
                <thead>
                    <tr>
                        <th>회원번호</th>
                        <th>아이디</th>
                        <th>이름</th>
                        <th>전화번호</th>
                        <th>이메일주소</th>
                        <th>집주소</th>
                        <th>회원가입일자</th>
                        <th>탈퇴여부</th>
                    </tr>
                </thead>
                <tbody>
                    {dataList}
                </tbody>
            </table>
        </div>
    )
}

export default AxiosComponent4