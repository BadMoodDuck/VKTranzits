import axios from "axios";

const CALENDAR_REST_API_URL = 'http://localhost:8080/api/calendar/dates';

class CalendarService {

    getDates(){
        return axios.get(CALENDAR_REST_API_URL);
    }
}
export default new CalendarService();