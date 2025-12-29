import { useState, useEffect } from "react"
import axios from "axios";
import { toast } from "react-toastify";
import { useNavigate } from "react-router-dom";

const NoticeEnrollFormComponent = () => {

    let navigate = useNavigate();

    // input, textarea 요소에 적힐 값을 나타내는 State형 변수 세팅
    let noticeInit = {
        noticeTitle : "",
        noticeContent : "",
        noticeWriter : "admin"
    }

    const [notice, setNotice] = useState(noticeInit);

    // onChange handler 함수 생성
    const handleChange = (e) => {

        // console.log(e.target);
        // e.target의 주요 속성인 name, value 속성값을 따로 빼두기
        const {name, value} = e.target;
        // console.log(name, value)

        // 깊은복사하기 notice라는 state형 변수의 값을.
        let copyNotice = {...notice};
        copyNotice[name] = value;
        // console.log(copyNotice);

        setNotice(copyNotice);

    }

    // 작성 실행할 함수
    const insertNotice = (e) => {

        e.preventDefault();

        const insertNoticeAxios = async () => {

            // url, 방식
            const url = `http://localhost:8006/backend/notice/insert`;
            const method = 'post';

           try {

            const response = await axios({
                url,
                method,
                data : notice
            })


            navigate("/notice/list");


           } catch {

                console.log("ajax 실패")
                toast.info("ㅅㄱ");
           }

        }

        insertNoticeAxios();
    }

    // 초기화 실행 함수
    const reset = () => {

        // 입력받은 내용물 비우기
        // 깊은복사
        let copyNotice = {...notice};
        copyNotice.noticeTitle = "";
        copyNotice.noticeContent = "";
        setNotice(copyNotice);
    }

    return (
        <div>
            <h2 align="center">공지사항 작성</h2> <br /><br />

            <form id="enroll-form">
                <table className="form">
                    <tbody>
                        <tr>
                            <th>제목</th>
                            <td>
                                <input type="text" name="noticeTitle" onChange={handleChange} value={notice.noticeTitle} />
                            </td>
                        </tr>
                        <tr>
                            <th>내용</th>
                            <td>
                                <textarea name="noticeContent" onChange={handleChange} value={notice.noticeContent}></textarea>
                            </td>
                        </tr>
                    </tbody>

                </table>
                <br /><br />
                <div align="center">
                    <button type="subnit" onClick={insertNotice}>작성</button> &nbsp;
                    <button type="reset" onClick={reset}>초기화</button>
                </div>
                <br /><br />
            </form>
        </div>
    )
}

export default NoticeEnrollFormComponent