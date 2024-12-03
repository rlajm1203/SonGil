let selectedDays = [];
let selectedCategory = null;

// 요일 선택/해제
function toggleDay(element) {
    const day = element.innerText;
    const isSelected = selectedDays.includes(day);

    selectedDays = isSelected
        ? selectedDays.filter(d => d !== day) // 선택 해제
        : [...selectedDays, day]; // 선택

    element.classList.toggle('selected', !isSelected);
}

// 도움 카테고리 선택
function selectCategory(element) {
    document.querySelector('.category.selected')?.classList.remove('selected');
    selectedCategory = element.innerText;
    element.classList.add('selected');
}

// 공통 모달 열기/닫기 함수
function toggleModal(modalId, isOpen=true) {
    const modal = document.getElementById(modalId);
    if (modal) {
        modal.style.display = isOpen ? 'flex' : 'none';
    } else {
        console.error(`Modal not found: ${modalId}`);
    }
}

function closeModal(modalId) {
    toggleModal(modalId, false);
}

function openMainModal() {
    toggleModal('once-help-main-modal', true);
}

function closeMainModal() {
    closeModal('once-help-main-modal');
}

function openDetailsModal(isOnce) {
    closeMainModal();
    toggleModal(isOnce ? 'once-help-details-modal' : 'help-details-modal', true);
}

function closeDetailsModal() {
    closeModal('once-help-details-modal');
    closeModal('help-details-modal');
}

function openOnceModal() {
    closeDetailsModal();
    toggleModal('once-modal', true);
}

function closeOnceModal() {
    closeModal('once-modal');
}

function openLongTermModal() {
    closeModal('once-help-main-modal');
    toggleModal('long-term-modal', true);
}

// CSS 및 HTML 파일 로드
function loadCSS(filePath) {
    const link = document.createElement('link');
    link.rel = 'stylesheet';
    link.href = filePath;
    document.head.appendChild(link);
}

function loadModal(modalId, filePath) {
    return fetch(filePath)
        .then(response => response.ok ? response.text() : Promise.reject('Failed to load modal'))
        .then(html => {
            const container = document.createElement('div');
            container.innerHTML = html;
            document.body.appendChild(container);
            initializeModal(modalId);
        })
        .catch(error => console.error('Error loading modal:', error));
}

// 다음 단계로 이동
function proceedToOnceModal(isOnce) {
    if (isOnce) {
        selectedDays.push(getCurrentDayInKorean());
    } else {
        if (selectedDays.length === 0 || !selectedCategory) {
            alert('요일과 도움 종류를 선택해주세요.');
            return;
        }
    }

    // 다음 모달 열기
    closeDetailsModal();
    openOnceModal();
}

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
        console.error("모달 초기화 실패: ${modalId}를 찾을 수 없습니다.");
    }
}

// 도움 요청 폼 제출
function handleSubmitForm(formId, apiEndpoint, helpType) {
    const form = document.getElementById(formId);

    form?.addEventListener('submit', (event) => {
        event.preventDefault();

        const accessToken = localStorage.getItem("accessToken");
        if (!accessToken) {
            alert('로그인이 필요합니다.');
            return;
        }

        const data = {
            helpType,
            dayOfWeek: selectedDays,
            category: selectedCategory,
            title: form.title.value,
            address: form.address.value,
            content: form.content.value,
            price: parseInt(form.price.value, 10)
        };

        fetch(apiEndpoint, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${accessToken}`
            },
            body: JSON.stringify(data)
        })
            .then(response => response.ok ? response.json() : Promise.reject('Request failed'))
            .then(() => {
                alert(`${helpType === 'once' ? '도움 요청' : '장기 도움 요청'}이 성공적으로 등록되었습니다.`);
                closeModal(helpType === 'once' ? 'once-modal' : 'long-term-modal');
            })
            .catch(error => {
                console.error('Request error:', error);
                alert('도움 요청에 실패했습니다. 다시 시도해주세요.');
            });
    });
}

// 현재 요일 가져오기
function getCurrentDayInKorean() {
    const daysInKorean = ['일', '월', '화', '수', '목', '금', '토'];
    return daysInKorean[new Date().getDay()];
}

// DOMContentLoaded
document.addEventListener('DOMContentLoaded', async () => {
    loadCSS('/css/modals/help-request-modal.css');
    loadCSS('/css/modals/help-details-modal.css');

    await Promise.all([
        loadModal('once-help-main-modal', '/html/modals/once-help-main-modal.html'),
        loadModal('once-help-details-modal', '/html/modals/once-help-details-modal.html'),
        loadModal('help-details-modal', '/html/modals/help-details-modal.html'),
        loadModal('once-modal', '/html/modals/once-help-request-modal.html'),
        loadModal('long-term-modal', '/html/modals/long-term-help-request-modal.html')
    ]);


    handleSubmitForm('once-help-form', '/api/v1/helps', 'once');
    handleSubmitForm('long-term-form', '/api/v1/helps', 'regular');
});