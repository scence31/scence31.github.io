import axios from "axios"

// 게시글 조화수 증가
const boardIncreaseCountAxios = async (boardNo) => {

    try {

        const url = `http://localhost:8006/backend/board/increaseCount/${boardNo}`;
        const method = 'get';

        const response = await axios({
            url,
            method,
            headers: {Authorization: `Bearer ${sessionStorage.getItem("loginUser")}`}
            
        })

        // console.log(response.data);
        if(response.data > 0) {

            const item = await selectBoardAxios(boardNo);

            return item;

        } else {

            return null;
        }

    } catch(e) {

        console.log("ajax 통신 실패", e);
    }

}

// 게시글 상세조회용 axios 함수
const selectBoardAxios = async (boardNo) => {

    try {

        const url = `http://localhost:8006/backend/board/detail/${boardNo}`
        const method = 'get'

        const response = await axios({
            url,
            method,
            headers: {Authorization: `Bearer ${sessionStorage.getItem("loginUser")}`}
        })

        // console.log(response.data)
        return response.data;


    } catch(e) {

        console.log("상세조회 ajax 실패", e)
    }    

}

const updateBoardAxios = async (formData) => {

    try {

        const url = "http://localhost:8006/backend/board/update";
        const method = 'post';

        const response = await axios({
            url,
            method,
            data : formData,
            headers : {"Content-Type" : "multipart/form-data", Authorization: `Bearer ${sessionStorage.getItem("loginUser")}`}
        })

        // console.log(response.data);

    } catch {

        console.log("실패");
    }
}

// 삭제용 Axios
// 글번호 넘기면서 삭제요청 axios
const deleteBoardAxios = async (boardNo) => {

    try {

        const url = "http://localhost:8006/backend/board/delete";
        const method = 'post';

        const response = await axios({
            url,
            method,
            data : {boardNo},
            headers: {Authorization: `Bearer ${sessionStorage.getItem("loginUser")}`}
        })

        return response.data;


    } catch(e) {

        console.log("삭제 ajax 실패", e)
    }
}

export {selectBoardAxios, boardIncreaseCountAxios, updateBoardAxios, deleteBoardAxios}