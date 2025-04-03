var MOCK_CALENDARS = [
	{
		id: 'member1',
		name: '장관',
		color: '#ffffff',
		borderColor: '#9e5fff',
		backgroundColor: '#9e5fff',
		dragBackgroundColor: '#9e5fff',
	}
	,{
		id: 'member2',
		name: '차관',
		color: '#ffffff',
		borderColor: '#00a9ff',
		backgroundColor: '#00a9ff',
		dragBackgroundColor: '#00a9ff',
	}
	,{
		id: 'member3',
		name: '대변인',
		color: '#ffffff',
		borderColor: '#DB473F',
		backgroundColor: '#DB473F',
		dragBackgroundColor: '#DB473F',
	}
	,{
		id: 'member4',
		name: '의정관',
		color: '#ffffff',
		borderColor: '#03bd9e',
		backgroundColor: '#03bd9e',
		dragBackgroundColor: '#03bd9e',
	}
	,{
		id: 'member5',
		name: '감사관',
		color: '#ffffff',
		borderColor: '#bbdc00',
		backgroundColor: '#bbdc00',
		dragBackgroundColor: '#bbdc00',
	}
	,{
		id: 'member6',
		name: '인사기획관',
		color: '#ffffff',
		borderColor: '#ffd400',
		backgroundColor: '#ffd400',
		dragBackgroundColor: '#ffd400',
	}
	
	,{
		id: 'member7',
		name: '기획조정실장',
		color: '#ffffff',
		borderColor: '#ff7f00',
		backgroundColor: '#ff7f00',
		dragBackgroundColor: '#ff7f00',
	}
	,{
		id: 'member8',
		name: '정책기획관',
		color: '#ffffff',
		borderColor: '#ff3399',
		backgroundColor: '#ff3399',
		dragBackgroundColor: '#ff3399',
	}
	,{
		id: 'member9',
		name: '기타',
		color: '#ffffff',
		borderColor: '#38ecfb',
		backgroundColor: '#38ecfb',
		dragBackgroundColor: '#38ecfb',
	}
]

var eventData = [{
	'id': 'event1'		// 이벤트 아이디
	, 'calendarId': 'member4' // 캘린더 아이디 (멤버아이디)
	, 'title': '주간회의' // 타이틀
	, 'body': '' // 일정 내용
	, 'isReadOnly': false	// 읽기전용 여부
	, 'location': '서울 강남구 강남대로118길 20' // 장소
	, 'attendees': [ // 참가자
		'최명진'
		, '이솔희'
		, '강지연'
		, '김지효'
	]
	, 'state': 'Busy' // 이벤트 바쁨.안바쁨 (Busy, Free)
	, 'goingDuration': 30 // 가는시간
	, 'comingDuration': 30 // 오는시간
	, 'isAllday': false // 하루종일 일정?
	, 'category': 'time' // 일정종류 ('milestone' | 'task' | 'allday' | 'time')
	, 'start': '2025-03-18T10:00:00.000' //시작시간
	, 'end': '2025-03-18T12:00:00.000' // 종료시간
}
, {
	'id': 'event2'
	, 'calendarId': 'member1' 
	, 'title': 'TEST1'
	, 'body': ''
	, 'isReadOnly': false
	, 'isPrivate': false
	, 'location': '서울 강남구 강남대로118길 20'
	, 'attendees': [
		'최명진'
		, '이솔희'
		, '강지연'
		, '김지효'
	]
	, 'recurrenceRule': ''
	, 'state': 'Busy'
	, 'raw': {
		'memo': 'Kij lu mipze todo hibhad lur cep ki jagulpab razfor melficta su cufesfob regukva hoteki.'
		, 'creator': {
			'name': 'Richard Underwood'
			, 'avatar': '//www.gravatar.com/avatar/5cf5e25e5f84fe4993949179046134c7'
			, 'email': 'tutzugov@rebgile.ar'
			, 'phone': '(702) 606-2502'
		}
	}
	, 'isAllday': false
	, 'category': 'time'
	, 'start': '2025-03-19T10:00:00.000'
	, 'end': '2025-03-19T10:30:00.000'
}
, {
	'id': 'event3'
	, 'calendarId': 'member2' 
	, 'title': 'TEST2'
	, 'body': ''
	, 'isReadOnly': false
	, 'isPrivate': false
	, 'location': '서울 강남구 강남대로118길 20'
	, 'attendees': [
		'최명진'
		, '이솔희'
		, '강지연'
		, '김지효'
	]
	, 'recurrenceRule': ''
	, 'state': 'Busy'
	, 'raw': {
		'memo': 'Kij lu mipze todo hibhad lur cep ki jagulpab razfor melficta su cufesfob regukva hoteki.'
		, 'creator': {
			'name': 'Richard Underwood'
			, 'avatar': '//www.gravatar.com/avatar/5cf5e25e5f84fe4993949179046134c7'
			, 'email': 'tutzugov@rebgile.ar'
			, 'phone': '(702) 606-2502'
		}
	}
	, 'isAllday': false
	, 'category': 'time'
	, 'start': '2025-03-19T10:00:00.000'
	, 'end': '2025-03-19T10:30:00.000'
}];


// 		, makeRandomEvents : function(viewName, renderStart, renderEnd){
// 			var i, j;
// 			var event, duplicateEvent;
// 			var events = [];

// 			MOCK_CALENDARS.forEach(function(calendar) {
// 				for (i = 0; i < chance.integer({ min: 20, max: 50 }); i += 1) {
// 					event = generateRandomEvent(calendar, renderStart, renderEnd);
// 					events.push(event);

// 					if (i % 5 === 0) {
// 						for (j = 0; j < chance.integer({min: 0, max: 2}); j+= 1) {
// 							duplicateEvent = JSON.parse(JSON.stringify(event));
// 							duplicateEvent.id += `-${j}`;
// 							duplicateEvent.calendarId = chance.integer({min: 1, max: 5}).toString();
// 							duplicateEvent.goingDuration = 30 * chance.integer({min: 0, max: 4});
// 							duplicateEvent.comingDuration = 30 * chance.integer({min: 0, max: 4});
// 							events.push(duplicateEvent);
// 						}
// 					}
// 				}
// 			});

// 			return events;
// 		}