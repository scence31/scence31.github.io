import { useState, useEffect } from "react";
import axios from "axios";


const AxiosComponent2 = () => {

    // 1. 응답데이터를 tr 요소로 감싸 담을 State형 배열 세팅
    const [dataList, setDataList] = useState([]);

    // 2. 이 컴포넌트가 최초로딩 한 번 실행할 훅 함수 세팅
    useEffect(() => {

        axios({
            url : 'https://my-json-server.typicode.com/typicode/demo/comments',
            method : 'get'
            // params 생략

        }).then((response) => {

            // console.log(response.data) // [{}, {}, ..]
            // 4. tbody 영역에 들어갈 tr 요소들 전환하기
            const trArr = response.data.map((item, index) => {

                return (
                    <tr key={index}>
                        <td>{item.id}</td>
                        <td>{item.body}</td>
                        <td>{item.postId}</td>
                    </tr>
                );
            })

            // 5. 세팅된 tr 배열을 setter로 State형 변수에 담기
            setDataList(trArr);


        }).catch(() => {

            console.log('통신 실패');
        });

    }, []);

    return (
        <div>
            <h4>JSOM 샘플 데이터 2 출력</h4>
            <table border={1}>
                <thead>
                    <tr>
                        <th>id</th>
                        <th>body</th>
                        <th>postId</th>
                    </tr>
                </thead>
                <tbody>
                    {
                        // 6.
                        dataList
                    }
                </tbody>
            </table>
        </div>
    )
}

export default AxiosComponent2