document.addEventListener('DOMContentLoaded', function() {
    const calendarGrid = document.getElementById('calendarGrid');
    const currentMonthElement = document.getElementById('currentMonth');
    const prevMonthButton = document.getElementById('prevMonth');
    const nextMonthButton = document.getElementById('nextMonth');
    //const choiceDay = document.querySelector('.calendar-day.selected');

    let currentDate = new Date();
    let selectedDate = null;

    function renderCalendar() {
        const year = currentDate.getFullYear();
        const month = currentDate.getMonth();

        currentMonthElement.textContent = `${currentDate.toLocaleString('ko-KR', { month: 'long' })} ${year}`;

        const firstDay = new Date(year, month, 1);
        const lastDay = new Date(year, month + 1, 0);
        const today = new Date();
        today.setHours(0, 0, 0, 0);

        calendarGrid.innerHTML = `
            <div class="calendar-weekday">일</div>
            <div class="calendar-weekday">월</div>
            <div class="calendar-weekday">화</div>
            <div class="calendar-weekday">수</div>
            <div class="calendar-weekday">목</div>
            <div class="calendar-weekday">금</div>
            <div class="calendar-weekday">토</div>
        `;

        for (let i = 0; i < firstDay.getDay(); i++) {
            calendarGrid.innerHTML += '<div class="calendar-day"></div>';
        }

        for (let day = 1; day <= lastDay.getDate(); day++) {
            const dayElement = document.createElement('div');
            dayElement.classList.add('calendar-day');
            dayElement.textContent = day;

            const currentDayDate = new Date(year, month, day);
            if(day === today.getDate()) dayElement.classList.toggle('selected');
            if (currentDayDate < today) {
                dayElement.classList.add('disabled');
            } else {
                dayElement.addEventListener('click', () => selectDate(day));
            }

            calendarGrid.appendChild(dayElement);
        }
    }

    function selectDate(day) {
        selectedDate = new Date(currentDate.getFullYear(), currentDate.getMonth(), day);
        console.log(`선택된 날짜: ${currentDate.getFullYear()}-${currentDate.getMonth()}-${day}`);
        console.log(selectedDate);
        const allDays = document.querySelectorAll('.calendar-day');
        allDays.forEach(dayElement => dayElement.classList.remove('selected'));

        const selectedDay = Array.from(allDays).find(dayElement => dayElement.textContent === day.toString());
        if (selectedDay) {
            selectedDay.classList.add('selected');
        }
    }

    prevMonthButton.addEventListener('click', () => {
        currentDate.setMonth(currentDate.getMonth() - 1);
        renderCalendar();
    });

    nextMonthButton.addEventListener('click', () => {
        currentDate.setMonth(currentDate.getMonth() + 1);
        renderCalendar();
    });

    renderCalendar();

    let choiceDay = document.querySelector('.calendar-day.selected');
    const testButton = document.getElementById("testButton");
    testButton.addEventListener('click', () =>{
        alert("테스트 버튼 클릭");
        alert(message[0]);
        alert(currentDate.getFullYear());
        alert(currentDate.getMonth()+1);
        alert(choiceDay.textContent);
        let date = new Date(currentDate.getFullYear(),currentDate.getMonth()+1, choiceDay.textContent);
        alert(date);
        alert(currentDate.getFullYear() + '-' + (currentDate.getMonth()+1) + '-' + choiceDay.textContent);
    });

    /*------------------------------------*/
    function createTimeButtons() {

        const container = document.getElementById('timeContainer');

        const startTime = fitDTO.openTime;
        const endTime = fitDTO.closeTime;

        for(let hour = parseInt(startTime.split(":")[0], 10); hour<parseInt(endTime.split(":")[0], 10); hour++){
            const timeDiv = document.createElement('div');
            timeDiv.className = 'time-btn';
            timeDiv.textContent = `${String(hour).padStart(2, "0")}:00`;

            if(reservedTimes.includes(timeDiv.textContent+":00")){
                timeDiv.classList.add('disabled');
            }
            else{
                timeDiv.addEventListener('click', function() {
                // 기존에 선택된 버튼의 선택 상태를 해제
                document.querySelectorAll('.time-btn.selected')
                    .forEach(btn => btn.classList.remove('selected'));

                // 현재 버튼을 선택 상태로 변경
                this.classList.add('selected');
                });
            }

            container.appendChild(timeDiv);
            console.log(document.querySelector('.time-btn').textContent);
        }
    }

    createTimeButtons();

    let choiceTime = null;
    const timeTestButton = document.getElementById("timeTestButton");
    timeTestButton.addEventListener('click', () =>{
        choiceTime = document.querySelector('.time-btn.selected');

        alert("시간테스트 버튼 클릭");
        alert(choiceTime.textContent);
        console.log(choiceTime.textContent);
    });

    dateTimeSubmit = document.getElementById("transform");

    dateTimeSubmit.addEventListener('click', () => {
        choiceTime = document.querySelector('.time-btn.selected');
        console.log(choiceTime);
        if(selectDate === null || choiceTime === null){
            alert("날짜와 시간을 선택해주세요");
            console.log("실패");
            return;
        }
        //let date = `${selectedDate.getFullYear()}-${selectedDate.getMonth()+1}-${selectedDate.getDate()}`;
        let date = `${currentDate.getFullYear()}-${("00" + (currentDate.getMonth() + 1)).slice(-2)}-${choiceDay.textContent.padStart(2, "0")}`;
        let time = `${choiceTime.textContent}`;
        console.log(date);
        console.log(time);

        let submitDate = document.getElementById("reservationDate");
        submitDate.value=`${date}`;
        console.log(submitDate.value);

        let submitTime = document.getElementById("reservationStartTime");

        console.log((`${time}`.split(":")[0].padStart(2,"0"))); // 시 출력
        console.log(`${time}`.split(":")[1].padStart(2,"0")); // 분 출력
        submitTime.value = `${time.split(":")[0].padStart(2, "0")}:${time.split(":")[1].padStart(2, "0")}`;
        console.log(submitTime.value);

        let submitPersonCnt = document.getElementById("personCnt").value;
        console.log(submitPersonCnt);

        let reservationForm = document.getElementById("reservationForm");
        console.log(reservationForm);
        console.log(gymNum);
        reservationForm.submit();
    });
});