import { useState, useEffect } from "react";
import axios from "axios";

const AxiosComponent3 = () => {

    // 실행할 구문
    // 1. 응답데이터를 표현할 tr 요소들이 담겨있을 배열을 State형 변수로 세팅
    const [dataList, setDataList] = useState([]);

    // 2. 맨 처음
    useEffect(() => {

        // 요청할 주소 http://apis.data.go.kr/6260000/FoodService/getFoodKr / 요청시 전달값(요청변수) ServiceKey, pageNo, numOfRows, resultType / 요청방식 get
        
        let url = 'http://apis.data.go.kr/6260000/FoodService/getFoodKr';
        const key = '00d2893717c3abcb4d0a95371a23f4cbd8293107f3354028ea277be8baf6e5e0';

        url += '?ServiceKey=' + key;
        url += '&pageNo=1';
        url += '&numOfRows=10';
        url += '&resultType=json';

        // console.log(url)

        // 3. axios
        axios({
            url : url,
            method : "get"
            /*
            params : {
                ServiceKey : key,
                pageNo : 1,
                numOfRows : 10,
                resultType : 'json'
            }
                */

        }).then((response) => {

            // 데이터 추출
            const items = response.data.getFoodKr.item;

            // console.log(response.data.getFoodKr.item);
            const trArr = items.map((item, index) => {

                return (

                <tr key={index}>
                    <td>{item.MAIN_TITLE}</td>
                    <td>{item.ADDR1}</td>
                    <td>{item.CNTCT_TEL}</td>
                    <td>{item.ITEMCNTNTS}</td>
                    <td>
                        <img src={item.MAIN_IMG_THUMB} width={150} />
                    </td>
                </tr>
            )
            })

            setDataList(trArr);

        }).catch(() => {

            console.log('ajax 통신실패');
        });

    }, []);

    return (
        <div>
            <h4>부산 맛집</h4>
            <table border={1}>
                <thead>
                    <tr>
                        <th width='150'>가게명</th>
                        <th width='260'>주소</th>
                        <th width='120'>전화번호</th>
                        <th width='310'>설명</th>
                        <th>대표이미지</th>
                    </tr>
                </thead>
                <tbody>
                    {dataList}
                </tbody>
            </table>
        </div>
    )
}

export default AxiosComponent3