import './ReplyInsertComponent.css';

const ReplyInsertComponent = (props) => {

    // console.log(props);
    const {replyList, setReplyList} = props;

    const insertReply = (e) => {

        // console.log(e.target);
        e.preventDefault();

        // 사용자가 아래 입력한 본인 이름, 댓글내용을 뽑아서 변수에 담기
        let replyWriter = document.querySelector('#replyWriter').value;
        let replyContent = document.querySelector("#replyContent").value;

        // 위 두 개를 하나의 댓글객체로 묶기 - 객체확장표현식 문법 적용(변수명이 알아서 속성명으로)
        let replyItem = {
            replyWriter,
            replyContent
        };

        // replyItem을 replyList에 추가
        // 1. 기존 댓글리스트를 깊은복사
        const newReplyList = [...replyList];

        // 2. 깊은복사본 배열에 replyItem 추가
        newReplyList.push(replyItem);

        // 3. setter 함수로 배열 덮어씌우기
        setReplyList(newReplyList);

    }

    return (
        <div>
            <form>
                <table id="replyInsertForm">
                    <tbody>
                        <tr>
                            <th>이름</th>
                            <td>
                                <input type="text" id="replyWriter" />
                            </td>
                        </tr>
                        <tr>
                            <th>내용</th>
                            <td>
                                <textarea id="replyContent" placeholder="300자 이내로 입력"></textarea>
                            </td>
                        </tr>
                        <tr>
                            <th></th>
                            <td>
                                <button type="submit" onClick={insertReply}>작성하기</button>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </form>
        </div>
    )
}

export default ReplyInsertComponent