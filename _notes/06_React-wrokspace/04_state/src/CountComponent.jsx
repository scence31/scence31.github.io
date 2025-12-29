import { useState } from "react";
import './CountComponent.css';

const CountComponent = () => {

    
    const [count, setCount] = useState(0);

    

    const countPlus = () => {

        // console.log(count);
        setCount(count + 1);
    }

    const countMinus = () => {

        if(count > 0) {

            setCount(count - 1);

        } // else { count = 0; }
    }

    // 인라인 스타일을 부여하기 위한 객체
    let styleObj = {
        width : '50px',
        height : '30px',
        textAlign : 'center',
        boxSizing : 'border-box'
    };

    return (

        <div>
            <button onClick={countMinus} className="countBtn">-</button>
            <input style={styleObj} type="text" readOnly value={count}></input>
            <button onClick={countPlus} className="countBtn">+</button>
        </div>
    )
}

export default CountComponent