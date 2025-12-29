import ReplyItem from './ReplyItem';

// 댓글목록조회용 컴포넌트
const ReplyListComponent = (props) => {

    // console.log(props); // {replyList: [{}, {}, ...]}
    // console.log(props.replyList); // [{}, ...]
    
    const replyList = props.replyList;
    // console.log(replyList); // [{}, ...]
    // replyList 내용을 댓글 한 개를 표현하는 요소로 변환한 다음
    // 그 요소들을 배열에 차곡차곡 담을 것

    return (
        <div>
            {
                replyList.map((item, index) => {

                    return (
                        <ReplyItem  key={index}
                                    replyWriter={item.replyWriter}
                                    replyContent={item.replyContent} />
                    )
                })
                // [<li>첫</li>, <li>둘</li>, <li>셋</li>]
            }
        </div>
    )
}

export default ReplyListComponent