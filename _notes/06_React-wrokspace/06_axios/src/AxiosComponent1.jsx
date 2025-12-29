import axios from 'axios';
import {useState, useEffect} from 'react';

const AxiosComponent1 = () => {

    // 실행할 구문
    // axios 라이브러리를 이용해서 응답데이터를 불러와 출력

    // 조회된 데이터들을 tr 요소로 옮긴 후 그 tr 요소들이 담길 배열을 State형 변수로 세팅하기
    const [dataList, setDataList] = useState([]);

    useEffect(() => {

        axios({
            url : 'https://my-json-server.typicode.com/typicode/demo/posts',
            method : 'get'
            // 전달값 없어서 params 생략
        
        }).then((response) => {

            // console.log('ajax 통신 성공');

            // 매개변수로 response 추가
            // console.log(response); // {}
            // 응답 정보들이 객체형식으로 담겨있음

            // 응답데이터만 보고싶다면?
            // console.log(response.data);

            // 이 조회해온 데이터들을 아래 tbody 영역 안에 tr 요소로 출력하면 됨
            // 조회한 데이터들을 tr 요소로 하나씩 옮겨 담은 후 출력

            // 우선 tr 요소들을 담을 배열 생성
            
            const trArr = [];

            for(let i = 0; i < response.data.length; i++) {

                // console.log(response.data[i]);

                trArr.push(
                    <tr key={i}>
                        <td>{response.data[i].id}</td>
                        <td>{response.data[i].title}</td>
                    </tr>)
            }
            // 이대로 하면 실제 응답데이터가 출력되지 않음
            // axios 함수 호출 자체가 아래 return 구문보다 먼저 실행되지만
            // return 구문이 실행되는 순간에도 axios 호출 구문이 아직 안 끝나서
            // 빈 table이 출력되는 것임
            // State형 변수를 활용하면 됨

            setDataList(trArr); // dataList = 어떤값;

        }).catch(() => {

            // console.log('통신실패');

        }).finally(() => {

            // console.log('통신 완료');

        });
    }, []);

    // 무한반복 해결하기 위해 딱 한 번만 실행되는 리액트 구문 사용하기
    // => useEffect() 함수 (훅) - 어떤 컴포넌트가 마운트(렌더링 1빠, 로딩)될 때 딱 한 번 실행 - import
    // useEffect(() => {딱 한번 실행할 구문}, [값을감지할변수명, ...]);

    /* 
        - get 방식은 params 속성에 객체형식으로 - url 주소의 헤더 영역에 데이터 담기
        - post는 data 속성 - url 주소 바디영역       
    */

    return ((
        <div>
            <h4>JSON 샘플데이터 1 출력</h4>
            <table border={1}>
            <thead>
                <tr>
                    <td>id</td>
                    <td>title</td>
                </tr>
            </thead>
            <tbody>
                {dataList}
            </tbody>
            </table>
        </div>
    ))
}

export default AxiosComponent1