import { useState, useEffect } from "react"
import { useNavigate, useLocation } from "react-router-dom"

import { selectBoardAxios, updateBoardAxios } from "../api/boardApi";

const BoardUpdateFormComponent = () => {

    // 해당 게시글 상세조회 먼저
    let location = useLocation();
    let boardNo = location.state.boardNo;

    useEffect(() => {

        const api = async () => {

            const item = await selectBoardAxios(boardNo);

            setBoard(item);
        }

        api();

    }, [])

    const navigate = useNavigate();

    const boardInit = {
        boardNo : "",
        boardTitle : "",
        boardContent : ""
    }

    const [board, setBoard] = useState(boardInit);

    const handleChange = (e) => {

        const {name, value} = e.target;

        const copyBoard = {...board}
        copyBoard[name] = value;

        setBoard(copyBoard);

        // console.log(copyBoard)

    }

    // 수정버튼 클릭시 실행할 함수
    const updateBoard = (e) => {

        e.preventDefault();

        const api = async () => {

            // 요청시 전달값을 매개변수 인자로 넘길 것
            // 세팅 - 글번호, 제목, 내용, 원본파일명, 수정파일명
            // + 새로 파일 넘길 경우 파일 자체도 전달값으로 넘겨야 함
            const formData = new FormData();

            formData.append("boardNo", board.boardNo);
            formData.append("boardTitle", board.boardTitle);
            formData.append("boardContent", board.boardContent);

            // 원본, 수정파일명은 기존 첨부파일만 있는 경우에만 세팅함
            if(board.originName != null) {

                formData.append("originName", board.originName);
                formData.append("changeName", board.changeName);
            }

            // reupfile 정보 넘기기
            const reupfile = document.getElementsByName("reupfile")[0];

            if(reupfile.files.length == 1) {

                formData.append("reupfile", reupfile.files[0]);
            }

            await updateBoardAxios(formData);

            navigate(`/board/detail/${boardNo}`)
    }

    api();
}

    return (
        <div>
            <h2 align="center">게시글 수정</h2> <br /><br />

            <form id="update-form"></form>
            <table className="form">
                <tbody>
                    <tr>
                        <th>제목</th>
                        <td>
                            <input type="text" name="boardTitle" onChange={handleChange} value={board.boardTitle} />
                        </td>
                    </tr>
                    <tr>
                        <th>내용</th>
                        <td>
                            <textarea name="boardContent" onChange={handleChange} value={board.boardContent}></textarea>
                        </td>
                    </tr>
                    <tr>
                        <th>첨부파일</th>
                        <td>
                            {(board.originName != null) ? (<a href={`http://localhost:8006/backend/board/download/${board.changeName}`}>{board.originName}</a>)
                            : "첨부파일 없음"}
                            <input type="file" name="reupfile"/>
                        </td>
                    </tr>
                </tbody>
            </table> <br /><br />

            <div align="center">
                <button type="submit" onClick={updateBoard} >수정</button> &nbsp;
                <button type="button" onClick={() => {navigate(`/board/detail/${boardNo}`)}}>취소</button>
            </div>

        </div>
    )
}

export default BoardUpdateFormComponent