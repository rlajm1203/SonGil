const apiUrl = "http://localhost:8080/api/v1/helps";
let currentPage = 1; // 현재 페이지 번호
const pageSize = 5;  // 페이지당 항목 수

// 도움 요청 리스트 불러오기
function fetchHelpList(page, size) {
    const url = `${apiUrl}?page=${page}&size=${size}`;
    // const accessToken = localStorage.getItem("accessToken");
    fetch(url)
        .then(response => {
            if (!response.ok) {
                throw new Error("API 호출 실패");
            }
            return response.json();
        })
        .then(data => {
            if (data.code === "200") {
                renderHelpList(data.data.responses);
                renderPagination(page, data.data.responses.length < size);
            } else {
                alert(data.message || "도움 리스트를 불러오는 데 실패했습니다.");
            }
        })
        .catch(error => {
            console.error("도움 요청 리스트 로드 오류:", error);
        });
}

// 도움 리스트 렌더링
function renderHelpList(helpItems) {
    const helpListContainer = document.getElementById("help-list");
    helpListContainer.innerHTML = ""; // 기존 내용 초기화

    if (helpItems.length === 0) {
        helpListContainer.innerHTML = "<p>요청된 도움이 없습니다.</p>";
        return;
    }

    helpItems.forEach(item => {
        const helpItemDiv = document.createElement("div");
        helpItemDiv.classList.add("help-item");

        helpItemDiv.innerHTML = `
            <h3>${item.title}</h3>
            <p><strong>도움 유형:</strong> ${item.helpType}</p>
            <p><strong>카테고리:</strong> ${item.helpCategory}</p>
            <p><strong>내용:</strong> ${item.content}</p>
            <p><strong>주소:</strong> ${item.address}</p>
            <p><strong>가격:</strong> ${item.price}원</p>
            <p><strong>상태:</strong> ${item.status}</p>
        `;
        helpListContainer.appendChild(helpItemDiv);
    });
}

// 페이지네이션 렌더링
function renderPagination(currentPage, isLastPage) {
    const paginationContainer = document.getElementById("pagination");
    paginationContainer.innerHTML = ""; // 기존 버튼 초기화

    if (currentPage > 1) {
        const prevButton = document.createElement("button");
        prevButton.textContent = "이전";
        prevButton.onclick = () => {
            fetchHelpList(currentPage - 1, pageSize);
        };
        paginationContainer.appendChild(prevButton);
    }

    if (!isLastPage) {
        const nextButton = document.createElement("button");
        nextButton.textContent = "다음";
        nextButton.onclick = () => {
            fetchHelpList(currentPage + 1, pageSize);
        };
        paginationContainer.appendChild(nextButton);
    }
}

// 초기 로드
document.addEventListener("DOMContentLoaded", () => {
    if(!localStorage.getItem("accessToken")){
        alert("로그인이 필요합니다.");
        window.location="/";
    }
    fetchHelpList(currentPage, pageSize);
});