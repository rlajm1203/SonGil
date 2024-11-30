let selectedDays = [];
let selectedCategory = null;

// 요일 선택/해제
function toggleDay(element) {
    const day = element.innerText;
    if (selectedDays.includes(day)) {
        selectedDays = selectedDays.filter(d => d !== day); // 선택 해제
        element.classList.remove('selected');
    } else {
        selectedDays.push(day); // 선택
        element.classList.add('selected');
    }
}

// 도움 카테고리 선택
function selectCategory(element) {
    // 이전에 선택된 카테고리 해제
    const previous = document.querySelector('.category.selected');
    if (previous) previous.classList.remove('selected');

    // 현재 선택된 카테고리 설정
    selectedCategory = element.innerText;
    element.classList.add('selected');
}

// 다음 단계로 이동
function proceedToOnceModal() {
    if (selectedDays.length === 0 || !selectedCategory) {
        alert('요일과 도움 종류를 선택해주세요.');
        return;
    }

    // 다음 모달 열기
    closeDetailsModal();
    openOnceModal();
}

// 모달 로드 함수
function loadModal(modalId, filePath) {
    fetch(filePath)
        .then(response => {
            if (!response.ok) {
                throw new Error('모달 파일을 로드하는 데 실패했습니다.');
            }
            return response.text();
        })
        .then(html => {
            const container = document.createElement('div');
            container.innerHTML = html;
            document.body.appendChild(container);
            initializeModal(modalId); // 모달 초기화
        })
        .catch(error => console.error('모달 로드 오류:', error));
}

function loadCSS(filePath) {
    const link = document.createElement('link');
    link.rel = 'stylesheet';
    link.type = 'text/css';
    link.href = filePath;
    document.head.appendChild(link);
}

// 모달 초기화 함수
function initializeModal(modalId) {
    const modal = document.getElementById(modalId);
    if (modal) {
        modal.style.display = 'none'; // 초기 상태 설정

        // 화면 아무 곳이나 클릭하면 모달 닫기
        modal.addEventListener('click', (event) => {
            if (event.target === modal) {
                closeModal(modalId);
            }
        });
    } else {
        console.error(`모달 초기화 실패: ${modalId}를 찾을 수 없습니다.`);
    }
}

// 메인 모달 열기
function openMainModal() {
    const modal = document.getElementById('once-help-main-modal');
    if (modal) {
        modal.style.display = 'flex';
    } else {
        console.error('Main Modal을 찾을 수 없습니다.');
    }
}

// 메인 모달 닫기
function closeMainModal() {
    const modal = document.getElementById('once-help-main-modal');
    if (modal) {
        modal.style.display = 'none';
    } else {
        console.error('Main Modal을 찾을 수 없습니다.');
    }
}

// 세부사항 모달 열기
function openDetailsModal() {
    closeMainModal();
    const modal = document.getElementById('help-details-modal');
    if (modal) {
        modal.style.display = 'flex';
    } else {
        console.error('Details Modal을 찾을 수 없습니다.');
    }
}

function closeDetailsModal() {
    const detailModal = document.getElementById('help-details-modal');
    detailModal.classList.remove('active');
    detailModal.style.display = 'none';
}
// 도움 한 번 요청하기 모달 열기
function openOnceModal() {
    closeDetailsModal();
    const modal = document.getElementById('once-modal');
    if (modal) {
        modal.style.display = 'flex';
    } else {
        console.error('Once Modal을 찾을 수 없습니다.');
    }
}

function closeModal(modalId){
    document.getElementById(modalId).style.display = 'none';
}

function closeOnceModal() {
    document.getElementById('once-modal').classList.remove('active');
}

// 도움 요청하기 폼 처리
document.addEventListener('DOMContentLoaded', () => {
    loadCSS('/css/modals/help-request-modal.css');
    loadCSS('/css/modals/help-details-modal.css');
    loadModal('once-help-main-modal', '/html/modals/once-help-main-modal.html');
    loadModal('once-modal', '/html/modals/once-help-request-modal.html');
    loadModal('help-details-modal', '/html/modals/help-details-modal.html'); // 파일 경로 확인

    document.body.addEventListener('submit', event => {
        if (event.target.id === 'help-form') {
            event.preventDefault();

            const helpType = 'once';
            const title = document.getElementById('title').value;
            const address = document.getElementById('address').value;
            const content = document.getElementById('content').value;
            const price = parseInt(document.getElementById('price').value, 10);
            const accessToken = localStorage.getItem("accessToken");

            if(!accessToken){
                new Error("로그인이 필요합니다.");
                return;
            }

            const data = {
                helpType,
                dayOfWeek: selectedDays,
                category : selectedCategory,
                title,
                address,
                content,
                price
            };

            fetch('http://localhost:8080/api/v1/helps', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': `Bearer ${accessToken}`
                },
                body: JSON.stringify(data)
            })
                .then(response => {
                    if (!response.ok) throw new Error('요청 실패');
                    return response.json();
                })
                .then(result => {
                    alert('도움 요청이 성공적으로 등록되었습니다.');
                    closeModal('once-modal');
                })
                .catch(error => {
                    console.error('오류:', error);
                    alert('도움 요청에 실패했습니다. 다시 시도해주세요.');
                });
        }
    });
});