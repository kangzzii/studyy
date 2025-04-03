<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  
<article class="calendar-wrap">
    <aside class="side">
      <div class="sidebar-item">
        <input class="checkbox-all" type="checkbox" id="all" value="all" checked />
        <label class="checkbox checkbox-all" for="all">View all</label>
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
          <a href="#" class="dropdown-item" data-view-name="month">Monthly</a>
          <a href="#" class="dropdown-item" data-view-name="week">Weekly</a>
          <a href="#" class="dropdown-item" data-view-name="day">Daily</a>
        </div>
      </div>
    </div>
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
    <div class="nav-checkbox">
      <input class="checkbox-collapse" type="checkbox" id="collapse" value="collapse" />
      <label for="collapse">Collapse duplicate events and disable the detail popup</label>
    </div>
  </nav>
  <main id="app"></main>
</section>
</article>
   
<!-- css -->
<link rel="stylesheet" href="/resource/css/toastui-calendar.min.css">
<link rel="stylesheet" href="/resource/css/calendar/app.css" />
<link rel="stylesheet" href="/resource/css/calendar/icons.css" />
<!-- js -->
<script src="/resource/js/calendar/moment.min.js"></script>
<script src="/resource/js/calendar/toastui-calendar.min.js"></script>
<script src="/resource/js/calendar/mockData.js"></script>
<script src="/resource/js/calendar/utils.js"></script>

<script>
$(function(){
    init.calendar();
})
/////////////////////////////////////////////////////////
var cal;
var cls = function (className) {
    return 'toastui-calendar-' + className;
};
// 요소
var navbarRange = $('.navbar--range');
var prevButton = $('.prev');
var nextButton = $('.next');
var todayButton = $('.today');
var dropdown = $('.dropdown');
var dropdownTrigger = $('.dropdown-trigger');
var dropdownTriggerIcon = $('.dropdown-icon');
var dropdownContent = $('.dropdown-content');
var checkboxCollapse = $('.checkbox-collapse');
var sidebar = $('.side');

// 초기함수
var init = {
    // 달력
    calendar : function() {
        // 달력 초기셋팅
        cal = new tui.Calendar($('#app')[0],{ 
            calendars: MOCK_CALENDARS,
            useFormPopup: true, // 팝업
            useDetailPopup: true,   // 팝업
            eventFilter: function (event) {
              var currentView = cal.getViewName();
              if (currentView === 'month') {
                return ['allday', 'time'].includes(event.category) && event.isVisible;
              }

              return event.isVisible;
            },
            template: {
              allday: function (event) {
                return getEventTemplate(event, true);
              },
              time: function (event) {
                return getEventTemplate(event, false);
              },
            },
        });
        
        // sidebar 셋팅
        function initSideBar() {
            var txt= '';
            for(let i=0;i<MOCK_CALENDARS.length ;i++){
              txt += '<div class="sidebar-item">';
              txt += '  <input type="checkbox" id="'+MOCK_CALENDARS[i].id+'" value="'+MOCK_CALENDARS[i].id+'" checked />';
              txt += '  <label class="checkbox checkbox-calendar checkbox-'+(i+1)+'" for="'+MOCK_CALENDARS[i].id+'">'+MOCK_CALENDARS[i].name+'</label>';
              txt += '</div>';
            }
            $('.side').append(txt);
        }
        
        // Init
        bindInstanceEvents();
        bindAppEvents();
        update();
        initSideBar();
        initCheckbox();
    },
}


// App State
var appState = {
  activeCalendarIds: MOCK_CALENDARS.map(function (calendar) { // calendarId 주입
    return calendar.id;
  }),
  isDropdownActive: false,
};

// functions to handle calendar behaviors
function reloadEvents() {
  // var randomEvents;
  /* randomEvents = [
    cal.getViewName(),
    cal.getDateRangeStart(),
    cal.getDateRangeEnd()
  ]; */

  cal.clear();
  cal.createEvents(eventData);  // 이벤트 주입
}

function getReadableViewName(viewType) {
  switch (viewType) {
    case 'month':
      return 'Monthly';
    case 'week':
      return 'Weekly';
    case 'day':
      return 'Daily';
    default:
      throw new Error('no view type');
  }
}

// 달력 시작일 종료일
function displayRenderRange() {
  var rangeStart = cal.getDateRangeStart();
  var rangeEnd = cal.getDateRangeEnd();

  navbarRange.text(getNavbarRange(rangeStart, rangeEnd, cal.getViewName()));
}

function setDropdownTriggerText() {
  var viewName = cal.getViewName();
  var buttonText = $('.dropdown .button-text');
  buttonText.text(getReadableViewName(viewName));
}

function toggleDropdownState() {
  appState.isDropdownActive = !appState.isDropdownActive;
  dropdown.toggleClass('is-active', appState.isDropdownActive);
  dropdownTriggerIcon.toggleClass(cls('open'), appState.isDropdownActive);
}

// sidebar all check
function setAllCheckboxes(checked) {
  var checkboxes = $('.sidebar-item > input[type="checkbox"]');

  checkboxes.each(function (checkbox) {
    checkbox.checked = checked;
    setCheckboxBackgroundColor(checkbox);
  });
}

//sidebar all check
function setCheckboxBackgroundColor(checkbox) {
    var calendarId = $(checkbox).val();
    
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

// update
function update() {
  setDropdownTriggerText();
  displayRenderRange();
  reloadEvents();
}
// event
function bindAppEvents() {
  dropdownTrigger.on('click', toggleDropdownState);
  //btn prev
  prevButton.on('click', function () {
    cal.prev();
    update();
  });
  // btn next
  nextButton.on('click', function () {
    cal.next();
    update();
  });
  // btn today
  todayButton.on('click', function () {
    cal.today();
    update();
  });
  // drop down
  dropdownContent.on('click', function (e) {
    var targetViewName;

    if ('viewName' in e.target.dataset) {
      targetViewName = e.target.dataset.viewName;
      cal.changeView(targetViewName);
      checkboxCollapse.disabled = targetViewName === 'month';
      toggleDropdownState();
      update();
    }
  });

  checkboxCollapse.on('change', function (e) {
    if ('checked' in e.target) {
      cal.setOptions({
        week: {
          collapseDuplicateEvents: !!e.target.checked,
        },
        useDetailPopup: !e.target.checked,
      });
    }
  });

  sidebar.on('click', function (e) {
    if ('value' in e.target) {
      if (e.target.value === 'all') {
        if (appState.activeCalendarIds.length > 0) {
          cal.setCalendarVisibility(appState.activeCalendarIds, false);
          appState.activeCalendarIds = [];
          setAllCheckboxes(false);
        } else {
          appState.activeCalendarIds = MOCK_CALENDARS.map(function (calendar) {
            return calendar.id;
          });
          cal.setCalendarVisibility(appState.activeCalendarIds, true);
          setAllCheckboxes(true);
        }
      } else if (appState.activeCalendarIds.indexOf(e.target.value) > -1) {
        appState.activeCalendarIds.splice(appState.activeCalendarIds.indexOf(e.target.value), 1);
        cal.setCalendarVisibility(e.target.value, false);
        setCheckboxBackgroundColor(e.target);
      } else {
        appState.activeCalendarIds.push(e.target.value);
        cal.setCalendarVisibility(e.target.value, true);
        setCheckboxBackgroundColor(e.target);
      }
    }
  });
}

function bindInstanceEvents() {
  cal.on({
    clickMoreEventsBtn: function (btnInfo) {
      console.log('clickMoreEventsBtn', btnInfo);
    },
    clickEvent: function (eventInfo) {
      console.log('clickEvent', eventInfo);
    },
    clickDayName: function (dayNameInfo) {
      console.log('clickDayName', dayNameInfo);
    },
    selectDateTime: function (dateTimeInfo) {
      console.log('selectDateTime', dateTimeInfo);
    },
    beforeCreateEvent: function (event) {
      console.log('beforeCreateEvent', event);
      event.id = chance.guid();

      cal.createEvents([event]);
      cal.clearGridSelections();
    },
    beforeUpdateEvent: function (eventInfo) {
      var event, changes;

      console.log('beforeUpdateEvent', eventInfo);

      event = eventInfo.event;
      changes = eventInfo.changes;

      cal.updateEvent(event.id, event.calendarId, changes);
    },
    beforeDeleteEvent: function (eventInfo) {
      console.log('beforeDeleteEvent', eventInfo);

      cal.deleteEvent(eventInfo.id, eventInfo.calendarId);
    },
  });
}

function initCheckbox() {
  var checkboxes = $('input[type="checkbox"]');
  checkboxes.each(function (indx,item) {
    setCheckboxBackgroundColor(item);
  });
}

function getEventTemplate(event, isAllday) {
  var html = [];
  var start = moment(event.start.toDate().toUTCString());
  if (!isAllday) {
    html.push('<strong>' + start.format('HH:mm') + '</strong> ');
  }

  if (event.isPrivate) {
    html.push('<span class="calendar-font-icon ic-lock-b"></span>');
    html.push(' Private');
  } else {
    if (event.recurrenceRule) {
      html.push('<span class="calendar-font-icon ic-repeat-b"></span>');
    } else if (event.attendees.length > 0) {
      html.push('<span class="calendar-font-icon ic-user-b"></span>');
    } else if (event.location) {
      html.push('<span class="calendar-font-icon ic-location-b"></span>');
    }
    html.push(' ' + event.title);
  }

  return html.join('');
}

</script>