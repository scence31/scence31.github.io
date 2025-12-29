import { useState } from "react"
import axios from "axios";
import { toast } from "react-toastify";

const Index = (props) => {

    const {loginUser, setLoginUser} = props;
    

    // input 요소들에 입력받을 값들을 담아둘 변수를 State형 변수로 세팅
    const userInit = {
        userId : "",
        userPwd : ""
    }

    const [user, setUser] = useState(userInit);

    // onChange
    const handleChange = (e) => {

        const {name, value} = e.target;
        
        const copyUser = {...user};
        copyUser[name] = value;

        setUser(copyUser);

        // console.log(copyUser)
    }

    // 로그인
    const login = (e) => {

        e.preventDefault();

        // input으로 입력받은 로그인정보 초기화(로그아웃 시)
        const copyUser = {...user};
        copyUser.userId = "";
        copyUser.userPwd = "";
        setUser(copyUser);

        // 사용자가 입력한 아디비번 컨트롤러로 넘기기
        const loginAxios = async () => {

            try {

                const url = "http://localhost:8006/backend/auth/login"; // 인증, 인가 기능 - 권한체크 용도
                const method = 'post'

                const response = await axios({
                    url,
                    method,
                    data : user
                })

                // console.log(response.data)
                // 제대로 관리자 로그인되면 jwt 토큰 문자열로 출력, 아니면 빈 문자열

                if(response.data == "") {

                    toast.warning("로그인 실패");

                } else {

                    toast.info("로그인 성공");
                    // 성공하면 자바스크립트 저장소에 정보 담기 - 전역으로 사용, 키+밸류 저장
                    // 대표 저장소(HttpSession 대체) 2개
                    // 1. localStrage : 브라우저 종료해도 데이터 유지
                    // 2. sessionStorage : 초기화
                    sessionStorage.setItem("loginUser", response.data);

                    // 로그인 후 화면전환(주소변경은 x)
                    setLoginUser(sessionStorage.getItem("loginUser"));
                    // == setLoginUser(response.data);

                }


            } catch(e) {

                console.log("로그인 ajax 실패", e);
            }

        }

        loginAxios();
        
    }

    // 로그아웃
    const logout = () => {

        toast.warning("로그아웃");

        // sessionStorage에 담긴 회원정보 날리기
        sessionStorage.removeItem("loginUser");

        setLoginUser(null);
    }

    if(loginUser != null) {

        return (
            <div>
                <h2 align="center">관리자 환영</h2> <br /><br />

                <button onClick={logout} className="btn btn-secondary btn-bg btn-block">로그아웃</button>
            </div>
        );
    } else {

        return (
            <div>
            <h2 align="center">관리자 로그인</h2> <br /><br />
            <form id="">
                <table className="form">
                    <tbody>
                        <tr>
                            <th>아이디</th>
                            <td>
                                <input type="text" name="userId" onChange={handleChange} value={user.userId} />
                            </td>
                        </tr>
                        <tr>
                            <th>비밀번호</th>
                            <td>
                                <input type="password" name="userPwd" onChange={handleChange} value={user.userPwd} />
                            </td>
                        </tr>
                    </tbody>
                </table> <br /><br />

                <div align="center">
                    <button type="submit" className="btn btn-primary btn-bg" onClick={login}>로그인</button>
                </div> <br /><br />
            </form>
            </div>
        )
    }
    
}

export default Index