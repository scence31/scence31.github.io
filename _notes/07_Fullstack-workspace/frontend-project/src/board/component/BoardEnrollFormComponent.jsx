import { useState } from "react"
import axios from "axios"
import { toast } from "react-toastify"
import { useNavigate } from "react-router-dom"


const BoardEnrollFormComponent = () => {

    const navigate = useNavigate();

    // input, textarea 요소에 입력받을 값을 담을 변수를 State형 변수로 세팅
    let boardInit = {
        boardTitle : "",
        boardContent : "",
        boardWriter : "admin"

    }

    const [board, setBoard] = useState(boardInit);

    // handleChange
    const handleChange = (e) => {

        const {name, value} = e.target;

        const copyBoard = {...board};
        copyBoard[name] = value;

        setBoard(copyBoard);
    }

    // reset
    const reset = () => {

        const copyBoard = {...board};
        copyBoard.boardTitle = "";
        copyBoard.boardContent = "";

        setBoard(copyBoard);
    }

    // insertBoard
    const insertBoard = (e) => {

        e.preventDefault();

        const insertBoardAxios = async () => {

            try {

                const url = "http://localhost:8006/backend/board/insert"
                const method = "post"

                /*
                    Ajax 요청시 첨부파일 같이 보내기(키:밸류 불가능)
                    => FormData - 전용 객체
                    const formData = new FormData(); -> 생성자함수로 생성

                    formData.append("키", 밸류);
                    formData.append("키", 밸류);
                    ...
                    // 이걸 요청시 전달값으로 넘기면 됨
                */
                const formData = new FormData();

                formData.append("boardTitle", board.boardTitle);
                formData.append("boardContent", board.boardContent);
                formData.append("boardWriter", board.boardWriter);

                // FormData 객체에 첨부파일 넣기 - 이미지 미리보기 비슷
                let upfile = document.getElementsByName("upfile")[0];
                // name 속성값은 중복될 수 있어서 여러 개를 한번에 골라낼 수 있으므로
                // [0]으로 한 개의 요소 객체만 저확히 골라내야 함
                // console.log(upfile.files);
                // <input type="file"> 요소 객체는 입력받은 파일의 정보가 files 배열 속성에
                // 입력한 파일 있으면 files.length == 1 or == 0
                // console.log(upfile.files.length);
                // 파일을 여러 개 입력받는다면 files 배열의 0번 인덱스부터 정보가 담겨서 꺼내쓰면 됨
                if(upfile.files.length == 1) {

                    formData.append("upfile", upfile.files[0])
                    // 파일 여러개면 "upfile1", "upfile2", ...
                    // if는 > 0

                } 

                const response = await axios({
                    url,
                    method,
                    data : formData,
                    headers : {"Content-Type" : "multipart/form-data"} // 요청시 전달값 중 첨부파일이 있다는 것을 알려줌
                })

                // console.log(response.data);
                toast.info(response.data);
                navigate("/board/list");
                


            } catch {


            }
        }

        insertBoardAxios();

    }


    return (
        <div>
            <h2 align="center">일반게시글 작성</h2> <br /><br />
            <form id="enroll-form">
                <table className="form">
                    <tbody>
                        <tr>
                            <th>제목</th>
                            <td>
                                <input type="text" name="boardTitle" value={board.boardTitle} onChange={handleChange} />
                            </td>
                        </tr>
                        <tr>
                            <th>내용</th>
                            <td>
                                <textarea name="boardContent" value={board.boardContent} onChange={handleChange}></textarea>
                            </td>
                        </tr>
                        <tr>
                            <th>첨부파일</th>
                            <td>
                                <input type="file" name="upfile" />
                            </td>
                        </tr>
                    </tbody>
                </table> <br /><br />

                <div align="center">
                    <button type="submit" onClick={insertBoard}>작성</button> &nbsp;
                    <button type="reset" onClick={reset}>초기화</button>
                </div> <br /><br />
            </form>
        </div>
    )
}

export default BoardEnrollFormComponent