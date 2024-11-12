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

        let choiceDay = document.querySelector('.calendar-day.selected');

        let date = new Date(currentDate.getFullYear(),currentDate.getMonth()+1, choiceDay.textContent);
        //alert(date);
        // 날짜 형식에 두 자리 수 보정
        var month = (currentDate.getMonth() + 1).toString().padStart(2, '0');
        var day = choiceDay.textContent.toString().padStart(2, '0');
        var submitDayRest = currentDate.getFullYear() + '-' + month + '-' + day;

        //alert(submitDayRest);

        // URL에 인코딩하여 파라미터 전달
        fetch("http://127.0.0.1:8080/reservation/selectDay?gymNum="+encodeURIComponent(gymNum) + "&selectedDay="+ encodeURIComponent(submitDayRest))
            .then((response) => response.json())
            .then((data) => {
                createTimeButtonsRest(data);
            })
            .catch((error) => console.log(error));
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

    //let choiceDay = document.querySelector('.calendar-day.selected');
    const testButton = document.getElementById("testButton");
//    testButton.addEventListener('click', () =>{
//        alert("테스트 버튼 클릭");
//        alert(message[0]);
//        alert(currentDate.getFullYear());
//        alert(currentDate.getMonth()+1);
//        alert(choiceDay.textContent);
//        let date = new Date(currentDate.getFullYear(),currentDate.getMonth()+1, choiceDay.textContent);
//        alert(date);
//        alert(currentDate.getFullYear() + '-' + (currentDate.getMonth()+1) + '-' + choiceDay.textContent);
//    });

    /*------------------------------------*/

    function convertTo24HourFormat(time12hr) {
        const [time, modifier] = time12hr.split(' '); // 시간을 AM/PM으로 나누기
        let [hours, minutes, seconds] = time.split(':'); // 시간, 분, 초로 분리

        hours = parseInt(hours, 10); // 문자열을 숫자로 변환

        // 오전(A.M.)일 경우 12시를 제외하고는 그대로, 오후(P.M.)일 경우 12시를 더함
        if (modifier === '오후' && hours !== 12) {
            hours += 12; // 오후는 12시간 더하기
        } else if (modifier === '오전' && hours === 12) {
            hours = 0; // 오전 12시는 자정, 0시로 변환
        }

        // 24시간 형식으로 변환된 시간 반환
        return `${String(hours).padStart(2, '0')}:${minutes}:${seconds}`;
    }

    function createTimeButtonsRest(data) {

        console.log("data : " + data);


        const container = document.getElementById('timeContainer');
        container.innerHTML = "";

        const startTime = fitDTO.openTime;
        const endTime = fitDTO.closeTime;
        var timeDiv = document.createElement('div');
        for(let hour = parseInt(startTime.split(":")[0], 10); hour<parseInt(endTime.split(":")[0], 10); hour++){
            var timeDiv = document.createElement('div');

            timeDiv.className = 'time-btn';
            timeDiv.textContent = `${String(hour).padStart(2, "0")}:00`;
            if(data != null && data != ''){
                var reservedTimes24 = data.map(time=>convertTo24HourFormat(time));
                console.log("reservedTimes24 : " + reservedTimes24);
                if(reservedTimes24.includes(timeDiv.textContent+":00")){
                    console.log("disabled 시간 : " + reservedTimes24);
                    timeDiv.classList.add('disabled');
                }
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

        function createTimeButtons() {

            const container = document.getElementById('timeContainer');

            const startTime = fitDTO.openTime;
            const endTime = fitDTO.closeTime;
            const currentHour = new Date().getHours()+2;
            console.log(currentHour);
            var timeDiv = document.createElement('div');
            for(let hour = parseInt(startTime.split(":")[0], 10); hour<parseInt(endTime.split(":")[0], 10); hour++){
                var timeDiv = document.createElement('div');

                timeDiv.className = 'time-btn';
                timeDiv.textContent = `${String(hour).padStart(2, "0")}:00`;

                if(reservedTimes.includes(timeDiv.textContent+":00")){
                    timeDiv.classList.add('disabled');
                }
                if (hour < currentHour) {
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
//    const timeTestButton = document.getElementById("timeTestButton");
//    timeTestButton.addEventListener('click', () =>{
//        choiceTime = document.querySelector('.time-btn.selected');
//
//        alert("시간테스트 버튼 클릭");
//        alert(choiceTime.textContent);
//        console.log(choiceTime.textContent);
//    });

    dateTimeSubmit = document.getElementById("transform");

    dateTimeSubmit.addEventListener('click', () => {
        choiceTime = document.querySelector('.time-btn.selected');
        console.log(choiceTime);
        if(selectDate === null || choiceTime === null){
            alert("날짜와 시간을 선택해주세요");
            console.log("실패");
            return;
        }

        let choiceDay = document.querySelector('.calendar-day.selected');
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

function selectDay(){
    //alert("aa");
}

function personCntCheck(){
    var cnt = document.getElementById("personCnt");
    if(cnt.value<1 || cnt.value > fitDTO.maxCnt){
        alert("수용인원이 맞지 않습니다.")
        cnt.value = 1;
        return false;
    }
}

function reserveForm(){
    const loginCheck = document.getElementById('reserve').dataset.sessionNum;
    var reserveFormGo = document.getElementById("reserve");
    alert("loginCheck : " + loginCheck);
    if (isNaN(loginCheck) || loginCheck === 0 || loginCheck === null) {
        alert("회원만 접근 가능합니다.");
        return false;
    }
    //alert("aa");
    //alert("aa");
}