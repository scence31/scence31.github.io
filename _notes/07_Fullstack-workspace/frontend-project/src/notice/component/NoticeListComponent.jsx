import {useState, useEffect} from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

const NoticeListComponent = () => {

    // navigate
    const navigate = useNavigate();

    // 1. tr요소 담긴 State형 변수 세팅
    const [dataList, setDataList] = useState([]);

    // 2. 최초 로딩, 1번 실행
    useEffect(() => {

        // 3. 목록조회 ajax
        // 변수명 = async () => { try {응답정보 = await axios({요청정보}) 성공시 실행할 후처리 구문;} catch { 실패 시 실행}};  변수명(); -> 호출
        
        const selectNoticeListAxios = async () => {

            // url, 전송방식
            const url = 'http://localhost:8006/backend/notice/list'
            const method = 'get';

            try {

                const response = await axios({
                    url,
                    method
                });

                // console.log(response.data)
                const items = response.data;
                const trArr = items.map((item, index) => {

                    // console.log(item)
                    return (
                        <tr key={index} onClick={() =>{navigate(`/notice/detail/${item.noticeNo}`)}}>
                            <td>{item.noticeNo}</td>
                            <td>{item.noticeTitle}</td>
                            <td>{item.noticeWriter}</td>
                            <td>{item.createDate}</td>
                        </tr>
                    )
                })

                // 출력을 위한 state형 변수 대입
                setDataList(trArr);
                

            } catch {

                console.log("공지사항 조회 ajax 통신실패");

            }


        };

        selectNoticeListAxios();

    }, []);


    return (
        <div>
            <h2 align="center">공지사항 목록 조회</h2> <br /><br />
            <div align="right" style={{width : '950px'}}>
                <button type='button' className='btn btn-primary btn-sm'
                        onClick={() => {navigate("/notice/enrollForm")}}>작성</button>
            </div>
            <br />
            <table className="list-area table table-hover">
                <thead>
                    <tr>
                        <th width="150">글번호</th>
                        <th width="500">제목</th>
                        <th width="200">작성자</th>
                        <th width="200">작성일</th>
                    </tr>
                </thead>
                <tbody>
                    {dataList}
                </tbody>
            </table> <br /><br />


        </div>

    )
}

export default NoticeListComponent