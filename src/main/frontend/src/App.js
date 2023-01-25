import React, {useEffect, useState} from 'react';
import { Calendar, momentLocalizer } from 'react-big-calendar';
import moment from 'moment';
import 'react-big-calendar/lib/css/react-big-calendar.css';
import axios from "axios";
function App() {
  const [selectedDate, setSelectedDate] = useState(new Date());
  const [events, setEvents] = useState([]);


    useEffect(() => {
     
        axios.get("http://localhost:8080/api/calendar/dates")
            .then(response => {
                console.log("Received dates:", response.data);
                const events = response.data.map(date => ({
                    start: date.startDate,
                    end: date.endDate,
                    title: date.courseName
                }));
                setEvents(events);
            })
            .catch(error => {
                console.error("Error getting events:", error);
            });
    }, []);

   

  const localizer = momentLocalizer(moment);

  return (
      <Calendar
          localizer={localizer}
          events={events}
          defaultDate={moment().toDate()}
          startAccessor="start"
          endAccessor="end"
          style={{height:600}}
          onNavigate={(date) => setSelectedDate(date)}
          dayPropGetter={(date) => {
            let newStyle = {};
            if (date.getMonth() !== selectedDate.getMonth()) {
                newStyle.backgroundColor = 'rgba(0,0,255,0.2)';
            }
            return {
                className: "",
                style: newStyle
            };
            }}
      />
  );
}



export default App;

