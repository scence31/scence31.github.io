import {useParams, useNavigate} from 'react-router-dom';
import { useState, useEffect } from 'react';
import axios from 'axios';
import { toast } from 'react-toastify';

// alert 기능 제공하는 리액트용 라이브러리
// 'react-toastify' 라이브러리

const MemberDetailComponent = () => {


    let navigate = useNavigate();

    // 실행할 구문
    // 조회할 회원의 아이디값(전달값) 뽑기 (useParams 훅)
    const userId = useParams().userId; // == const {userId} = useParmams();
    // console.log(userId);

    // userId를 요청시 전달값으로 넘기면서 Ajax 요청하기
    // 응답데이터로 넘어온 해당 회원의 정보를 아래 return 구문 안에 출력하면 끝

    // 1. State 변수 세팅 - 회원정보담기
    let memberInit = {
        userId : "",
        userName : "",
        gender : "",
        email : "",
        age : "",
        phone : "",
        address : "",
        enrollDate : "",
        status : ""
    };

    const [member, setMember] = useState(memberInit);

    // 요청할 url 주소: http://localhost:8006/backend/member/detail/

    useEffect(() => {

        const selectMember = async () => {
            try {

                const url = `http://localhost:8006/backend/member/detail/${userId}`;
                const method = "get";

                // axios 그대로 + await
                const response = await axios({
                    url,
                    method,
                    headers: {Authorization: `Bearer ${sessionStorage.getItem("loginUser")}`}
                });

                const item = response.data;

                // 조회된 회원이 없으면 "" 이 넘어옴
                if (item === "") {

                    // alert("정보없음");
                    toast.success('회원정보찾기 불가');
                    navigate("/member/list");

                } else {

                    setMember(item);
                    // 성별 체크 등 추가 처리
                }

            } catch (error) {
                console.log("ajax 통신 실패", error);
            }
        };

        selectMember();

    }, []);

    // 수정 버튼 클릭시 실행할 함수
    const updateMember = (e) => {

        // submit 버튼 기본이벤트 지우기
        e.preventDefault();

        // 아래 사용자가 입력한 값들을 뽑아서 ajax 요청 후 돌아오기

        // member라는 State형 변수에는 사용자가 입력한 값들이 제대로 들어가있음
        // 요청시 전달값들을 State형 변수로 그대로 넘기면 됨
        // console.log(member);
        // member를 가지고 회원정보수정요청을 그대로 보내기
        const updateMemberAxios = async () => {

            // 요청할 url 주소
            let url = 'http://localhost:8006/backend/member/update'
            // 요청방식
            let method = 'post';

            try {

                const response = await axios({
                    url,
                    method,
                    data : member,
                    headers: {Authorization: `Bearer ${sessionStorage.getItem("loginUser")}`}
                });

                // console.log(response.data)
                toast.info(response.data);

                // 갱신된 회원정보 조회 후 출력

            } catch {

                console.log('ajax 회원수정 실패');
            }
        };

        // 호출하기
        updateMemberAxios();
        

    }

    // onChange 핸들러 함수
    const handleChange = (e) => {

        // console.log("실?")
        // console.log(e.target)
        // 내용은 바뀌지 않아도 내용물은 바뀌고 있음

        // 타겟의 id 속성값, value 속성값 꺼내기
        // 요소객체명.속성명
        // console.log(e.target.id);
        // console.log(e.target.value);

        const {id, value} = e.target;
        // console.log(id, value);

        // input 요소들의 value 속성값들은 모두 State형 변수를 불러와서 출력했음

        // 기존의 member라는 State형 변수의 값을 깊은복사할 것
        let copyMember = {...member};
        // console.log(copyMember)
        copyMember[id] = value;
        // console.log(copyMember)
        setMember(copyMember);

    }

    // 탈퇴하기 버튼 클릭시 실행할 함수
    const deleteMember = () => {

        // type='button'은 기본이벤트 없어서 useEffect 안해도 됨
        
        const deleteMemberAxios = async () => {

            const url = "http://localhost:8006/backend/member/delete"
            const method = 'post';

            try {

                const response = await axios({
                    url,
                    method,
                    headers: {Authorization: `Bearer ${sessionStorage.getItem("loginUser")}`}

                });

                alert(response.data);

            } catch {


            }
        }
        deleteMemberAxios();
    }
    

    return (
        <div>
            <h2 align="center">회원 상세조회</h2> <br /><br />
            <form id="update-form">
                <table className="form">
                    <tbody>
                        <tr>
                            <th width="130">아이디</th>
                            <td>
                                <input type="text" id="userId" value={member.userId} readOnly />
                            </td>
                        </tr>
                        <tr>
                            <th>이름</th>
                            <td>
                                <input type="text" id="userName" value={member.userName} onChange={handleChange} required />
                            </td>
                        </tr>
                        <tr>
                            <th>이메일</th>
                            <td>
                                <input type="email" id="email" value={member.email} onChange={handleChange} />
                            </td>
                        </tr>
                        <tr>
                            <th>성별</th>
                            <td>
                                <input type="radio" id="genderM" value="M" name="gender" checked={member.gender === 'M'} onChange={handleChange} /><label htmlFor="genderM">남</label>
                                <input type="radio" id="genderF" value="F" name="gender" checked={member.gender === 'F'} onChange={handleChange} /><label htmlFor="genderF">여</label>
                                <input type="radio" id="genderX" value="" name="gender" checked={member.gender === null} onChange={handleChange} /><label htmlFor="genderX">선택안함</label>
                            </td>
                        </tr>
                        <tr>
                            <th>나이</th>
                            <td>
                                <input type="number" id="age" value={member.age} onChange={handleChange} />
                            </td>
                        </tr>
                        <tr>
                            <th>전화번호</th>
                            <td>
                                <input type="text" id="phone" value={member.phone} onChange={handleChange} />
                            </td>
                        </tr>
                        <tr>
                            <th>주소</th>
                            <td>
                                <input type="text" id="address" value={member.address} onChange={handleChange} />
                            </td>
                        </tr>
                        <tr>
                            <th>회원가입일</th>
                            <td><span id="enrollDate">{member.enrollDate}</span></td>
                        </tr>
                        <tr>
                            <th>탈퇴여부</th>
                            <td><span id="status">{(member.status === 'Y') ? '유효' : "탈퇴"}</span></td>
                        </tr>
                    </tbody>
                </table> <br /><br />
                <div align="center">
                    <button type="submit" onClick={updateMember} disabled={member.status === "N"}>수정</button>
                    
                    <button type="button" disabled={member.status === "N"} onClick={deleteMember}>탈퇴</button>
                </div> <br /><br />
            </form>
        </div>
    )
}

export default MemberDetailComponent