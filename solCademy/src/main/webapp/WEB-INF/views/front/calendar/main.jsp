<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<article class="calendar-wrap">
    <aside class="side">
      <div class="sidebar-item">
        <input class="checkbox-all" type="checkbox" id="memberAll" value="memberAll" checked />
        <label class="checkbox checkbox-all" for="memberAll">View all</label>
      </div>
      <hr />
    </aside>
    <section class="app-column">
  <nav class="navbar">
    <div class="dropdown">
      <div class="dropdown-trigger">
        <button
          class="button is-rounded"
          aria-haspopup="true"
          aria-controls="dropdown-menu"
        >
          <span class="button-text"></span>
          <span
            class="dropdown-icon toastui-calendar-icon toastui-calendar-ic-dropdown-arrow"
          ></span>
        </button>
      </div>
      <div class="dropdown-menu">
        <div class="dropdown-content">
          <a href="#" class="dropdown-item" data-view-name="month">월간일정</a>
          <a href="#" class="dropdown-item" data-view-name="week">주간일정</a>
          <a href="#" class="dropdown-item" data-view-name="day">일간일정</a>
        </div>
      </div>
    </div>
    <button class="button is-rounded register">등록</button>
    <button class="button is-rounded today">Today</button>
    <button class="button is-rounded prev">
      <img
        alt="prev"
        src="/resource/images/ic-arrow-line-left.png"
      />
    </button>
    <button class="button is-rounded next">
      <img
        alt="prev"
        src="/resource/images/ic-arrow-line-right.png"
      />
    </button>
    <span class="navbar--range"></span>
  </nav>
  <main id="app"></main>
</section>
</article>

<script>
$(function(){
    calendar.init();
})
// App State
var appState = {
  activeCalendarIds: MOCK_CALENDARS.map(function (calendar) { // calendarId 주입
    return calendar.id;
  }),
  isDropdownActive: false,
};
var cal;
var calFunc = {
        // 드롭다운 텍스트 리턴
        getReadableViewName : function(viewType){
            switch (viewType) {
            case 'month':
              return '월간일정';
            case 'week':
              return '주간일정';
            case 'day':
              return '일간일정';
            default:
              throw new Error('no view type');
          }
        }
       // dropdown toggle class
       ,toggleDropdownState: function(){
           $('.dropdown').toggleClass('is-active', appState.isDropdownActive);
           $('.dropdown-icon').toggleClass('open', appState.isDropdownActive)
           appState.isDropdownActive = !appState.isDropdownActive;
       }
       // checkbox all
       ,setAllCheckboxes :function(checked) {    // checked: true,false
           var checkboxes = $('.sidebar-item > input[type="checkbox"]');
           checkboxes.each(function (index, checkbox) {
               checkbox.checked = checked;
               calFunc.setCheckboxBackgroundColor(checkbox);
           });
       }
       // 사이드바 체크박스 색상
        ,setCheckboxBackgroundColor: function(checkbox) {
            var calendarId = $(checkbox).val(); // member__ 값
            var label = $(checkbox).siblings('label');
            var calendarInfo = MOCK_CALENDARS.find(function (calendar) {
                return calendar.id === calendarId;
              });
            if (!calendarInfo) {
                calendarInfo = {
                  backgroundColor: '#2a4fa7',
                };
            }
            label.css('--checkbox-' + calendarId.split('member')[1], checkbox.checked ? calendarInfo.backgroundColor : '#fff');
        }
}
var calendar = {
        // 초기값
        init: function(){
            calendar.setCal();  // 캘린더 기본 셋팅
            calendar.bindEvent();
        }
        //캘린더 초기설정
        ,setCal: function(){
            // calendar 기본설정
            cal = new tui.Calendar($('#app')[0],{
                calendars: MOCK_CALENDARS
                ,defaultView: 'month'
                ,useFormPopup: true // 팝업
                ,useDetailPopup: true   // 팝업
            });
            // 요일 셋팅
            cal.setOptions({
                week: {
                  dayNames: ['월', '화', '수', '목', '금', '토', '일']
                },
            });

            // 사이드바 셋팅
            var txt= '';
            for(let i=0;i<MOCK_CALENDARS.length ;i++){
              txt += '<div class="sidebar-item">';
              txt += '  <input type="checkbox" id="'+MOCK_CALENDARS[i].id+'" value="'+MOCK_CALENDARS[i].id+'" checked />';
              txt += '  <label class="checkbox checkbox-calendar checkbox-'+(i+1)+'" for="'+MOCK_CALENDARS[i].id+'">'+MOCK_CALENDARS[i].name+'</label>';
              txt += '</div>';
            }
            $('.side').append(txt);

            // 사이드바 내부 체크박스 색 추가
            var checkboxes = $('.sidebar-item > input[type="checkbox"]');
            checkboxes.each(function (index, checkbox) {
                checkbox.checked = 'checked';
                calFunc.setCheckboxBackgroundColor(checkbox);
            });

            //드롭다운 세팅
            var viewName = cal.getViewName();
            var buttonText = $('.dropdown .button-text');
            buttonText.text(calFunc.getReadableViewName(viewName));

            // 날짜 범위표시
            calendar.renderRange();
        }
        // update
        ,updateCal : function(){
            var viewName = cal.getViewName();
            var buttonText = $('.dropdown .button-text');
            buttonText.text(calFunc.getReadableViewName(viewName));
            calendar.renderRange()
            cal.clear();
            cal.createEvents(eventData);  // 이벤트 주입
        }
        // 날짜 범위표시
        ,renderRange : function(){
            var rangeStart = cal.getDateRangeStart();
            var rangeEnd = cal.getDateRangeEnd();

            var date;
            var txt;
            var viewName = cal.getViewName();

            if( viewName == 'month' ){  // 월간일정
                date = getNavbarRange(rangeStart, rangeEnd, cal.getViewName()).split('-');
                txt = date[0] + '년 ' + date[1] + '월';
            } else if( viewName == 'day') { //일간일정
                date = getNavbarRange(rangeStart, rangeEnd, cal.getViewName()).split('-');
                txt = date[0] + '년 ' +  + date[1] + '월 ' + date[2] + '일';
            } else {    // 주간일정
                date = getNavbarRange(rangeStart, rangeEnd, 'month').split('-');

                txt = date[0] + '년 ' + date[1] + '월 ' + '' +'주차';
            }
            $('.navbar--range').text(txt);
        }
        // 이벤트 데이터 ajax
        ,setCreateEvent : function(event){
            $.ajax({
                url: '/calendar/ajax/createEvent.do'
                ,type: 'post'
                ,contentType: 'application/json'
                ,data: JSON.stringify(event)
                ,success : function(res){
                   console.log('suc------------'+ res);
                }
                ,error : function(res){
                    console.log('err------------'+ res);
                }
            })
        }

        // 이벤트
        ,bindEvent :function(){
            // 달력 인스턴스 이벤트
            cal.on({
                beforeCreateEvent: function (event) {   // 이벤트 추가
                    console.log('beforeCreateEvent', event);
                    calendar.setCreateEvent(event);
                    cal.createEvents([event]);
                    cal.clearGridSelections();
                }
                ,beforeUpdateEvent: function (eventInfo) {   // 이벤트 수정
                    var event, changes;
                    console.log('beforeUpdateEvent', eventInfo);

                    event = eventInfo.event;
                    changes = eventInfo.changes;

                    cal.updateEvent(event.id, event.calendarId, changes);
                }
                ,beforeDeleteEvent: function (eventInfo) {  // 이벤트 삭제
                    console.log('beforeDeleteEvent', eventInfo);

                    cal.deleteEvent(eventInfo.id, eventInfo.calendarId);
                },
            })

            // dropdown 이벤트
            $('.dropdown-trigger').on('click', calFunc.toggleDropdownState);
            // dropdown contents event
            $('.dropdown-content').on('click', function (e) {
              var targetViewName;
              if ('viewName' in e.target.dataset) {
                targetViewName = e.target.dataset.viewName;
                cal.changeView(targetViewName);
                calFunc.toggleDropdownState();
                calendar.updateCal();
              }
            });
            //btn prev
            $('.prev').on('click', function () {
              cal.prev();
              calendar.updateCal();
            });
            // btn next
            $('.next').on('click', function () {
              cal.next();
              calendar.updateCal();
            });
            // btn today
             $('.today').on('click', function () {
              cal.today();
              calendar.updateCal();
            });
            // btn 등록
            $('.register').on('click', function () {
                cal.openFormPopup()
            });
            // 체크박스 이벤트
            $('.side').on('click', function (e) {
              if ($(e.target).val()) {
                if ($(e.target).val() === 'memberAll') {  // check all
                  if (appState.activeCalendarIds.length > 0) {
                    cal.setCalendarVisibility(appState.activeCalendarIds, false);
                    appState.activeCalendarIds = [];
                    calFunc.setAllCheckboxes(false);
                  } else {
                    appState.activeCalendarIds = MOCK_CALENDARS.map(function (calendar) {
                      return calendar.id;
                    });
                    cal.setCalendarVisibility(appState.activeCalendarIds, true);
                    calFunc.setAllCheckboxes(true);
                  }
                } else if (appState.activeCalendarIds.indexOf(e.target.value) > -1) {
                  appState.activeCalendarIds.splice(appState.activeCalendarIds.indexOf(e.target.value), 1);
                  cal.setCalendarVisibility(e.target.value, false);
                  calFunc.setCheckboxBackgroundColor(e.target);
                } else {
                  appState.activeCalendarIds.push(e.target.value);
                  cal.setCalendarVisibility(e.target.value, true);
                  calFunc.setCheckboxBackgroundColor(e.target);
                }
              }
            });
        }
}
</script>