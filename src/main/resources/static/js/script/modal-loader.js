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
        modal.style.display = 'none';

        // 화면 아무 곳이나 클릭하면 모달 닫기
        modal.addEventListener('click', (event) => {
            if (event.target === modal) {
                closeModal(modalId);
            }
        });
    }
}

// 모달 열기/닫기 함수
function openMainModal() {
    document.getElementById('once-help-main-modal').style.display = 'flex';
}

function openOnceModal() {
    document.getElementById('once-help-main-modal').style.display = 'none';
    document.getElementById('once-modal').style.display = 'flex';
}

function closeModal(modalId) {
    document.getElementById(modalId).style.display = 'none';
}

// 도움 요청하기 폼 처리
document.addEventListener('DOMContentLoaded', () => {
    loadCSS('/css/modals/help-request-modal.css');
    loadModal('once-help-main-modal', '/html/modals/once-help-main-modal.html');
    loadModal('once-modal', '/html/modals/once-help-request-modal.html');

    document.body.addEventListener('submit', event => {
        if (event.target.id === 'help-form') {
            event.preventDefault();

            const helpType = 'once';
            const title = document.getElementById('title').value;
            const address = document.getElementById('address').value;
            const content = document.getElementById('content').value;
            const price = parseInt(document.getElementById('price').value, 10);

            const data = {
                helpType,
                dayOfWeek: ["월", "화", "수"], // 고정값
                title,
                address,
                content,
                price
            };

            fetch('http://localhost:8080/api/v1/helps', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
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