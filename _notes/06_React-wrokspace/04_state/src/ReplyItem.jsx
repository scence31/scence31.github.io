import "./ReplyItem.css";

// 댓글항목 하나를 나타내는 컴포넌트
const ReplyItem = (props) => {

    // 실행할구문
    const {replyWriter, replyContent} = props


    return(
        <div className="replyItem">
            <div>
                <span className="material-symbols-outlined">face</span>
                <span>{replyWriter}</span>
            </div>
            <div>
                {replyContent}
            </div>
        </div>
    )
}

export default ReplyItem