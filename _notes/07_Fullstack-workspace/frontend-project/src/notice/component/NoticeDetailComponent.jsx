
import { useState, useEffect } from "react"
import axios from "axios";
import { useParams, useNavigate } from "react-router-dom";
import { toast } from "react-toastify";

const NoticeDetailComponent = () => {

    // navigate 얻기
    let navigate = useNavigate();

    // Path Variable 방식으로 전달한 글번호 받기
    let noticeNo = useParams().noticeNo;
    // console.log(noticeNo);

    let noticeInit = {
        noticeNo : "",
        noticeTitle : "",
        noticeWriter : "",
        noticeContent : "",
        createDate : ""
    }

    const [notice, setNotice] = useState(noticeInit);

    useEffect(() => {

        const selectNoticeAxios = async (NoticeDetailComponent) => {

            // url, 요청방식 세팅
            let url = `http://localhost:8006/backend/notice/detail/${noticeNo}`;
            let method = 'get';

            try {

                const response = await axios({
                    url,
                    method
                });

                // console.log(response.data)
                const item = response.data;
                
                if(item === "") {

                    toast.info("없는 공지사항");

                    // 공지사항 목록조회 페이지로 이동시키기
                    navigate("/notice/list");

                } else {

                    setNotice(item);

                }

            } catch {

                console.log("공지 상세조회 ajax 실패");
            }

        };

        selectNoticeAxios();
        
    }, []);

    // 수정버튼 실행 함수
    const updateNoticeForm = () => {

        // console.log('클릭수정')
        navigate(`/notice/updateForm`, {state : {noticeNo : noticeNo}})
    }

    // 삭제버튼 실행 함수
    const deleteNotice = () => {

        const deleteNoticeAxios = async () => {

            const url = `http://localhost:8006/backend/notice/delete`;
            const method = 'post';

            try {

                const response = await axios({
                    url,
                    method,
                    data : {noticeNo : noticeNo}

                })

                console.log(response.data)

                toast.info("삭제완료");
                navigate("/notice/list");


            } catch {

                console.log('ajax 삭제 실패')
                
            }

        } 

        deleteNoticeAxios();
    }

    return (
        <div>
            <h2 align="center">공지사항 상세조회</h2> <br /><br />

            <table className="table">
                <tbody>
                    <tr>
                        <th>제목</th>
                        <td colSpan={3}>{notice.noticeTitle}</td>
                    </tr>
                    <tr>
                        <th>작성자</th>
                        <td>{notice.noticeWriter}</td>
                        <th>작성일</th>
                        <td>{notice.createDate}</td>
                    </tr>
                    <tr>
                        <th>내용</th>
                        <td colSpan={3}>
                            <p style={{height : '200px'}}>
                                {notice.noticeContent}
                            </p>
                        </td>
                    </tr>
                </tbody>
            </table>
            <br /><br />
            <div align="center">
                <button onClick={() => {navigate("/notice/list")}}>목록</button> &nbsp;
                <button onClick={updateNoticeForm}>수정</button> &nbsp;
                <button onClick={deleteNotice}>삭제</button> &nbsp;
            </div>
            <br /><br />
        </div>
    )
}

export default NoticeDetailComponent