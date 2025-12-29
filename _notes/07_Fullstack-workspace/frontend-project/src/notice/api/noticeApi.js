import axios from "axios";
 
 const selectNoticeAxios = async (noticeNo) => {

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

 }

 export { selectNoticeAxios}