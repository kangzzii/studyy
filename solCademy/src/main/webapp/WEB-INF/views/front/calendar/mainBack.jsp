<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
      <main id="app" style="height: 400px;"></main>
    </section>

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
var sidebar = $('.sidebar');

// 초기함수
var init = {
    // 달력
    calendar : function() {
        // 달력 초기셋팅
        cal = new tui.Calendar($('#app')[0],{
            defaultView: 'month'
            ,calendars: MOCK_CALENDARS
            ,useFormPopup: true
            ,useDetailPopup: true

        });

         // 펑션 해보라이~~~~
        //bindInstanceEvents();
        bindAppEvents();
        //initCheckbox();
        update();
    }
    // calendar function() end
}
//App State
var appState = {
  activeCalendarIds: MOCK_CALENDARS.map(function (calendar) {
    return calendar.id;
  }),
  isDropdownActive: false,
};

function reloadEvents() {
    var randomEvents;

    cal.clear();
    randomEvents = [
      cal.getViewName(),
      cal.getDateRangeStart(),
      cal.getDateRangeEnd()
    ];
    cal.createEvents(randomEvents);
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

    navbarRange.textContent = getNavbarRange(rangeStart, rangeEnd, cal.getViewName());
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

  // function setAllCheckboxes(checked) {
  //   var checkboxes = $$('.sidebar-item > input[type="checkbox"]');

  //   checkboxes.forEach(function (checkbox) {
  //     checkbox.checked = checked;
  //     setCheckboxBackgroundColor(checkbox);
  //   });
  // }

  // function setCheckboxBackgroundColor(checkbox) {
  //   var calendarId = checkbox.value;
  //   var label = checkbox.nextElementSibling;
  //   var calendarInfo = MOCK_CALENDARS.find(function (calendar) {
  //     return calendar.id === calendarId;
  //   });

  //   if (!calendarInfo) {
  //     calendarInfo = {
  //       backgroundColor: '#2a4fa7',
  //     };
  //   }

  //   label.style.setProperty(
  //     '--checkbox-' + calendarId,
  //     checkbox.checked ? calendarInfo.backgroundColor : '#fff'
  //   );
  // }

  function update() {
    setDropdownTriggerText();
    displayRenderRange();
    reloadEvents();
  }

  function bindAppEvents() {
    dropdownTrigger.on('click', toggleDropdownState);

    prevButton.on('click', function () {
      cal.prev();
      update();
    });

    nextButton.on('click', function () {
      cal.next();
      update();
    });

    todayButton.on('click', function () {
      cal.today();
      update();
    });

    // dropdown 변경 + 캘린더 변경
    dropdownContent.on('click', function (e) {
        var targetViewName;
        if ('viewName' in e.target.dataset) {
          targetViewName = e.target.dataset.viewName;
          cal.changeView(targetViewName);
        // checkboxCollapse.disabled = targetViewName === 'month';
          toggleDropdownState();
          update();
        }
    });

    // checkboxCollapse.on('change', function (e) {
    //   if ('checked' in e.target) {
    //     cal.setOptions({
    //       week: {
    //         collapseDuplicateEvents: !!e.target.checked,
    //       },
    //       useDetailPopup: !e.target.checked,
    //     });
    //   }
    // });

    // sidebar.on('click', function (e) {
    //   if ('value' in e.target) {
    //     if (e.target.value === 'all') {
    //       if (appState.activeCalendarIds.length > 0) {
    //         cal.setCalendarVisibility(appState.activeCalendarIds, false);
    //         appState.activeCalendarIds = [];
    //         setAllCheckboxes(false);
    //       } else {
    //         appState.activeCalendarIds = MOCK_CALENDARS.map(function (calendar) {
    //           return calendar.id;
    //         });
    //         cal.setCalendarVisibility(appState.activeCalendarIds, true);
    //         setAllCheckboxes(true);
    //       }
    //     } else if (appState.activeCalendarIds.indexOf(e.target.value) > -1) {
    //       appState.activeCalendarIds.splice(appState.activeCalendarIds.indexOf(e.target.value), 1);
    //       cal.setCalendarVisibility(e.target.value, false);
    //       setCheckboxBackgroundColor(e.target);
    //     } else {
    //       appState.activeCalendarIds.push(e.target.value);
    //       cal.setCalendarVisibility(e.target.value, true);
    //       setCheckboxBackgroundColor(e.target);
    //     }
    //   }
    // });
  }

  // function initCheckbox() {
  //   var checkboxes = $$('input[type="checkbox"]');

  //   checkboxes.forEach(function (checkbox) {
  //     setCheckboxBackgroundColor(checkbox);
  //   });
  // }

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