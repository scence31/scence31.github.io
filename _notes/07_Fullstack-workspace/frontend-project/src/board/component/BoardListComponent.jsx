import { useState, useEffect } from "react";
import axios from "axios";
import { Link, useNavigate } from "react-router-dom";

const BoardListComponent = () => {

    const navigate = useNavigate();

    const [keyword, setKeyword] = useState("");
    const [dataList, setDataList] = useState([]);
    const [pageList, setPageList] = useState([]);
    const [cpage, setCpage] = useState(1);

    // ================================
    // ⭐ 공통 함수 1: TR 리스트 생성
    // ================================
    const makeTableRows = (list) => {
        return list.map((item, index) => (
            <tr key={index} onClick={() => {navigate(`/board/detail/${item.boardNo}`)}}>
                <td>{item.boardNo}</td>
                <td>{item.boardTitle}</td>
                <td>{item.boardWriter}</td>
                <td>{item.count}</td>
                <td>{item.createDate}</td>
                <td>{item.originName ? "★" : ""}</td>
            </tr>
        ));
    };

    // ================================
    // ⭐ 공통 함수 2: 페이지 버튼 생성
    // ================================
    const makePagingButtons = (pi) => {
        const btnArr = [];

        // prev
        if (cpage === 1) {
            btnArr.push(<Link key="prev" className="btn btn-info btn-sm">&lt;</Link>);
        } else {
            btnArr.push(
                <Link key="prev" className="btn btn-outline-info btn-sm" onClick={() => setCpage(cpage - 1)}>
                    &lt;
                </Link>
            );
        }

        // page numbers
        for (let p = pi.startPage; p <= pi.endPage; p++) {
            if (cpage === p) {
                btnArr.push(<Link key={p} className="btn btn-info btn-sm">{p}</Link>);
            } else {
                btnArr.push(
                    <Link key={p} className="btn btn-outline-info btn-sm" onClick={() => setCpage(p)}>
                        {p}
                    </Link>
                );
            }
        }

        // next
        if (cpage === pi.maxPage) {
            btnArr.push(<Link key="next" className="btn btn-info btn-sm">&gt;</Link>);
        } else {
            btnArr.push(
                <Link key="next" className="btn btn-outline-info btn-sm" onClick={() => setCpage(cpage + 1)}>
                    &gt;
                </Link>
            );
        }

        return btnArr;
    };

    // ================================
    // 📌 검색 조회 AJAX
    // ================================
    const searchBoardListAxios = async () => {
        try {
            const response = await axios({
                url: "http://localhost:8006/backend/board/search",
                method: "get",
                params: { keyword, cpage },
            });

            const { pi, list } = response.data;
            setDataList(makeTableRows(list));
            setPageList(makePagingButtons(pi));

        } catch (e) {
            console.log("검색 실패:", e);
        }
    };

    // ================================
    // 📌 일반 목록 조회 AJAX
    // ================================
    const selectBoardListAxios = async () => {
        try {
            const response = await axios({
                url: "http://localhost:8006/backend/board/list",
                method: "get",
                params: { cpage },
            });

            const { pi, list } = response.data;
            setDataList(makeTableRows(list));
            setPageList(makePagingButtons(pi));

        } catch (e) {
            console.log("목록조회 실패:", e);
        }
    };

    // ================================
    // 📌 useEffect → keyword or cpage 변경 시 실행
    // ================================
    useEffect(() => {
        if (keyword === "") {
            selectBoardListAxios();
        } else {
            searchBoardListAxios();
        }
    }, [cpage]);

    // 변경 핸들러
    const handleChange = (e) => {
        setKeyword(e.target.value);
    };

    // 검색 버튼
    const searchBoardList = (e) => {
        e.preventDefault();
        setCpage(1);  // 검색은 항상 1페이지부터
        searchBoardListAxios();
    };

    return (
        <div>
            <h2 align="center">일반게시글 목록 조회</h2> <br /><br />

            {/* 검색창 */}
            <div align="center" className="search-area">
                <form>
                    <input
                        type="search"
                        name="keyword"
                        value={keyword}
                        onChange={handleChange}
                        placeholder="제목검색"
                    />
                    <button type="submit" onClick={searchBoardList}>검색</button>
                </form>
            </div>

            <br /><br />

            {/* 작성 버튼 */}
            <div align="right" style={{ width: "950px" }}>
                <button onClick={() => {navigate("/board/enrollForm")}} className="btn btn-secondary btn-sm">작성</button>
            </div>

            {/* 테이블 */}
            <table className="list-area table table-hover">
                <thead>
                    <tr>
                        <th width="150">글번호</th>
                        <th width="500">제목</th>
                        <th width="200">작성자</th>
                        <th width="150">조회수</th>
                        <th width="200">작성일</th>
                        <th width="150">첨부파일</th>
                    </tr>
                </thead>
                <tbody>{dataList}</tbody>
            </table>

            <br /><br />

            {/* 페이징바 */}
            <div className="paging-area" align="center">
                {pageList}
            </div>

            <br /><br />
        </div>
    );
};

export default BoardListComponent;
