import {useState, useEffect} from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

const MemberListComponent = () => {

    // 실행할 구문
    // tr 요소 클릭시 url주소 이동 - navigate 함수 세팅
    const navigate = useNavigate();
    

    const [dataList, setDataList] = useState([]);

    useEffect(() => {

        // com.kh.controller.MemberController 클래스로 요청보내기
        // http://localhost:8006/backend/member/list - GET 방식

        let url = "http://localhost:8006/backend/member/list";
        let method = 'get';

        axios({
            url,
            method,
            // 앞으로 요청마다 JWT 토큰값을 전달값으로 같이 보낼 것
            headers: {Authorization: `Bearer ${sessionStorage.getItem("loginUser")}`}
            // 설정값들을 url 주소의 header 영역에 담을 수 있음. 인증키는 어쏘라이즈에
            
        }).then((response) => {
            // console.log('ajax 통신성공');
            // console.log(response.data)

            const items = response.data; // [{}, {}, {}, ...]

            const trArr = items.map((items, index) => {

                return (
                    // tr 요소를 클릭하면 상세보기 페이지로 넘어가야 함
                    <tr key={index} onClick={() => {

                                                // 회원의 PK는 아이디값임(item.userId) -> 이 아이디로 한 사람만 정확히 조회하고 화면에 뿌리기
                                                // navigate 함수 이용 url 요청
                                                navigate(`/member/detail/${items.userId}`);
                                                // Path Variable 방식으로 주소창에 표시해서 넘김

                                            }}>
                        <td>{items.userId}</td>
                        <td>{items.userName}</td>
                        <td>{items.email}</td>
                        <td>{items.gender}</td>
                        <td>{items.age}</td>
                        <td>{items.enrollDate}</td>
                        <td>{(items.status == 'Y') ? '유효회원' : '탈퇴회원'}</td>
                    </tr>
                )
            });
            

            setDataList(trArr);


        }).catch(() => {

            console.log('회원목록 조회용 ajax 통신 실패');
        });

    }, []);

    return (
        <div>
            <h2 align="center">회원목록조회</h2> <br /><br />

            <table className="list-area table table-hover">
                <thead>
                    <tr>
                        <th width="180">아이디</th>
                        <th width="150">회원명</th>
                        <th width="250">이메일</th>
                        <th width="100">성별</th>
                        <th width="100">나이</th>
                        <th width="150">회원가입일</th>
                        <th width="100">탈퇴여부</th>
                    </tr>
                </thead>
                <tbody>
                    {dataList}
                </tbody>
            </table>

            <br /><br />
        </div>
    )
}

export default MemberListComponent