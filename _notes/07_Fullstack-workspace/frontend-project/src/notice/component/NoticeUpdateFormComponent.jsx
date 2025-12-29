import { useState, useEffect } from "react"
import axios from "axios"
import { useLocation, useNavigate } from "react-router-dom"
import { toast } from "react-toastify"

const NoticeUpdateFormComponent = () => {

    // 실행할 구문

    const navigate = useNavigate();
    // state 세팅 - 출력할 내용물
    const noticeInit = {
        noticeNo : "",
        noticeTitle : "",
        noticeWriter : "",
        noticeContent : "",
        createDate : ""
    }

    const [notice, setNotice] = useState(noticeInit);

    // 수정할 공지사항 글번호(url 주소 상에 노출되지 않게 보냈음)
    // => location 객체를 사용했음 - useLocation 훅 사용
    const location = useLocation();

    // console.log(location);
    
    let noticeNo = location.state.noticeNo;

    // 로딩되자마자 정보 갖고오기
    useEffect(() => {

        const selectNoticeAxios = async () => {

            // 공지사항 상세조회요청 재활용하기
            const url = `http://localhost:8006/backend/notice/detail/${noticeNo}`;
            const method = 'get';

            try {

                const response = await axios({
                    url,
                    method
                })

                // console.log(response.data)
                const item = response.data;

                setNotice(item);

            } catch {

                console.log('ajax 실패')
            }

        }

        selectNoticeAxios();

    }, []);

    // onChange handler 함수
    const handleChange = (e) => {

        // console.log(e.target);

        const {name, value} = e.target;

        // 깊은복사
        const copyNotice = {...notice};

        copyNotice[name] = value;

        setNotice(copyNotice);
    }

    // 수정완료 버튼 클릭 실행함수
    const updateNotice = (e) => {

        e.preventDefault();

        const updateNoticeAxios = async () => {

            const url = `http://localhost:8006/backend/notice/update`;
            const method = 'post';

            try {

                const response = await axios({
                    url,
                    method,
                    data : notice
                })

                // console.log(response.data)

                toast.info(response.data);

                navigate(`/notice/detail/${noticeNo}`)

            } catch {

                console.log("실패");

            }

        }

        updateNoticeAxios();

    }
    


    return (
        <div>
            <h2 align="center">공지사항 수정</h2> <br /><br />

            <form id="update-form">
                <table className="form">
                    <tbody>
                        <tr>
                            <th>제목</th>
                            <td>
                                <input type="text" value={notice.noticeTitle} name="noticeTitle" onChange={handleChange} />
                            </td>
                        </tr>
                        <tr>
                            <th>내용</th>
                            <td>
                                <textarea value={notice.noticeContent} name="noticeContent" onChange={handleChange}></textarea>
                            </td>
                        </tr>
                    </tbody>
                </table> <br /><br />
                <div align="center">
                    <button type="submit" className="btn btn-primary" onClick={updateNotice} >완료</button>
                </div>
            </form>
            <br /><br />

        </div>
    )
}

export default NoticeUpdateFormComponent