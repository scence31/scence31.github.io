import { useState, useEffect } from "react"
import { useParams, useNavigate } from "react-router-dom"
import { toast } from "react-toastify"

import { boardIncreaseCountAxios, deleteBoardAxios } from "../api/boardApi"

const BoardDetailComponent = () => {

    const navigate = useNavigate();

    // 글번호 뽑아오기 (get - useParams, post - location)
    const boardNo = useParams().boardNo;
    // console.log(boardNo);

    // State형 변수 세팅
    const boardInit = {
        boardNo : "",
        boardTitle : "",
        boardContent : "",
        boardWriter : "",
        createDate : ""
    }

    const [data, setData] = useState(boardInit);

    useEffect(() => {

        // 게시글 상세조회 흐름
        // 1. 조회수 증가 먼저 하기
        // 2. 조회수 증가되면 단일행 조회
        // 3. 옵션) 만약 첨부파일 별개로 있으면 조회해야 함 - 근데 지금은 테이블에 포함되어있어서 안해도 됨

        const api = async () => {

            const item = await boardIncreaseCountAxios(boardNo);

            if(item != null) {

                setData(item);

            } else {

                toast.info("없는 게시글")

                navigate("/board/list")
            }

        }

        api();
        
    }, [])

    // 삭제버튼 클릭시 실행할 함수
    const deleteBoard = () => {

        const api = async () => {
        
            const msg = await deleteBoardAxios(boardNo);

            toast.info(msg);

            navigate("/board/list")

        }

        api();
    }

    return (
        <div>
            <h2 align="center">게시글 상세조회</h2> <br /><br />
            <table className="table">
                <tbody>
                    <tr>
                        <th>제목</th>
                        <td colSpan={3}>{data.boardTitle}</td>
                    </tr>
                    <tr>
                        <th>작성자</th>
                        <td>{data.boardWriter}</td>
                        <th>작성일</th>
                        <td>{data.createDate}</td>
                    </tr>
                    <tr>
                        <th>내용</th>
                        <td colSpan={3}>
                            <p style={{height : '250px'}}>
                                {data.boardContent}
                            </p>
                        </td>
                    </tr>
                    <tr>
                        <th>첨부파일</th>
                        <td colSpan={3}>
                            {/* 첨부파일 있으면 있다고 or .. 출력 */}
                            {
                                (data.originName != null) ? (<a href={`http://localhost:8006/backend/board/download/${data.changeName}`}>{data.originName}</a>)
                                : "첨부파일 없음"
                            }
                        </td>
                    </tr>
                </tbody>
            </table> <br /><br />
            <div align="center">
                <button onClick={() => {navigate("/board/list")}}>목록</button> &nbsp;
                <button onClick={() => {navigate(`/board/updateForm`, {state : {boardNo}})}}>수정</button> &nbsp;
                <button onClick={deleteBoard}>삭제</button>
            </div>
        </div>
    )
}

export default BoardDetailComponent