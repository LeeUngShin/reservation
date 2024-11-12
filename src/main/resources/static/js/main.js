document.addEventListener('DOMContentLoaded', function() {
    const regionChoice = document.getElementById('regionChoice');
    const bigRegion = document.getElementById('big_region');
    const smallRegion = document.getElementById('small_region');
    const typeChoice = document.getElementById('typeChoice');
    const type = document.getElementById('type');

//    function initializePage() {
//        alert(regionChoice);
//        alert(bigRegion);
//        alert(smallRegion);
//        alert(typeChoice);
//        alert(type);
//    }

    window.onload = function() {
        initializePage();
    };


    function toggleRegionChoice() {  // 지역 대분류 보이게 하기
        if (bigRegion.style.display === 'none' || bigRegion.style.display === '') {
            console.log("지역 대분류 논");
            bigRegion.style.display = 'block';
            smallRegion.style.display = 'none';
            type.style.display = 'none';
        } else {
            console.log("지역 대분류 블락");
            bigRegion.style.display = 'none';
        }
    }

    regionChoice.addEventListener('click', toggleRegionChoice);

    const regions = {
        '서울': ['강남구', '서초구', '송파구', '강동구', '강서구', '양천구', '구로구', '영등포구', '금천구', '동작구', '관악구', '서대문구', '은평구', '마포구', '용산구', '중구', '종로구', '동대문구', '성동구', '광진구', '중랑구', '노원구', '도봉구', '강북구'],
        '경기': ['수원시', '성남시', '의정부시', '안양시', '부천시', '광명시', '평택시', '동두천시', '안산시', '고양시', '과천시', '구리시', '남양주시', '오산시', '시흥시', '군포시', '의왕시', '하남시', '용인시', '파주시', '이천시', '안성시', '김포시', '화성시', '광주시', '양주시', '포천시', '여주시'],
        '인천': ['중구', '동구', '미추홀구', '연수구', '남동구', '부평구', '계양구', '서구', '강화군', '옹진군']
    };

    const choicedBigRegions = document.querySelectorAll('.bigRegion');
    choicedBigRegions.forEach(choicedBigRegion =>{
        choicedBigRegion.addEventListener('click', function(){
            const region = choicedBigRegion.textContent.trim();
            bigRegion.style.display = 'none';
            smallRegion.innerHTML = `
             <span id="Metropolitan" style="display: inline-block; margin-top: 5px; margin-left: 5px;">${region}</span> >
             <span style="display: inline-block; margin-top: 5px;">시/군/구 선택</span>
             <hr>
             `;

            regions[region].forEach(subRegion => {
             const button = document.createElement('button');
             button.type = 'button';
             button.className = 'btn btn-light';
             button.textContent = subRegion;
             button.onclick = () => choiceSmallRegion(region, subRegion);  // 지역 소분류 선택 시 이 함수 호출
             smallRegion.appendChild(button);
            });
            smallRegion.style.display = 'block';
        });
    });


//    function choiceBigRegion(region){  // 지역 대분류 선택 시 이 함수 호출, () 안에는 대분류 값
//        bigRegion.style.display = 'none';
//        smallRegion.innerHTML = `
//            <span id="Metropolitan" style="display: inline-block; margin-top: 5px; margin-left: 5px;">${region}</span> >
//            <span style="display: inline-block; margin-top: 5px;">시/군/구 선택</span>
//            <hr>
//            `;
//
//        regions[region].forEach(subRegion => {
//            const button = document.createElement('button');
//            button.type = 'button';
//            button.className = 'btn btn-light';
//            button.textContent = subRegion;
//            button.onclick = () => choiceSmallRegion(region, subRegion);  // 지역 소분류 선택 시 이 함수 호출
//            smallRegion.appendChild(button);
//        });
//        smallRegion.style.display = 'block';
//    }

    function choiceSmallRegion(region, subRegion) {
        console.log('Selected:', subRegion);
        smallRegion.style.display = 'none';
        //regionChoice.textContent = `${region}-${subRegion}`;
        regionChoice.firstChild.nodeValue = '';
        document.getElementById('a').textContent = `${region}`;
        document.getElementById('b').textContent = `${subRegion}`;
    }

    document.getElementById("typeChoice").addEventListener('click', function(){
        if (type.style.display === 'none' || type.style.display === '') {
           type.style.display = 'block';
           bigRegion.style.display = 'none';
           smallRegion.style.display = 'none';
        } else {
            type.style.display = 'none';
        }
    });

//    function toggleTypeChoice(){  // 유형선택 클릭 시 호출
//        if (type.style.display === 'none' || type.style.display === '') {
//            type.style.display = 'block';
//            bigRegion.style.display = 'none';
//            smallRegion.style.display = 'none';
//        } else {
//            type.style.display = 'none';
//        }
//    }

    const choiceTypes = document.querySelectorAll('.type');
    choiceTypes.forEach(choiceType =>{
        choiceType.addEventListener('click', function(){

            const inOutType = choiceType.textContent.trim();
            type.style.display = 'none';
            typeChoice.textContent = `${inOutType}`;
        })
    });
});

function submitChoice(form){

    var bigRegion = document.getElementById('a').textContent;
    var smallRegion = document.getElementById('b').textContent;
    var selectedType = typeChoice.textContent;  // 이름 변경
    console.log(bigRegion);
    console.log(smallRegion);
    console.log(selectedType);
    //if(bigRegion === '전체') bigRegion = "";
    //if(smallRegion === '전체') smallRegion = "";
    //if(selectedType === '전체') selectedType = "";
    if(selectedType === '실내') selectedType = '실내';
    if(selectedType === '실외') selectedType = '실외';
    if(selectedType === "유형선택") selectedType = '';

    if(bigRegion === '' || smallRegion === '' || selectedType === ''){
        alert("지역과 유형을 선택하세요!");
        return false;
    }

    var bigRegionInput = document.getElementById("bigRegionInput");
    bigRegionInput.value = bigRegion;
    var smallRegionInput = document.getElementById("smallRegionInput");
    smallRegionInput.value = smallRegion
    var choiceTypeInput = document.getElementById("choiceTypeInput");
    choiceTypeInput.value = selectedType;
    //var url = `/fit/fitList?bigRegion=${encodeURIComponent(bigRegion)}&smallRegion=${encodeURIComponent(smallRegion)}&choiceType=${encodeURIComponent(selectedType)}`;
    form.submit();
}